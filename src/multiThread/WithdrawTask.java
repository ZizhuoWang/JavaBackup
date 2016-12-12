package multiThread;

public class WithdrawTask implements Runnable {
	Account account;
	public WithdrawTask(Account a){
		account=a;
	}
	public void run() {
		while (true) {
			account.withdraw(100.0);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}