/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class IAdmit extends IApplication{
    
    public IAdmit(Application app){
        super(app);
    }
    
    public void offer(){
        getApplication().offer();
    }
}
