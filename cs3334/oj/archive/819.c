//
//  819.c
//  OJAssignment
//
//  Created by Jerry Kwok on 10/3/2021.
//

#include <stdio.h>
#include <stdlib.h>

typedef struct Node{
    int data;
    struct Node* next;
}LinkedList;

int main(){
    LinkedList *head, *p, *s, *temp;
    int n, k;
    scanf("%d %d", &n, &k);
    head = (LinkedList*)malloc(sizeof(LinkedList));
    p = head;
    p->data = 1;
    p->next = p;
    for(int i = 2; i <= n; i++){
        s = (LinkedList*)malloc(sizeof(LinkedList));
        s->data = i;
        s->next = p->next;
        p->next = s;
        p = s;
    }
    
    p = head;
    while(n--){
        for(int i = 0; i < k - 2; i++)
            p = p->next;
        if(n == 0)
            printf("%d", p->next->data);
        else
            printf("%d ", p->next->data);
        temp = p->next;
        p->next = temp->next;
        free(temp);
        p = p->next;
    }
    printf("\n");
    
}

