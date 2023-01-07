/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Adult implements AgeGroup{

    @Override
    public void call(Type type) {
        System.out.println(type.getName());
    }
    
}
