package ngd;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class NGDUI {
	public NGDUI() {
		//初始化应用标题和标签
		JFrame frame = new JFrame("Normalized Google Distance");
		JLabel label1 = new JLabel("参数一");
		JLabel label2 = new JLabel("参数二");
		JLabel label3 = new JLabel("结果");
		//三行一列的网格布局
		frame.setLayout(new GridLayout(3, 1));
		//第一行流式布局
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout());
		panel1.add(label1);
		JTextField textField1 = new JTextField(20);
		panel1.add(textField1);
		//第二行流式布局
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.add(label2);
		JTextField textField2 = new JTextField(20);
		panel2.add(textField2);
		//第三行流式布局
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout());
		panel3.add(label3);
		JTextField textField3 = new JTextField(20);
		textField3.setEditable(false);//结果框不可更改
		panel3.add(textField3);
		JButton query = new JButton("查询");//查询按钮
		//给查询按钮加上事件监听器
		query.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				textField3.setText(String.valueOf(
						NormalizedGoogleDistance.NGD(
								textField1.getText(),textField2.getText() )));				
			}
		});
		panel3.add(query);
		
		frame.setSize(300, 300);
		//设置窗口居中
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((dimension.width-frame.getWidth())/2, 
				(dimension.height-frame.getHeight())/2);
		//添加到网格布局中
		frame.add(panel1);frame.add(panel2);frame.add(panel3);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
	public static void main(String[] args) {
		try {
			//设置皮肤
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			NGDUI ui = new NGDUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
