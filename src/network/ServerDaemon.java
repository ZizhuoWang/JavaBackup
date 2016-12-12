package network;

import java.net.ServerSocket;

public class ServerDaemon {
	public static final int PORT = 8080;
	private static final int MAX_THREADS = 10;
	public static int CURRENT_THREADS = 0;
	ServerSocket server;
	
	public ServerDaemon() {
		System.out.println("Server started.");
		try {
			server = new ServerSocket(PORT);
			while(true){
				if(CURRENT_THREADS<MAX_THREADS){
					ServerThread thread = new ServerThread(server.accept());
					thread.start();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				server.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ServerDaemon daemon = new ServerDaemon();
	}
}
