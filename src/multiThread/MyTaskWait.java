package multiThread;

public class MyTaskWait implements Runnable {

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		String name = Thread.currentThread().getName();
		System.out.println(name + " started");
		
		try {
			wait(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(name+" ended");
	}
}
