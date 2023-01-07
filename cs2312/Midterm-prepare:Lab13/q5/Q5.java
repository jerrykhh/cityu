/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Q5 {
    public static void main(String[] args){
        Sentence[] s = new Sentence[3];
        s[0] = new Do(new Mi(new Sentence()));
        s[1] = new Do(new Mi(new Do(new Mi( new Sentence()))));
        s[2] = new Re(new Mi(new Fa(new Fa(new Mi(new Re(new Fa(new Sentence())))))));
        
        for(int i = 0; i < 3; i++){
            s[i].print();
        }
    }
}
