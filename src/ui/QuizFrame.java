package ui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class QuizFrame extends JFrame{
	public QuizFrame() {
		QuizPanel panel = new QuizPanel(this);
		this.setTitle("Quiz");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		this.setSize(600, 200);
		
	}
	public static void main(String[] args) {
		QuizFrame frame = new QuizFrame();
		frame.setVisible(true);
//		JFileChooser fileChooser = new JFileChooser();
//		fileChooser.setVisible(true);
	}

}
