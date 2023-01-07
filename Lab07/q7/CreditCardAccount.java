public class CreditCardAccount extends Account {
    private double balance;
    private double creditLimit;

    public CreditCardAccount(String ano, double bal, double climit){
        super(ano);
        balance = bal;
        creditLimit = climit;
    }

    public double getTotal(){
        return balance;
    }

    public String toString(){
        return "Credit Card A/C Number: "+super.getAccountNumber()+
        " Balance: $"+String.format("%.2f",balance)+
        " Credit limit: $"+String.format("%.2f",creditLimit);

    }
}
