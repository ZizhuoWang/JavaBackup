package network;
import java.io.*;
import java.net.*;
public class Server {

	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(8088);
		System.out.println("The server is running");
		Socket socket = server.accept();
		try{			
			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
			while(true){
				String str = in.readLine();
				if (str!=null && str.equals("Hello")) out.println("Hello, this is the server!");
				else out.println("What did you just say to me?");

				socket = server.accept();
			}
		}catch(Exception e){
			e.printStackTrace();		
		}finally{
			socket.close();
			server.close();
		}
	}
}