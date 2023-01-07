/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Mi extends Sentence{
    
    private Sentence se;
    
    public Mi (Sentence se){
        super();
        this.se = se;
    }
    
    public void print(){
        System.out.print("mi ");
        se.print();
    }
}
