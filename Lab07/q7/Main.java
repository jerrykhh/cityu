import java.util.*;
import java.io.*;

public class Main{

	//Add the method called findAccount

	private static Account findAccount(ArrayList<Account> list, String account_no){
        Account result = null;
        for(Account account: list){
            if(account.getAccountNumber().equals(account_no)){
                result = account;
            }
        }
        return result;
    }




	public static void main(String [] args) throws FileNotFoundException
	{

		Scanner in = new Scanner(System.in);

		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();

		ArrayList<Account> acs = new ArrayList<>();

		Scanner infile = new Scanner(new File(filepathname));

		while(infile.hasNext()){
            String accountNo = infile.next();
            Account account = null;
            if(accountNo.charAt(0) <= '5'){
                account = new SavingsAccount(accountNo, infile.nextDouble());
            }else if(accountNo.charAt(0) >= '6' && accountNo.charAt(0) <= '8'){
                account = new CreditCardAccount(accountNo, infile.nextDouble(), infile.nextDouble());
            }else if(accountNo.charAt(0) == '9'){
                SavingsAccount savingAccount = (SavingsAccount) findAccount(acs, infile.next());
                CreditCardAccount creditCardAccount = (CreditCardAccount) findAccount(acs, infile.next());
                account = new PowerAdvantageAccount(accountNo, savingAccount, creditCardAccount);
            }
            acs.add(account);
        }





		infile.close();
		System.out.print("\nInput an account number to search: ");
    String ac = in.next();
    Account a = findAccount(acs, ac);

		if (a != null)
			System.out.println("\n[Result]\n"+ a );
		else
			System.out.println("\n[Result]\nThe account is not found.");

		in.close();
	}
}
