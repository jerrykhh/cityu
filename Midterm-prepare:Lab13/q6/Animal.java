/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Animal {
    private AgeGroup group;
    private Type type;
    
    public Animal(Type type, AgeGroup group){
        this.type = type;
        this.group = group;
    }
    
    public void call(){
        group.call(type);
    }
}
