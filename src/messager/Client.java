package messager;

import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Client extends JFrame{
	private JTextField userText;
	private JTextArea chatWindow;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP;
	private Socket connection;
	private JButton submitButton;

	public Client(String host) {
		super("Zizhuo Client");//设置标题
		this.setLayout(new GridLayout(2, 1));//设置为两行一列的网格布局
		serverIP = host;//服务器
		userText = new JTextField(30);
		userText.setEditable(false);//未连接不可编辑
		userText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sendMessage(e.getActionCommand());//得到文本框中的文字
				userText.setText("");//清空文本框
			}
		});
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
		setSize(400,400);
		setVisible(true);
	}

	public void startRunning(){
		try {
			connectToServer();
			setupStreams();
			whileChatting();
		} catch (EOFException e) {
			showMessage("\n Server closed connection!");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeAll();
		}
	}

	private void connectToServer() throws IOException{
		showMessage("Attempting connection...\n");
		connection = new Socket(InetAddress.getByName(serverIP), 6789);//连接服务器的6789端口
		showMessage("Connected to "+ connection.getInetAddress().getHostName());
	}

	private void setupStreams() throws IOException{
		output = new ObjectOutputStream(connection.getOutputStream());//建立输出流
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());//建立输入流
		showMessage("\n Good to go!\n");
	}

	private void whileChatting() throws IOException{
		ableToType(true);
		do {
			try {
				message = (String) input.readObject();
				showMessage("\n"+ message);
			} catch (ClassNotFoundException e) {
				showMessage("\n I don't know what you are talking about!");
			}
		} while (!message.equals("SERVER - END"));
	}

	private void closeAll(){
		showMessage("\n Closing down...");
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
			output.writeObject("\nCLIENT - "+getTime()+"\n"+message);
			output.flush();
			showMessage("\nCLIENT - "+getTime()+"\n"+message);
		} catch (IOException e) {
			chatWindow.append("\n Something happened!");
		}
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
	private void showMessage(final String m){
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				chatWindow.append(m);
			}
		});
	}

	private void ableToType(final boolean TOF){
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				userText.setEditable(TOF);
			}
		});
	}
}