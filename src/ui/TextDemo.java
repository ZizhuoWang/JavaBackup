package ui;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class TextDemo {
	JFrame frame;
	JTextArea textArea;

	public TextDemo(){
		frame = new JFrame();
		frame.setTitle("hello.txt");
		textArea = new JTextArea();
		textArea.setText("abc");
		textArea.getDocument().addDocumentListener(new TextChangeListener());
		frame.add(textArea);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 200);
		frame.setVisible(true);
	}

	private class TextChangeListener implements DocumentListener {


		boolean changed = true;
		public void insertUpdate(DocumentEvent e) {
			if(changed){
				frame.setTitle("*"+frame.getTitle());
				changed = false;
			}
		}
		public void removeUpdate(DocumentEvent e) {
			if(changed){
				frame.setTitle("*"+frame.getTitle());
				changed = false;
			}
		}
		public void changedUpdate(DocumentEvent e) {
			if(changed){
				frame.setTitle("*"+frame.getTitle());
				changed = false;
			}
		}
	}
	public static void main(String[] args) {
		TextDemo textDemo = new TextDemo();
	}
}


