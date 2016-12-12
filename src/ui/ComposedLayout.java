package ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

public class ComposedLayout {

	public static void main(String[] args) {
//		JFrame frame = new JFrame("Composed");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JSplitPane splitPane = new JSplitPane();
//
//		JButton b1 = new JButton("1");
//		JButton b2 = new JButton("2");
//		JButton b3 = new JButton("3");
//		JButton b4 = new JButton("4");
//		frame.getContentPane().setLayout(new GridLayout(4, 1));
//		frame.add(b1);frame.add(b2);frame.add(b3);frame.add(b4);
//		
//		frame.setSize(600, 600);
//		frame.setVisible(true);
		
		JPanel left = new JPanel();
		left.setLayout(new GridLayout(4, 1));
		JButton b1 = new JButton("1");
		JButton b2 = new JButton("2");
		JButton b3 = new JButton("3");
		JButton b4 = new JButton("4");
		left.add(b1);left.add(b2);left.add(b3);left.add(b4);
		
		JPanel right = new JPanel();
		right.setLayout(new GridLayout(4, 1));
//		FlowLayout f1 = new FlowLayout();FlowLayout f2 = new FlowLayout();FlowLayout f3 = new FlowLayout();
//		FlowLayout f4 = new FlowLayout();
		JPanel temp = null;
		for(int i=0;i<4;i++){
			temp = new JPanel();
			temp.setLayout(new FlowLayout(FlowLayout.LEFT));
			temp.add(new JLabel(Integer.toString(i+1)));
			temp.add(new JTextField("Hello"));
			right.add(temp);
		}
		
		JFrame frame = new JFrame("Composed");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JSplitPane splitPane = new JSplitPane();
		splitPane.setLeftComponent(left);
		splitPane.setRightComponent(right);
		splitPane.setDividerLocation(80);
		frame.add(splitPane);
		frame.setSize(300, 300);
		frame.setVisible(true);

	}

}
