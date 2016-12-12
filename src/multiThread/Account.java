package multiThread;

public class Account {

	Double balance=0.0;
	
	synchronized void withdraw(Double amount){
		System.out.println("Entered withdraw");
		try {
			while(balance<1000.0){
				System.out.println(Thread.currentThread().getName()+" is waiting");
				notifyAll();
				System.out.println("Notified1");
				wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		balance-=amount;
		System.out.println(Thread.currentThread().getName()+" withdrew "+amount);
		
	}
	
	synchronized void deposit(Double amount){
		System.out.println("Entered deposit");
		balance+=amount;
		try {
			if (balance>1000.0) {
				notifyAll();
				System.out.println("Notified2");
				wait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
