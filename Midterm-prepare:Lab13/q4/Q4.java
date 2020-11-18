/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Q4 {
    public static void main(String[] args){
        Cook c1 = new SteamCook();
        Cook c2 = new CurryCook();
        c1.cook(new Fish());
        c2.cook(new Chicken());
        c1.cook(new Chicken());
        c2.cook(new Fish());
    }
}
