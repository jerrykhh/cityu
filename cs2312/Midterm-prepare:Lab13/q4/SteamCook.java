/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class SteamCook implements Cook{

    public void cook(Food food){
        System.out.println("steamed " + food.getName());
    }
}
