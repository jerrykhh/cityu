public class PowerAdvantageAccount extends Account {
    private SavingsAccount savingsAC;
    private CreditCardAccount creditCardAC;

    public PowerAdvantageAccount(String ano, SavingsAccount sa, CreditCardAccount cr) {
        super(ano);
        savingsAC = sa;
        creditCardAC = cr;
    }

    public double getTotal() {
        return savingsAC.getTotal() - creditCardAC.getTotal();
    }

    public String toString() {
        return "Power Advantage A/C Number: " + super.getAccountNumber() + " Balance: $" + String.format("%.2f", getTotal()) + "\n 1."
                + savingsAC + "\n 2." + creditCardAC;
    }
}
