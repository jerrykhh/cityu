/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Baby implements AgeGroup {

    @Override
    public void call(Type type) {
        if (type.getName().equals("Dog")) {
            System.out.println("Puppy");
        } else if (type.getName().equals("Cat")) {
            System.out.println("Kitten");
        } else if (type.getName().equals("Rabbit")) {
            System.out.println("Bunny");
        }

    }

}
