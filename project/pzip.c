 /*
 * NOTE TO STUDENTS: Before you do anything else, please
 * provide your group information here.
 *
 * Group No.:  (Join a project group in Canvas)
 * First member's full name:
 * First member's email address:
 * Second member's full name:
 * Second member's email address:
 * Third member's full name:
 * Third member's email address:
 */

// add/remove header files as you need
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <pthread.h>
#include <fcntl.h>
#include <sys/mman.h>
#include <sys/stat.h>
#include <sys/sysinfo.h>
#include <unistd.h>
#include <math.h>

#define queue_capacity 10
#define file_apart_size 10000000


int total_count_files;          // Number of files
int total_pages = 0;            // Number of pages in All files
int* total_page_in_file;         // Each file has number of pages



//Threads things
int producer_complete = 0;                                 // 0 is false, 1 is true
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER;

// print data
struct structured_file_data {
    char* data;
    int* count;
    int word_count;
}*structured_data;


// Queue
int queue_size = 0;
int queue_head = 0;
int queue_tail = 0;

struct apart_file_data {
    char* address;      // File -> Address
    int file_index;    // File ->
    int page;
    int page_size;
}queue[queue_capacity];

void enqueue(struct apart_file_data data){
    queue[queue_tail] = data;
    queue_tail = (queue_tail + 1) % queue_capacity;
    queue_size++;
}

struct apart_file_data dequeue(){
    struct apart_file_data data = queue[queue_head];
    queue_head = (queue_head + 1) % queue_capacity;
    queue_size--;
    return data;
}


void producer_apart_file(struct stat file_inf_buffer, char* mmap, int fileIndex){

    int file_pages = (file_inf_buffer.st_size / file_apart_size);
    int page_size = 0;
    
    if(((double)file_inf_buffer.st_size/file_apart_size) > file_pages ) {
        file_pages++;
        page_size = file_inf_buffer.st_size % file_apart_size;
    }else{
        page_size = file_apart_size;
    }

    total_pages += file_pages;
    total_page_in_file[fileIndex] = file_pages;
    

    for (int page = 0; page < file_pages; page++) {
        pthread_mutex_lock(&lock);

        // Producer put the data to queue
        while(queue_size >= queue_capacity){
            pthread_cond_broadcast(&cond);
            pthread_cond_wait(&cond,&lock);
        }
        pthread_mutex_unlock(&lock);
        struct apart_file_data apart_file;
  
        apart_file.address = mmap;
        apart_file.file_index = fileIndex;
        apart_file.page = page;
        
        if(page==file_pages-1){ //Last page, might not be page-alligned
            apart_file.page_size=page_size;
        }
        else{
            apart_file.page_size=file_apart_size;
        }
        
       /* apart_file.page_size = (page != (file_pages - 1))? file_apart_size: page_size;*/
        mmap += file_apart_size;
        pthread_mutex_lock(&lock);
        enqueue(apart_file);
        pthread_mutex_unlock(&lock);
        pthread_cond_signal(&cond);
        
    }
    
}


// producer
void* producer(void *argv){
    char ** fileNames =  (char **)argv;
    for(int count = 0; count < total_count_files; count++){
        int file;
        if((file = open(fileNames[count], O_RDONLY)) < 0){
            printf("Error: File cannot open\n");
            exit(EXIT_FAILURE);
        }
        struct stat file_inf_buffer;
        
        if(fstat(file, &file_inf_buffer) != 0){
            printf("Error: Get the file status failed");
            close(file);
            exit(EXIT_FAILURE);
        }
        if(file_inf_buffer.st_size <= 0){
            continue;
        }
        
        char* map = mmap(NULL, file_inf_buffer.st_size, PROT_READ, MAP_SHARED, file, 0);
        
        if(map == MAP_FAILED){
            printf("Error: File mapping failed");
            close(file);
            exit(EXIT_FAILURE);
        }
        producer_apart_file(file_inf_buffer, map, count);
        close(file);
    }
    producer_complete = 1;
    pthread_cond_broadcast(&cond);
    return EXIT_SUCCESS;
}

int consumer_find_apartfile_index(struct apart_file_data apart_file){
    
    int index = 0;
    for(int i = 0; i < apart_file.file_index; i++){
        index += total_page_in_file[i];
    }
    
    index += apart_file.page;
    
    return index;
    
}

struct structured_file_data consumer_structure_data(struct apart_file_data file){
    
    struct structured_file_data struct_data;
    struct_data.count = malloc(file.page_size);
    char* cache_char = malloc(file.page_size);
    //
    int word_count = 0;
    
    for(int i = 0; i < file.page_size; i++){
        cache_char[word_count] = file.address[i];
        
        struct_data.count[word_count] = 1;
        
        while(i+1 < file.page_size && file.address[i] == file.address[i + 1]){
            struct_data.count[word_count]++;
            i++;
        }
        word_count++;
        
    }
    struct_data.word_count = word_count;
    struct_data.data = realloc(cache_char,word_count); // cache_char memory area to be reallocated to same on number of word.
    return struct_data;
    
}




void *consumer(){
    do{
        pthread_mutex_lock(&lock);
        while(queue_size == 0 && producer_complete == 0){
            pthread_cond_signal(&cond);
            pthread_cond_wait(&cond,&lock);

        }
        
        if(queue_size == 0 && producer_complete == 1){
            pthread_mutex_unlock(&lock);
            return EXIT_SUCCESS;
        }
        
        struct apart_file_data data = dequeue();
        
        if(producer_complete != 1){
            pthread_cond_signal(&cond);
        }
        pthread_mutex_unlock(&lock);
        
        structured_data[consumer_find_apartfile_index(data)] = consumer_structure_data(data);

        
    }while ((producer_complete == 0 && queue_size > 0));
    return EXIT_SUCCESS;
}

void printResult(){
    
    for(int i = 0;i < total_pages; i++){
        if(i<total_pages - 1){
            if(structured_data[i].data[structured_data[i].word_count - 1]==structured_data[i + 1].data[0]){ //Compare i'th output's last character with the i+1th output's first character
                
                structured_data[i+1].count[0] += structured_data[i].count[structured_data[i].word_count-1];
                
                structured_data[i].word_count--;
            }
        }
        for(int index = 0; index<structured_data[i].word_count; index++){
            int count = structured_data[i].count[index];
            char character = structured_data[i].data[index];
            fwrite(&count,sizeof(int),1,stdout);
            fwrite(&character,sizeof(char),1,stdout);
        }
    }

    /*for(int i = 1; i < total_pages; i++){
        if(i-1 < total_pages-1){

            if(structured_data[i - 1].data[structured_data[i - 1].word_count - 1] == structured_data[i].data[0]){
                
                structured_data[i].count[0] += structured_data[i - 1].count[structured_data[i - 1].word_count - 1];
                structured_data[i - 1].word_count--;
                
            }
        }
        
        for(int index = 0; index < structured_data[i - 1].word_count; index++){
            int count = structured_data[i-1].count[index];
            char character = structured_data[i-1].data[index];
            fwrite(&count, sizeof(int), 1, stdout);
            fwrite(&character, sizeof(char), 1, stdout);
            
        }
    }*/
}

void clean_memory(){
    free(total_page_in_file);
    free(structured_data);
}


int main(int argc, char* argv[])
{
    // Check the arguments
    if (argc < 2) {
            printf("pzip: file1 [file2 ...]\n");
            exit(EXIT_FAILURE);
    }
    total_count_files = argc - 1;
    int total_threads = get_nprocs(); //get_nprocs();
    total_page_in_file = malloc(sizeof(int) * total_count_files);
    structured_data = malloc(sizeof(struct structured_file_data) * 512000*2);
    
    pthread_t producerId, consumerId[total_threads];
    pthread_create(&producerId, NULL, producer, argv + 1);
    for(int index = 0; index < total_threads; index++){
        pthread_create(&consumerId[index], NULL, consumer, NULL);
    }
    for (int index = 0; index < total_threads; index++){
        pthread_join(consumerId[index], NULL);
    }
    pthread_join(producerId, NULL);
    printResult();
    clean_memory();
    pthread_exit(NULL);
    return 0;
}

