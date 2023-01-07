/*
 * NOTE TO STUDENTS: Before you do anything else, please
 * provide your group information here.
 *
 * Group No.: 51 (Join a project group in Canvas)
 * First member's full name: Li Cheuk Yin
 * First member's email address: cyli79-c@my.cityu.edu.hk
 * Second member's full name: LI Hongzhou
 * Second member's email address: hongzholi2-c@my.cityu.edu.hk
 * Third member's full name: Kwok Ho Hin
 * Third member's email address: hohinkwok4-c@my.cityu.edu.hk
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
#include <dirent.h>
#define queue_capacity 10           // capacity of queue size
#define file_apart_size 10000000    // each apart file size

int total_count_files = 0;          // Number of files
int total_argc = 0;                 // Number of input files
int total_pages = 0;                // Number of pages in All files
int* total_page_in_file;            // Each file has number of pages
char ** fileNames;                  // files array
//Threads things
int producer_complete = 0;                                 // 0 is false, 1 is true
pthread_mutex_t lock = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t cond = PTHREAD_COND_INITIALIZER, wait = PTHREAD_COND_INITIALIZER;;

// output data
struct structured_file_data {
    char* data;          // Compressed char
    int* count;          // Count ot Compressed char
    int word_count;      // Count of different char for each separated file
}*structured_data;


struct apart_file_data {
    char* address;  // File Memory mapping addres
    int file_index; // File No.
    int page;       // Count of separated the page number
    int page_size;  // separate the file page
}queue[queue_capacity];



// Queue
int queue_size = 0; // pointer the queue size
int queue_head = 0; // pointer queue_head index
int queue_tail = 0; // pointer queue_tail index

// Put the separate data to queue let the consumer to process
void enqueue(struct apart_file_data data){
    queue[queue_tail] = data;                       // put the data in the queue tail
    queue_tail = (queue_tail + 1) % queue_capacity; // calcuate the tail index
    queue_size++;                                   // data in queue size + 1
}

// Consumer dequeue the data in queue to process
struct apart_file_data dequeue(){
    struct apart_file_data data = queue[queue_head];    // get the head data in queue head
    queue_head = (queue_head + 1) % queue_capacity;     // recalcuate the queue head index
    queue_size--;                                       // Due to data is dequeue so reduce 1 in queue size
    return data;                                        // return the data
}



// producer_apart_file use to separate the file data to sub file
void producer_apart_file(struct stat file_inf_buffer, char* mmap, int fileIndex){
    
    // Based on the defined file apart size to calcaute the file can separate
    // how many sub-files
    int file_pages = (file_inf_buffer.st_size / file_apart_size);
    // decare the variable and set the default value is 0
    int page_size = 0;
    // Check the parameter file whether more that file_apart_size
    // Due to int / int -> get integer. if the file file_inf_buffer.st_size/file_apart_size result have the
    // decimal places, that mean the file_pages need more 1 to process extra value
    if(((double)file_inf_buffer.st_size/file_apart_size) > file_pages ) {
        file_pages++;
        // Get the last page size
        page_size = file_inf_buffer.st_size % file_apart_size;
    }else{
        // if the file_inf_buffer.st_size/file_apart_size is small or equal the file_pages
        // that mean the page can handle it
        page_size = file_apart_size;
    }
    
    // Count of total separated file page
    total_pages += file_pages;
    // Store the each file have how many separated file page
    // fileIndex -> filePathIndex in looping
    total_page_in_file[fileIndex] = file_pages;
    
    // Base on how many file page to looping, and put the data to queue
    // and let the consumer to process data
    for (int page = 0; page < file_pages; page++) {
        
        // Lock that prevent threads contention to error
        pthread_mutex_lock(&lock);
        
        // Producer put the data to queue after
        // Need the consumer to process when queue size is full cannot enqueue more data
        while(queue_size >= queue_capacity){
            pthread_cond_broadcast(&cond);
            //Block the current thread, waiting for other threads to use
            pthread_cond_wait(&wait,&lock);
        }
        pthread_mutex_unlock(&lock);
        struct apart_file_data apart_file;
        // set the separated file infomration to apart_file_data struct
        apart_file.address = mmap;
        apart_file.file_index = fileIndex;
        apart_file.page = page;
        // Check the separated file whether equal last page
        // if yes the page is last page will store the last page file size
        apart_file.page_size = (page != (file_pages - 1))? file_apart_size: page_size;
        //Next page in memory
        mmap += file_apart_size;
        // Lock that prevent threads contention to error
        pthread_mutex_lock(&lock);
        enqueue(apart_file);
        // Enqueue the data to coumer to process
        pthread_mutex_unlock(&lock);
        // Due to enqueueed data in queue (Have data now), Wake-up all sleeping comsumer to compress
        pthread_cond_signal(&cond);
    }
}


// producer
void* producer(void *argv){
    // Array of all files Name
    fileNames =  (char **)argv;
    
    // Read all files to prepare separate to sub files and compress
    for(int count = 0; count < total_count_files; count++){
        int file;
        // printf("fileName: %s\n", fileNames[count]);
        // Read the file via fileNames[index] variable is stored the path of file, if cannot read will print error and exit
        if((file = open(fileNames[count], O_RDONLY)) < 0){
            printf("Error: File cannot open\n");
            exit(EXIT_FAILURE);
        }
        struct stat file_inf_buffer;
        // Get the file information like the file size and so on
        // If cannot get the information that may not have some error for files
        if(fstat(file, &file_inf_buffer) != 0){
            printf("Error: Get the file status failed");
            // close the file
            close(file);
            exit(EXIT_FAILURE);
        }
        // If the file is empty will store the producer_complete equal 1 to let the consumer know the producer is end
        if(file_inf_buffer.st_size <= 0){
            producer_complete = 1;
            continue;
        }
        // Memory map the file
        // PROT_READ -> The mapped area can be read
        // MAP_SHARED -> Allow other itineraries that map the file to share
        char* map = mmap(NULL, file_inf_buffer.st_size, PROT_READ, MAP_SHARED, file, 0);
        // Check the possibly due to no memory or some other case will return fail
        if(map == MAP_FAILED){
            printf("Error: File mapping failed");
            close(file);
            exit(EXIT_FAILURE);
        }
        // call the producer_apart_file method to separete the file
        producer_apart_file(file_inf_buffer, map, count);
        // Close the file
        close(file);
    }
    // Finsh the loop that mean producer is done, so let the consumer know
    producer_complete = 1;
    // Call all sleeping consumer threads to process compress
    pthread_cond_broadcast(&cond);
    return EXIT_SUCCESS;
}

//Let the consumer find the separated file index
int consumer_find_apartfile_index(struct apart_file_data apart_file){
    int index = 0;
    // Find the file current position of the apart_file_data struct.
    for(int i = 0; i < apart_file.file_index; i++){
        index += total_page_in_file[i];
        // total_page_in_file is array to stored the file page
    }
    //Previous page is added in index variable, so add received parameter apart_file_data page will get the index in current
    index += apart_file.page;
    return index;
}

// Consumer start to compress the separated file and return the result
struct structured_file_data consumer_structure_data(struct apart_file_data file){
    //This structe is use to store the result for compress
    struct structured_file_data struct_data;
    // Set the dynamily memory for struct_data and cache_char
    struct_data.count = malloc(file.page_size);
    // cache_char is use to store the got the char in separated file
    char* cache_char = malloc(file.page_size);
    // init the variable of word_count to count the word
    int word_count = 0;
    // Based on the Page size to loop each word
    for(int i = 0; i < file.page_size; i++){
        // store the char in array
        cache_char[word_count] = file.address[i];
        // set the default count is 1
        struct_data.count[word_count] = 1;
        // That loop is use to found same char in the file.address
        // i+1 < file.page_size  avoid the array is ArrayIndexOutOf
        while(i+1 < file.page_size && file.address[i] == file.address[i + 1]){
            //page_size index+1 and same char+1 if the conduction is true
            struct_data.count[word_count]++;
            i++;
        }
        // if loop stop that mean the char is not same so move to other
        // index to store the value in cache_char
        word_count++;
    }
    // result will stored in structured_file_data struct varaible
    struct_data.word_count = word_count;
    // cache_char memory area to be reallocated to same on number of word.
    struct_data.data = realloc(cache_char,word_count);
    // return the result
    return struct_data;
    
}

//Consumer compress method consumer_structure_data will called
void *consumer(){
    do{
        // Lock that prevent threads contention to error
        pthread_mutex_lock(&lock);
        // check the queue size is 0 and producer is not complete
        while(queue_size == 0 && producer_complete == 0){
            pthread_cond_signal(&wait);
            pthread_cond_wait(&cond,&lock);
            // Call the producer need to enqueue the data
        }
        
        // check the queue_size is 0 and confirm the producer is finish the process
        // If ture that will EXIT_SUCCESS
        if(queue_size == 0 && producer_complete == 1){
            pthread_mutex_unlock(&lock);
            return EXIT_SUCCESS;
        }
        
        // Get the sparated data in queue
        struct apart_file_data data = dequeue();
        
        if(producer_complete != 1){
            pthread_cond_signal(&wait);
        }
        pthread_mutex_unlock(&lock);
        
        // consumer_structure_data method is use to calcuate the char in separated files
        // and store the return value in structured_data value as well as the index
        // is based on  consumer_find_apartfile_index method return output position value
        structured_data[consumer_find_apartfile_index(data)] = consumer_structure_data(data);
    
    // Check the whether is not producer_complete or queue_size size bigger that 0
    // that mean the consumer is need to keep running
    }while ((producer_complete == 0 || queue_size > 0));
    
    return EXIT_SUCCESS;
}

// restructure the input argv
char** dir_checking(char** argv){
    // set the dir varaible to open dir after
    DIR * dir;
    // set the dir_struct to store the directory information
    struct dirent *dir_struct;
    // This is a restructured result
    char ** structed_fileNames = calloc(total_argc * 100, sizeof(char *));
    // start the input argv index is 0
    int i = 0;
    // check the current index whether small that total count of input file path
    while(i < total_argc){
        // All file will try to open the directory
        dir = opendir(argv[i]);
        // If the file is not the Directory that return NULL
        if(dir == NULL){
            // If is not the directory put the value to structed_fileNames array only
            // Not nedd other modify or change
            *(structed_fileNames+total_count_files) = argv[i];
            //Calcuate the total files
            total_count_files++;
            
        }else{
            // If the input path is directory
            // read the directory file and store the information to dirent struct object
            while ((dir_struct = readdir(dir)) != NULL) {
                // get the file name via dir_struct->d_name
                char* pathString = dir_struct->d_name;
                // dir_filePath varaiable use the dynamic memory for file path in directory
                //pathString is the file name and the argv[i] is the path string
                // that mean 7-0-1.in + test/test-cases/7/7-0
                char* dir_filePath = malloc(strlen(pathString) + strlen(argv[i]) + 1);
                
                // Due to the dir_struct->d_name will get the result of . and ..
                // This is not we need so skip it
                if(pathString[0] == '.'){
                    continue;
                }
                // Copy the argb[i](E.g. test/test-cases/7/7-0)  string to dir_filePath
                strcpy(dir_filePath, argv[i]);
                // Copy '/' to dir_filePath
                // dir_filePath -> test/test-cases/7/7-0/
                strcat(dir_filePath, "/");
                // Copy pathString string to dir_filePath
                // dir_filePath -> test/test-cases/7/7-0/7.0.0.in
                strcat(dir_filePath, pathString);
                // Change the structed_fileNames Size based on dir_filePath of string length
                // total_count_files mean current have how many files
                structed_fileNames[total_count_files] = malloc(strlen(dir_filePath));
                // copy the data to structed_fileNames
                memcpy(structed_fileNames[total_count_files], dir_filePath, strlen(dir_filePath) + 1);
                // add one file to array so total_count_files + 1
                total_count_files++;
                
            }
            // close the directory
            closedir(dir);
        }
        // currect looping input path index
        i++;
    }
    // return restructured array
    return structed_fileNames;
}

// print the result of compressed value
void printResult(){
    // loop separated files all page -> total_pages
    // i is the page
    for(int i = 0;i < total_pages; i++){
        // Check the i is not the last page
        if(i < total_pages - 1){
            // check current structured_data  data whether equal to next position structured_data array data
            if(structured_data[i].data[structured_data[i].word_count - 1]==structured_data[i + 1].data[0]){
                // if their is same char, the program will add current word count data to next structured_data array word count data
                structured_data[i + 1].count[0] += structured_data[i].count[structured_data[i].word_count - 1];
                // Also, skip last char /n
                structured_data[i].word_count--;
            }
        }
        // loop all result
        for(int index = 0; index<structured_data[i].word_count; index++){
            // get the output result in structured_data count and data
            int count = structured_data[i].count[index];
            char character = structured_data[i].data[index];
            // print the result based on sizeOf(int) nad sizeOf(char)
            fwrite(&count,sizeof(int),1,stdout);
            fwrite(&character,sizeof(char),1,stdout);
        }
    }

}

// Main
int main(int argc, char* argv[])
{
    // Check the arguments
    // If not match the command requirement will exit and print the error message
    if (argc < 2) {
            printf("pzip: file1 [file2 ...]\n");
            exit(EXIT_FAILURE);
    }
    // Get the total input path in pzip Path1 [Path2...]
    total_argc = argc - 1;
    // Get all available threads in the computer
    int total_threads = get_nprocs(); //get_nprocs();
    // set the dynamic memory for total_page_in_file and structured_data two gloable varialbe
    total_page_in_file = malloc(sizeof(int) * total_count_files);
    structured_data = malloc(sizeof(struct structured_file_data) * 512000*2);
    // get the restructured file names via dir_checking() method
    char ** filenames = dir_checking(argv + 1);
    // pthread_t object
    pthread_t producerId, consumerId[total_threads];
    // Create the producer and pass the restructured file name array
    pthread_create(&producerId, NULL, producer, filenames);
    // Create the consumer and baesd on the get_nprocs() method got the total threads
    for(int index = 0; index < total_threads; index++){
        pthread_create(&consumerId[index], NULL, consumer, NULL);
    }
    // Join all consumer threads
    for (int index = 0; index < total_threads; index++){
        pthread_join(consumerId[index], NULL);
    }
    // Join producer threads
    pthread_join(producerId, NULL);
    // Print the result of compressed value
    printResult();
    // Exit the thread
    pthread_exit(NULL);
    return 0;
}
