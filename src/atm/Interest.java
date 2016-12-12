package atm;

public class Interest implements Runnable {
	ATM temp;
	double rate;
	public Interest(ATM atm,double r) {
		// TODO Auto-generated constructor stub
		temp = atm;
		rate=r;
	}
	@Override
	public void run() {
		while (true) {
			// TODO Auto-generated method stub
			temp.addInterest(rate);
			try {
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}

}
