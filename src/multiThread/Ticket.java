package multiThread;

import java.util.ArrayList;

/**
 * @author wang
 * @see Runnable
 * 
 */
public class Ticket implements Runnable {
	public int amount;//The amount of tickets
	public static int sold=1;//The amount of tickets already been sold
	/**
	 * @param a
	 * Set the amount of tickets to a
	 */
	public Ticket(int a) {
		amount = a;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public synchronized void run() {
		//Get the name of the thread
		String name = Thread.currentThread().getName();
		
		//This condition is used to print out sold out information
		while (sold <= amount+1) {
			//If there are still some tickets left
			if (sold <= amount) {
				System.out.println(name + " is selling ticket No." + sold);
				sold++;
				int time = (int)Math.random()*5+1;//Get a random number
				try {
					wait(time);//Let other salesmen sell tickets
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("All tickets are sold out!");
				sold++;
			} 
		}
	}

}
