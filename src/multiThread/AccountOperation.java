package multiThread;



public class AccountOperation {

	Account account;
	Thread deposit;
	Thread withdraw;
	public AccountOperation() {
		// TODO Auto-generated constructor stub
		account = new Account();
		deposit = new Thread(new DepositTask(account),"Deposit");
		withdraw = new Thread(new WithdrawTask(account),"Withdraw");
		withdraw.start();
		deposit.start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountOperation operation = new AccountOperation();
	}

}
