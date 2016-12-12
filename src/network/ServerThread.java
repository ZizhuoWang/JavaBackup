package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread{
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	public ServerThread(Socket socket) {
		ServerDaemon.CURRENT_THREADS++;
		this.socket=socket;
		try {
			in= new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			while(true){
				String string = in.readLine();
				if(string!=null){
					if(string.equals("bye")){
						break;
					}
					else{
						String greeting = "Hello "+string+", I am server";
						out.println(greeting);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				socket.close();
				ServerDaemon.CURRENT_THREADS--;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}










