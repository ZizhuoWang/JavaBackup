package messager;

import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Server extends JFrame{
	
	private JTextField userText;
	private JTextArea chatWindow;
	private JButton submitButton;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private Socket connection;

	public Server() {
		super("Zizhuo Instant Messager");//设置标题
		this.setLayout(new GridLayout(2, 1));//设置为两行一列的网格布局
		userText = new JTextField(30);//用户输入框
		userText.setEditable(false);//未连接不可编辑
		userText.addActionListener(//添加事件监听器
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						sendMessage(e.getActionCommand());//得到文本框中的文字
						userText.setText("");//清空文本框
					}
				}
				);
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new FlowLayout());//设置为流式布局
		submitButton = new JButton("发送");//发送按钮
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(userText.getText());//得到文本框中的文字
				userText.setText("");//清空文本框
			}
		});
		messagePanel.add(userText);//流式布局1
		messagePanel.add(submitButton);//流式布局2
		chatWindow = new JTextArea();
		this.add(chatWindow);//添加聊天框
		add(new JScrollPane(chatWindow));//为聊天框添加滚动条
		this.add(messagePanel);//添加上流式布局
		setSize(500,500);
		setVisible(true);
	}
	
	public void startRunning(){
		try {
			server = new ServerSocket(6789,100);//监听6789端口，等待连接的客户端的最大数量为100
			while (true) {
				try {
					waitForConnection();//等待连接
					setupStreams();//设定数据流
					whileChatting();//正在聊天
				}catch (EOFException e) {
					showMessage("\nThe client ended the conversation!");
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					closeAll();//关闭所有连接和数据流
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void waitForConnection() throws IOException{
		showMessage("Waiting for connection...\n");
		connection = server.accept();//等待连接，连接后会跳到下一语句
		showMessage("Now connected to "+ connection.getInetAddress().getHostName());
	}
	
	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());//建立输出流
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());//建立输入流
		showMessage("\n Streams are now setup! \n");
	}
	
	private void whileChatting() throws IOException{
		String message = "You are now connected!";
		sendMessage(message);
		ableToType(true);//用户文本框可以编辑
		do{
			try {
				message = (String) input.readObject();
				showMessage("\n"+message);
			} catch (ClassNotFoundException e) {
				showMessage("What did you just say to me?");
			}
		}while(!message.equals("CLIENT - END"));
	}
	/**
	 * @return 当前时间
	 */
	private String getTime(){
		Calendar calendar = Calendar.getInstance();
		String time = String.valueOf(calendar.get(Calendar.YEAR))+"/"+
				String.valueOf(calendar.get(Calendar.MONTH)+1)+"/"+
				String.valueOf(calendar.get(Calendar.DAY_OF_MONTH))+"/"+
				String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))+":"+
				String.valueOf(calendar.get(Calendar.MINUTE))+":"+
				String.valueOf(calendar.get(Calendar.SECOND));
		return time;
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
			output.writeObject("\nSERVER - "+getTime()+"\n"+message);
			output.flush();
			showMessage("\nSERVER - "+getTime()+"\n"+message);
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