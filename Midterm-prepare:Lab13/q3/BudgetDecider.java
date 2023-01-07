/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class BudgetDecider implements Decider{

    @Override
    public String decider(String place) {
        String result = "Go " + place;
        if(place.toUpperCase().equals("HIKING")){
            return result + " anytime!";
        }else{
            return result + " if we have money.";
        }
    }
    
}
