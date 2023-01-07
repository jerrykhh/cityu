/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Not extends BooleanExp{
    private BooleanExp oper1;

    public Not(BooleanExp oper1) {
        this.oper1 = oper1;
    }

    @Override
    public boolean evaluate() {
        return !oper1.evaluate();
    }
    
    
}
