package network;

import java.net.InetAddress;
import java.util.ArrayList;

public class localhost {
	
	public localhost() {
		
	}

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			String localIP = inetAddress.getHostAddress().toString();
			String name = inetAddress.getHostName().toString();
			
			System.out.println(localIP+"\n"+name);
			
			ArrayList<String> all = new ArrayList<String>();
			String hostname = InetAddress.getLocalHost().getHostName();
			
			if (hostname.length()>0) {
				InetAddress[] addresses = InetAddress.getAllByName(hostname);
				for (int i = 0; i < addresses.length; i++) {
					System.out.println(addresses[i].getHostAddress().toString());
					all.add(addresses[i].getHostAddress().toString());
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
