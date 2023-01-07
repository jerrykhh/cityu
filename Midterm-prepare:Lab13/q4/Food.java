/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public abstract class Food {
    private String name;
    public Food(String name){
        this.name = name;
    }
    
    public String getName(){
        return name;
    }
        
}
