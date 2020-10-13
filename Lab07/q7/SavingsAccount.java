public class SavingsAccount  extends Account{

    private double balance;

    public SavingsAccount(String ano, double bal){
        super(ano);
        this.balance = bal;
    }
    public double getTotal(){
        return balance;
    }

    public String toString(){
        return "Savings A/C Number: "+super.getAccountNumber()+
				" Balance: $"+String.format("%.2f",getTotal());
    }
}
