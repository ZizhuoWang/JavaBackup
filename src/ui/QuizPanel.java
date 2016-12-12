package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class QuizPanel extends JPanel{
	JFrame quizFrame;
	JLabel question;
	JRadioButton a,b,c,d;
	ButtonGroup quizGroup;
	JButton submit;
	
	public QuizPanel(JFrame frame){
		this.quizFrame = frame;
		question = new JLabel("abcd");
		a = new JRadioButton("a");
		b = new JRadioButton("b");
		c = new JRadioButton("c");
		d = new JRadioButton("d");
		
		quizGroup = new ButtonGroup();
		quizGroup.add(a);quizGroup.add(b);quizGroup.add(c);quizGroup.add(d);
		submit = new JButton("Submit");
		submit.setSize(50,50);
		submit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(a.isSelected()){
					JOptionPane.showMessageDialog(submit,"Right");
				}else{
					JOptionPane.showMessageDialog(submit, "Wrong");
				}
				
			}
		});
		this.setLayout(new GridLayout(6, 1));
		this.add(question);
		this.add(a);
		this.add(b);
		this.add(c);
		this.add(d);
		this.add(submit);
	}
	private class SubmitListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(a.isSelected()){
				JOptionPane.showMessageDialog(submit,"Right");
			}else{
				JOptionPane.showMessageDialog(submit, "Wrong");
			}
		}
	}
}
