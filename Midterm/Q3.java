
import java.util.ArrayList;
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
    public static void main(String[] args)
    {
        Step s1 = new Step("Step 1");
        s1.appendStep(new Step("Step 2"));
        s1.appendStep(new Step("Step 3"));
        s1.print(); //output [Step 1 => Step 2 => Step 3]        

        s1.appendStep(new Step("Step 4"));
        s1.print(); //output [Step 1 => Step 2 => Step 3 => Step 4]

        Step s2 = new Step("Buy Tomatoes");
        s2.appendStep(new Step("Wash"));
        s2.appendStep(new Step("Cook"));
        s2.appendStep(new Step("Eat"));
        s2.print(); //output [Buy Tomatoes => Wash => Cook => Eat]
        
        s1.appendStep(s2);
        s1.print(); //output [Step 1 => Step 2 => Step 3 => Step 4 => Buy Tomatoes => Wash => Cook => Eat]
    }
}

class Step { 
    private String stepStr;
    private ArrayList<Step> steps;
    
    public Step(String stepStr){
        this.stepStr = stepStr;
        steps = new ArrayList();
        steps.add(this);
    }
    
    public ArrayList<Step> getSteps(){
        return steps;
    }
    
    public void appendStep(Step step){
        if(step.getSteps().size() > 1){
            steps.addAll(step.getSteps());
            return ;
        }
        steps.add(step);
        
    }
    
    public String getStepStr(){
        return stepStr;
    }
    
    public void print(){
        String result = "";
        int count = 0;
        for(Step step: steps){
            result += step.getStepStr();
            if(count != steps.size()-1)
                result += " => ";
            
            count++;
        }
        System.out.println(result);
    }


}