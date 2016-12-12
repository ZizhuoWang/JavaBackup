package multiThread;

public class DepositTask implements Runnable {

	Account account;

	public DepositTask(Account a) {
		// TODO Auto-generated constructor stub
		account=a;
	}
	@Override
	public void run() {
		account.deposit(1200.0);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
