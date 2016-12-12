package multiThread;

import java.util.ArrayList;

public class TestMyTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long begin = System.currentTimeMillis();
		TestMyTest test = new TestMyTest();
		MyTaskSleep taskSleep = new MyTaskSleep();
		MyTaskWait taskWait = new MyTaskWait();
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for(int i=0;i<10;i++){
			Thread thread = new Thread(taskSleep, "Thread"+i);
			threads.add(thread);
			thread.start();
		}
		
		for(int i=0;i<threads.size();i++){
			Thread thread = threads.get(i);
			try {
				thread.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Time: " +(System.currentTimeMillis()-begin));
	}

}
