/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */

public class Main { 

	public static void main(String [] args)	{	

		boolean result; 
		BooleanExp exp;
		
		Variable x = new Variable();
		Variable y = new Variable();
		
		// (true and x) or (y and (not x)) 
		exp = new Or(
				new And(new Constant(true), x),
				new And(y, new Not(x)));

		x.assign(false);
		y.assign(true);
		result = exp.evaluate();
		System.out.println(result); //Output [true]
		
		x.assign(false);
		y.assign(false);
		result = exp.evaluate(); 
		System.out.println(result); //Output [false]
		
		exp = new Not(new Or(new Or(x, y),
				new And(new Not(x), new Not(y))));
		x.assign(true);
		y.assign(false);
		result = exp.evaluate();
		System.out.println(result); //Output [false]
	}		
}

