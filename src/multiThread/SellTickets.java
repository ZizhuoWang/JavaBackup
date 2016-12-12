package multiThread;



/**
 * @author wang
 * All things are done in the constructor...
 */
public class SellTickets {
	public SellTickets(int a) {
		
		//Create ten threads and start them
		Runnable sell = new Ticket(a);
		Thread t1 = new Thread(sell,"Salesman1");
		Thread t2 = new Thread(sell,"Salesman2");
		Thread t3 = new Thread(sell,"Salesman3");
		Thread t4 = new Thread(sell,"Salesman4");
		Thread t5 = new Thread(sell,"Salesman5");
		Thread t6 = new Thread(sell,"Salesman6");
		Thread t7 = new Thread(sell,"Salesman7");
		Thread t8 = new Thread(sell,"Salesman8");
		Thread t9 = new Thread(sell,"Salesman9");
		Thread t10 = new Thread(sell,"Salesman10");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
	}

	public static void main(String[] args) {
		SellTickets sellTickets = new SellTickets(200);
	}

}
