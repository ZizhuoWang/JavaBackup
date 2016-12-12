package messager;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Server extends JFrame{
	
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;

	public Server() {
		super("Zizhuo Instant Messager");
		userText = new JTextField();
		userText.setEditable(false);
		userText.addActionListener(
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						sendMessage(e.getActionCommand());
						userText.setText("");
					}
				}
				);
		add(userText,BorderLayout.SOUTH);
		chatWindow = new JTextArea();
		add(new JScrollPane(chatWindow));
		setSize(500,500);
		setVisible(true);
	}
	
	public void startRunning(){
		try {
			server = new ServerSocket(6789,100);
			while (true) {
				try {
					waitForConnection();
					setupStreams();
					whileChatting();
				}catch (EOFException e) {
					showMessage("The server ended the conversation!");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					closeAll();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void waitForConnection() throws IOException{
		showMessage("Waiting for connection...\n");
		connection = server.accept();
		showMessage("Now connected to "+ connection.getInetAddress().getHostName());
	}
	
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		showMessage("\n Streams are now setup! \n");
	}
	
	private void whileChatting() throws IOException{
		String message = "You are now connected!";
		sendMessage(message);
		ableToType(true);
		do{
			try {
				message = (String) input.readObject();
				showMessage("\n"+message);
			} catch (ClassNotFoundException e) {
				showMessage("What did you just say to me?");
			}
		}while(!message.equals("CLIENT - END"));
	}
	
	private void closeAll(){
		showMessage("\n Closing connections... \n");
		ableToType(false);
		try {
			output.close();
			input.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(String message){
		try {
			output.writeObject("SERVER -  "+message);
			output.flush();
			showMessage("\nSERVER - "+message);
		} catch (IOException e) {
			chatWindow.append("\n I can't send that message!\n");
		}
	}
	
	private void showMessage(final String text){
		SwingUtilities.invokeLater(
				new Runnable() {
					
					@Override
					public void run() {
						chatWindow.append(text);
						
					}
				}
				);
	}
	
	private void ableToType(final boolean TOF){
		SwingUtilities.invokeLater(
				new Runnable() {
					
					@Override
					public void run() {
						userText.setEditable(TOF);
					}
				}
				);
	}
}























