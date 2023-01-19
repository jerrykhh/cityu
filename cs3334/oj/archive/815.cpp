//
//  815.cpp
//  OJAssignment
//
//  Created by Jerry Kwok on 11/3/2021.
//

#include <iostream>
#include <unordered_set>
using namespace std;


int main() {
    int n, number,find_number;
    unordered_set<int> s;
    cin >> n;
    s.insert(0);
    s.insert(n+1);
    
    while(n--){
        cin >> number;
        
        find_number = number - 1;
        while(s.end() == s.find(find_number))
            find_number--;
        cout << find_number;
        
        find_number = number + 1;
        while(s.end() == s.find(find_number))
            find_number++;
        cout << " " << find_number << endl;
        s.insert(number);
    }
}
