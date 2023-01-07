/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public abstract class Type {
    private String name;
    
    public Type(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
}
