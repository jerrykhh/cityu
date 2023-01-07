
import java.util.Scanner;
import java.util.InputMismatchException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jerrykwok
 */
public class Q2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            System.out.print("Initial amount ($): ");
            int amount = in.nextInt();
            MoneyShared m = new MoneyShared(amount);
            System.out.print("Divided by: ");
            int s = in.nextInt();
            if(s < 0)
                throw new NegativeExcp();
            if(amount % s != 0)
                throw new NotDivExcp();
            m.divide(s);

            System.out.println("Amount becomes: " + m);
        }catch(InputMismatchException e){
            System.out.println("Input mismatch!");
        }catch(NegativeExcp e){
            System.out.println(e.getMessage());
        } catch (NotDivExcp e) {
            System.out.println(e.getMessage());
        }
		
		

        in.close();
    }
}

class MoneyShared { 
    private int amount;

    public MoneyShared(int amount) throws NegativeExcp {
        if (amount < 0)
            throw new NegativeExcp();
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "$" + amount;
    }
    
    public void divide(int s){
        amount = amount/ s;
    }



} 

class NegativeExcp extends Exception {
    public NegativeExcp() { super("Negative number!"); }
    public NegativeExcp(String msg) { super(msg); }
}

class NotDivExcp extends Exception {
    public NotDivExcp() { super("Not divisible!"); }
    public NotDivExcp(String message) { super(message); }
}