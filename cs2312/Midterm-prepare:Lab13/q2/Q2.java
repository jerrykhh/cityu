/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Q2 {

    public static void main(String[] args) {

        Student a = new Student("Ada", 'F', "CS"); //name, gender, programme
        Student b = new Student("Billy", 'M', "Math");
        Student c = new Student("Candy", 'F', "CS");
        Student d = new Student("Daisy", 'F', "CS");
        Student e = new Student("Emily", 'F', "Math");

        Rule rG = new RuleSameGender();
        Rule rBk = new RuleSimilarBackground();

        a.listTargets(); //Output [Ada: Billy Candy Daisy Emily] 
        b.listTargets(); //Output [Billy: Ada Candy Daisy Emily]

        a.addRule(rG);
        a.listTargets(); //Output [Ada: Candy Daisy Emily]
        a.addRule(rBk);
        a.listTargets(); //Output [Ada: Candy Daisy] 

        Student f = new Student("Fanny", 'F', "CS");
        a.listTargets(); //Output [Ada: Candy Daisy Fanny]
        f.listTargets(); //Output [Fanny: Ada Billy Candy Daisy Emily]	
    }
}
