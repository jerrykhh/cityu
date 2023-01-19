//
//  836.cpp
//  OJAssignment
//
//  Created by Jerry Kwok on 12/3/2021.
//

#include <iostream>
#include <unordered_set>
using namespace std;

int main(){
    int n;
    cin >> n;
    unordered_set<int> list;
    
    while (n--) {
        int number;
        cin >> number;
        list.insert(number);
    }
    
    int find_number = 0;
    while(list.find(find_number) != list.end())
        find_number++;
    cout << find_number << endl;
    
}
