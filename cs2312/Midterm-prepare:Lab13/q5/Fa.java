/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Fa extends Sentence{
    
    private Sentence sentence;
    
    public Fa(Sentence sentence){
        super();
        this.sentence = sentence;
    }
    
    public void print(){
        System.out.print("fa ");
        sentence.print();
    }
}
