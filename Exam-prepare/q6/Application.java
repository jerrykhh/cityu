/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
class Application {
    private Student student;
    private Programme prog;
    private int status;

    public Application(Student student, Programme prog) {
        this.student = student;
        this.prog = prog;
        this.student.addApplication(new IApply(this));
        this.prog.addApplication(new IAdmit(this));
        status = 0;
    }
    
    public String getStatus(){
        if(status == 1)
            return "Offered";
        else if(status == 2)
            return "Accepted";
        
        return "Pending";
    }

    public Student getStudent(){
        return student;
    }
    
    public Programme getProgramme(){
        return prog;
    }
    
    public void offer(){
        status = 1;
    }
    
    public void accept(){
        status = 2;
    }
    
    
}
