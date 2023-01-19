//
//  826.cpp
//  OJAssignment
//
//  Created by Jerry Kwok on 13/3/2021.
//

#include <iostream>
#include <queue>
using namespace std;

int main(){
    int memory_size, word_szie;
    int time_to_exter_dict = 0;
    queue<int> word_dict_queue;
    cin >> memory_size >> word_szie;
    for(int word_count = 0; word_count < word_szie; word_count++){
        int word;
        cin >> word;
        
        int i = 0;
        bool found = false;
        while(i < word_dict_queue.size()){
            int temp = word_dict_queue.front();
            if(temp == word)
                found = true;
                word_dict_queue.pop();
                word_dict_queue.push(temp);
                i++;
        }
            
        if(!found){
            time_to_exter_dict++;
            if(word_dict_queue.size() >= memory_size)
                word_dict_queue.pop();
            word_dict_queue.push(word);
        }

    }
    cout << time_to_exter_dict << endl;
    return 0;
}

