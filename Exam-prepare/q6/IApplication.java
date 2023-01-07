/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public abstract class IApplication {
    private Application app;
    
    public IApplication(Application app){
        this.app = app;
    }
    
    public Application getApplication(){
        return app;
    }
    
}
