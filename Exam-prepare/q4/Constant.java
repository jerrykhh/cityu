/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Constant extends BooleanExp{

    private Boolean oper;
    public Constant(boolean b) {
        this.oper = b;
    }

    @Override
    public boolean evaluate() {
        return oper;
    }
    
}
