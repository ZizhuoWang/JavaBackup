package multiThread;

public class MyTaskSleep implements Runnable {

	@Override
	public synchronized void run() {
		// TODO Auto-generated method stub
		String name = Thread.currentThread().getName();
		System.out.println(name+" started");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(name+" ended");
	}

}
