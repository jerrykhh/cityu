/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Staff {
    private String name;
    private Staff helper;
    private Job job;

    public Staff(String name) {
        this.name = name;
        job = null;
    }

    public void setHelper(Staff helper){
        this.helper = helper;
    }

    public void assignTask(Job job) {

        String worker = tryAssignTask(job);

        if (worker == null)
            System.out.println("No manpower");
        else
            System.out.println(worker + " will do " + job);
    }

    public void finishJob(){
        this.job = null;
        System.out.println(name + " is free now");
    }

    public String tryAssignTask(Job job){
        if(helper != null){
            String worker = helper.tryAssignTask(job);
            if(worker == null && this.job == null){
                this.job = job;
                return name;
            }else{
                return worker;
            }
        }else if(this.job == null){
            this.job = job;
            return name;
        }
        return null;
    }


}
