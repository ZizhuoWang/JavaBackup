package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Ping {
	private static final int TIMEOUT = 5000;
	public Ping(InetAddress addr) {
		try {
			String hostName = addr.getHostName();
			while(true){
				long t1 = System.currentTimeMillis();
				if (addr.isReachable(TIMEOUT)) {
					long t2 = System.currentTimeMillis();
					System.out.println(hostName+" "+(t2-t1)+"ms");
				}
				Thread.sleep(100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		try {
			InetAddress address = InetAddress.getByName("58.192.114.194");
			Ping ping = new Ping(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
}
