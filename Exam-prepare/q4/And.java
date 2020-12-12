/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class And extends BooleanExp{

    private BooleanExp oper1;
    private BooleanExp oper2;

    public And(BooleanExp oper1, BooleanExp oper2) {
        this.oper1 = oper1;
        this.oper2 = oper2;
    }

    @Override
    public boolean evaluate() {
        return oper1.evaluate() && oper2.evaluate();
    }
    
}
