/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Variable extends BooleanExp{
    private boolean oper;
    void assign(boolean b) {
        oper = b;
    }

    @Override
    public boolean evaluate() {
        return oper;
    }
    
}
