public abstract class Account{
    private String account_no;
    public Account(String account_no){
        this.account_no = account_no; 
    }

    public String getAccountNumber(){
        return account_no;
    }

    public abstract double getTotal(); 

    public String toString() 
	{
		return "Bank A/C Number: "+getAccountNumber();
	}
}