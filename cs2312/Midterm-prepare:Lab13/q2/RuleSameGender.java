/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class RuleSameGender implements Rule{

    @Override
    public boolean matchRule(Student student, Student otherStudent) {
        if(student.getGender() == otherStudent.getGender()){
            return true;
        }
        return false;
    }


    
}
