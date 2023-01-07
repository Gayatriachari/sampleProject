import java.util.Scanner;

//user defined exception to generate error message if the user enters wrong credentials
class InvalidCredentialsException extends Exception{
	private static final long serialVersionUID = 1L;

	InvalidCredentialsException(String a){
		super(a);
	}
} 

//class to check the balance in the account
class CheckBalance{
	public void currentBalance(int currentBalance) {
		System.out.println("your account balance is "+currentBalance);
		System.out.println("Thankyou for using our application.");
	}
} 

//class to perform deposit operation on the account
class Deposit{ 
	public int CurrentBalanceAndDepositedMoney(int currentBalance, int depositMoney) {
		System.out.println("successfully "+depositMoney+" was Deposited to your account"); 
		return currentBalance+depositMoney;
	}
} 

//class to perform the withdraw operation on the account
class WithDraw{ 
	public int checkingBalanceToWithdraw(int currentBalance,int withDrawMoney) {
		if(currentBalance>=withDrawMoney) { 
			System.out.println("successfully "+withDrawMoney+" was withdrawn from your account");
			return currentBalance-withDrawMoney;
		}
		else { 
			System.out.println("U dont have enough balance to withdraw, make sure "
					+ "your current Balance is greater than withDrawMoney");
		} 
	return currentBalance;
	}
} 

public class BankingApplication {
      
    static Scanner scan=new Scanner(System.in); 
   
    public void performingBankingApplicatio() {
    	int currentBalance=20000;  
		String option="yes";
		while(option.equalsIgnoreCase("yes")) {
			System.out.println("enter whether u want to check your balance r deposit r withdraw or exit ");
			String value=scan.next();
		switch(value){
		    case "check": 
		    	new CheckBalance().currentBalance(currentBalance); 
		    	break;
		    	
		    case "deposit": 
		    	System.out.println("enter how much money u want to deposit"); 
		    	int depositMoney=scan.nextInt();
		    	currentBalance=new Deposit().CurrentBalanceAndDepositedMoney(currentBalance, depositMoney);
		    	System.out.println("now your balance after deposit is "+currentBalance);
		    	break; 
		    	
		    case "withdraw":
		    	System.out.println("enter how much money you want to withdraw"); 
		    	int withDrawMoney=scan.nextInt(); 
		    	int ModifiedCurrentBalance=new WithDraw().checkingBalanceToWithdraw(currentBalance, withDrawMoney); 
		    	if(ModifiedCurrentBalance<currentBalance) {
		    	       System.out.println("now your balance after withdraw is "+ModifiedCurrentBalance);
		    	}
		    	else {
		    		System.out.println("your current balance is "+ModifiedCurrentBalance);
		    	}
		    	break; 
		    case "exit":
		    	System.out.println("Successufully loged out from the application");
		    	System.exit(0);
		    default:
		    	System.out.println("enter proper value to work on your account :(");
		} 
		System.out.println("enter yes if u wnt to use the application");
    	option=scan.next();
		}
	
    }
    public static void checkingCredentials() throws InvalidCredentialsException {
    	final String Name="12345@upi"; 
		final String pwd="12345";
		
		System.out.println("enter userName"); 
		String userName=scan.next();
		System.out.println("enter password"); 
		String password=scan.next();
		if(Name.equals(userName) && pwd.equals(password)) {
			new BankingApplication().performingBankingApplicatio();
		}else {
			throw new InvalidCredentialsException("temperarly your account blocked, you entered wrong credentils too many times ");
		}
    }
	public static void main(String[] args) throws InterruptedException { 
		System.out.println("welcome...! please enter your login details to access our services "); 
		Thread.currentThread().sleep(1000); 
		
		    try {
			    BankingApplication.checkingCredentials();
		    }catch(Exception e) {
			       try {
			    	   System.out.println("credentials mismatched please enter again");
			    	   Thread.currentThread().sleep(1000);
			    	   BankingApplication.checkingCredentials();
			       }catch(Exception e1) {
			    	   try {
			    		   System.out.println("credentials mismatched please enter again");
			    		   Thread.currentThread().sleep(1000);
			    		   BankingApplication.checkingCredentials();
			    	   }catch(Exception e2) {
			    		   System.out.println(e2.getMessage());
			    	   }
			       }
		    }
	    
	}
}
