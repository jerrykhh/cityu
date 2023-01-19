//
//  835.cpp
//  OJAssignment
//
//  Created by Jerry Kwok on 12/3/2021.
//

#include <iostream>
using namespace std;

struct Wall{
    long long int x;
    long long int y;
    long long int r;
};

struct Point{
    long long int a;
    long long int b;
    long long int c;
    long long int d;
};

long long int cal_point_in_circle(long long int x, long long int y, long long int h, long long int k){
    return (x - h) * (x - h) + (y - k) * (y - k);
}


int main(){
    long long int n;
    cin >> n;
    Wall w[n];
    long long int count = 0;
    while(count < n){
        cin >> w[count].x >> w[count].y >> w[count].r;
        count++;
    }
    
    long long int q;
    cin >> q;
    Point p[q];
    count = 0;
    while(count < q){
        cin >> p[count].a >> p[count].b >> p[count].c >> p[count].d;
        count++;
    }
    
    for(long long int i = 0; i < q; i++){
        long long int times = 0;
        for(long long int j = 0; j < n; j++){
            long long int r = w[j].r * w[j].r;
            if((r > cal_point_in_circle(p[i].a, p[i].b, w[j].x, w[j].y) &&
                 r < cal_point_in_circle(p[i].c, p[i].d, w[j].x, w[j].y)) ||
               (r < cal_point_in_circle(p[i].a, p[i].b, w[j].x, w[j].y) &&
                r > cal_point_in_circle(p[i].c, p[i].d, w[j].x, w[j].y))){
                times++;
            }
        }
        cout << times << endl;
    }
}

