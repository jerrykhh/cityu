/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Q6 {
    public static void main(String[] args){
        Animal[] pets = new Animal[6];
        pets[0] = new Animal(new Dog(), new Baby());
        pets[1] = new Animal(new Dog(), new Adult());
        pets[2] = new Animal(new Cat(), new Baby());
        pets[3] = new Animal(new Cat(), new Adult());
        pets[4] = new Animal(new Rabbit(), new Baby());
        pets[5] = new Animal(new Rabbit(), new Adult());
        
        for(int i = 0; i < 6; i++){
            pets[i].call();
        }
    }
}
