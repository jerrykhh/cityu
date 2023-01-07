/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Q3 {
    public static void main(String[] args){
        Decider d1 = new WeatherDecider();
        Decider d2 = new BudgetDecider();
        goHiking(d1);
        goHiking(d2);
        goShopping(d1);
        goShopping(d2);
    }
    
    private static void goHiking(Decider d){
        System.out.println(d.decider("hiking"));
    }
    
    private static void goShopping(Decider d){
        System.out.println(d.decider("shopping"));
    }
    
    
    
}
