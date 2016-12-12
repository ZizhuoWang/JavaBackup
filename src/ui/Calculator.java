package ui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


/**
 *
 * @author ZizhuoWang
 * I'm sorry I can't use Chinese because of the layout of eclipse on Linux.
 * And Netbeans just can't display Chinese...
 * 
 * This application uses JavaFx instead of Swing
 * I think JavaFx may be the next trend
 * Although I didn't use FXML at all...
 * But still I don't think it's any better than Qt *_*
 * Anyway, this is a calculator named "Zizhuo Calculator"
 * It can do add, substract, multiply, divide, extract nth root and nth power of two double numbers.
 * I tried to simulate the calculator on my smartphone, which can display all the numbers in the calculation process
 * 
 * 
 * The coding style comes from my past experience with Qt
 * Maybe it looks like code from an amateur...
 * Well, I'm sorry for the redundant code I wrote.
 * Without any seniors' experience, I think I tried my best.
 * 
 */
public class Calculator extends Application {

	/**
	 * The flag moves with the "focus" of TextField
	 */
	public int flag=1;
	/**
	 * The css style
	 */
	public String style = "-fx-background-color: aqua; -fx-text-fill: black;-fx-font-size:18px;-fx-border-color:black;"
			+ "-fx-border-radius:15;-fx-border-style:solid;-fx-smooth:true;";
	public String style2 = "-fx-background-color: aqua; -fx-text-fill: black;-fx-font-size:18px;"
			+ "-fx-border-style:none;-fx-smooth:true;";

	@Override
	public void start(Stage primaryStage) {

		//Line46-56 are to create MenuBar
		Menu menu = new Menu("About");
		MenuItem item = new MenuItem("Author: ZizhuoWang");
		item.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				new Alert(Alert.AlertType.INFORMATION, "Author: ZizhuoWang\nE-mail: wjwzzc@163.com", ButtonType.OK).show();
			}
		});
		menu.getItems().add(item);
		MenuBar menuBar = new MenuBar(menu);
		menuBar.setMinSize(400, 30);

		//These are the buttons I used in this program. Yeah, they are all here233
		Button btn1 = new Button("1");
		Button btn2 = new Button("2");
		Button btn3 = new Button("3");
		Button btn4 = new Button("4");
		Button btn5 = new Button("5");
		Button btn6 = new Button("6");
		Button btn7 = new Button("7");
		Button btn8 = new Button("8");
		Button btn9 = new Button("9");
		Button btn0 = new Button("0");
		Button btnPoint = new Button(".");
		Button btnAdd = new Button("+");
		Button btnSubstract = new Button("—");//This is not exactly '-', I think it's to small. It's '——' to '—'.
		Button btnMultiply = new Button("x");
		Button btnDivide = new Button("÷");
		Button btnEqual = new Button("=");
		Button btnC = new Button("C");
		Button btnBack = new Button("←");
		Button btnSqrt = new Button("√");
		Button btnPower = new Button("^");
		//These are the TextFields I used in this program. I used them to represent something like "1+1=2" by '1''+''1''=''2'.
		TextField textField1 = new TextField();
		TextField textField2 = new TextField();
		TextField textField3 = new TextField();
		TextField textField4 = new TextField();
		TextField textField5 = new TextField();

		//Set the size of everything...
		btn1.setMinSize(50, 50);
		btn2.setMinSize(50, 50);
		btn3.setMinSize(50, 50);
		btn4.setMinSize(50, 50);
		btn5.setMinSize(50, 50);
		btn6.setMinSize(50, 50);
		btn7.setMinSize(50, 50);
		btn8.setMinSize(50, 50);
		btn9.setMinSize(50, 50);
		btn0.setMinSize(50, 50);
		btnPoint.setMinSize(50, 50);
		btnAdd.setMinSize(50, 50);
		btnSubstract.setMinSize(50, 50);
		btnMultiply.setMinSize(50, 50);
		btnDivide.setMinSize(50, 50);
		btnEqual.setMinSize(50, 50);
		btnC.setMinSize(50, 50);
		btnBack.setMinSize(50, 50);
		btnSqrt.setMinSize(50, 50);
		btnPower.setMinSize(50, 50);
		textField1.setMinSize(300, 50);
		textField2.setMinSize(300, 50);
		textField3.setMinSize(300, 50);
		textField4.setMinSize(300, 50);
		textField5.setMinSize(300, 50);


		//Set the style of almost everything
		btn1.setStyle(style);
		btn2.setStyle(style);
		btn3.setStyle(style);
		btn4.setStyle(style);
		btn5.setStyle(style);
		btn6.setStyle(style);
		btn7.setStyle(style);
		btn8.setStyle(style);
		btn9.setStyle(style);
		btn0.setStyle(style);
		btnPoint.setStyle(style);
		btnAdd.setStyle(style);
		btnSubstract.setStyle(style);
		btnMultiply.setStyle(style);
		btnDivide.setStyle(style);
		btnSqrt.setStyle(style);
		btnPower.setStyle(style);
		btnEqual.setStyle(style);
		btnC.setStyle(style);
		btnBack.setStyle(style);
		menuBar.setStyle(style2);
		textField1.setStyle(style2);
		textField2.setStyle(style2);
		textField3.setStyle(style2);
		textField4.setStyle(style2);
		textField5.setStyle(style2);

		
		//Apart from the MenuBar I wrote before, this is the main part.
		GridPane gridPane = new GridPane();
		//The format is (Node child, int columnIndex, int rowIndex), quite strange...
		gridPane.add(textField1, 0, 0);
		gridPane.add(btnSqrt, 1, 0);
		gridPane.add(btnC, 2, 0);
		gridPane.add(btnBack, 3, 0);
		gridPane.add(btnAdd, 4, 0);

		gridPane.add(textField2, 0, 1);
		gridPane.add(btn7, 1, 1);
		gridPane.add(btn8, 2, 1);
		gridPane.add(btn9, 3, 1);
		gridPane.add(btnSubstract, 4, 1);

		gridPane.add(textField3, 0, 2);
		gridPane.add(btn4, 1, 2);
		gridPane.add(btn5, 2, 2);
		gridPane.add(btn6, 3, 2);
		gridPane.add(btnMultiply, 4, 2);

		gridPane.add(textField5, 0, 3);textField5.setText("=");
		gridPane.add(btn1, 1, 3);
		gridPane.add(btn2, 2, 3);
		gridPane.add(btn3, 3, 3);
		gridPane.add(btnDivide, 4, 3);

		gridPane.add(textField4, 0, 4);
		gridPane.add(btn0, 1, 4);
		gridPane.add(btnPoint, 2, 4);
		gridPane.add(btnPower, 3, 4);
		gridPane.add(btnEqual, 4, 4);
		
		
//		btn0.setOnKeyPressed(new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent keyEvent) {
//				if (keyEvent.getCode()==KeyCode.DIGIT0) {
//					System.out.println("OK");
//				}
//			}
//		});
		
		//btn1 to btnPoint are of same structure. Read one, you'll get it all.
		//So, I'll just write one comment $_$
		btn1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					//If there already had a result, then flag move to the textField1 and clear all.
					flag=1;
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==2) {
					//If you have done with the operator, then move on to textField3
					flag++;
				}
				if (flag%4==1) {
					//Change the content of textfield1
					textField1.setText(textField1.getText() +btn1.getText());
				}
				if (flag%4==3) {
					//Change the content of textfield3
					textField3.setText(textField3.getText() +btn1.getText());
				}
			}
		});
		btn2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==2) {
					flag++;
				}
				if (flag%4==1) {
					textField1.setText(textField1.getText() +btn2.getText());
				}
				if (flag%4==3) {
					textField3.setText(textField3.getText() +btn2.getText());
				}
			}
		});
		btn3.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==2) {
					flag++;
				}
				if (flag%4==1) {
					textField1.setText(textField1.getText() +btn3.getText());
				}
				if (flag%4==3) {
					textField3.setText(textField3.getText() +btn3.getText());
				}
			}
		});
		btn4.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==2) {
					flag++;
				}
				if (flag%4==1) {
					textField1.setText(textField1.getText() +btn4.getText());
				}
				if (flag%4==3) {
					textField3.setText(textField3.getText() +btn4.getText());
				}
			}
		});
		btn5.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==2) {
					flag++;
				}
				if (flag%4==1) {
					textField1.setText(textField1.getText() +btn5.getText());
				}
				if (flag%4==3) {
					textField3.setText(textField3.getText() +btn5.getText());
				}
			}
		});
		btn6.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==2) {
					flag++;
				}
				if (flag%4==1) {
					textField1.setText(textField1.getText() +btn6.getText());
				}
				if (flag%4==3) {
					textField3.setText(textField3.getText() +btn6.getText());
				}
			}
		});
		btn7.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==2) {
					flag++;
				}
				if (flag%4==1) {
					textField1.setText(textField1.getText() +btn7.getText());
				}
				if (flag%4==3) {
					textField3.setText(textField3.getText() +btn7.getText());
				}
			}
		});
		btn8.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==2) {
					flag++;
				}
				if (flag%4==1) {
					textField1.setText(textField1.getText() +btn8.getText());
				}
				if (flag%4==3) {
					textField3.setText(textField3.getText() +btn8.getText());
				}
			}
		});
		btn9.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==2) {
					flag++;
				}
				if (flag%4==1) {
					textField1.setText(textField1.getText() +btn9.getText());
				}
				if (flag%4==3) {
					textField3.setText(textField3.getText() +btn9.getText());
				}
			}
		});
		btn0.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==2) {
					flag++;
				}
				if (flag%4==1) {
					textField1.setText(textField1.getText() +btn0.getText());
				}
				if (flag%4==3) {
					textField3.setText(textField3.getText() +btn0.getText());
				}
			}
		});
		btnPoint.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText("");
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==2) {
					flag++;
				}
				if (flag%4==1) {
					textField1.setText(textField1.getText() +btnPoint.getText());
				}
				if (flag%4==3) {
					textField3.setText(textField3.getText() +btnPoint.getText());
				}
			}
		});

		//Well, then from btnAdd to btnPower are the same. So I'll just write one comment.
		btnAdd.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if (flag%4==0) {
					//If there already had a result, then move the result in textField4 to textField1 and clear others.
					flag=1;//Use the next if statement
					textField1.setText(textField4.getText());
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==1||flag%4==3) {
					flag++;
				}
				if (flag%4==2) {
					//Set the operator
					textField2.setText(btnAdd.getText());
				}
			}
		});
		btnSubstract.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText(textField4.getText());
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==1||flag%4==3) {
					flag++;
				}
				if (flag%4==2) {
					textField2.setText(btnSubstract.getText());
				}
			}
		});
		btnMultiply.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText(textField4.getText());
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==1||flag%4==3) {
					flag++;
				}
				if (flag%4==2) {
					textField2.setText(btnMultiply.getText());
				}
			}
		});
		btnDivide.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText(textField4.getText());
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==1||flag%4==3) {
					flag++;
				}
				if (flag%4==2) {
					textField2.setText(btnDivide.getText());
				}
			}
		});	
		btnSqrt.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText(textField4.getText());
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==1||flag%4==3) {
					flag++;
				}
				if (flag%4==2) {
					textField2.setText(btnSqrt.getText());
				}
			}
		});
		btnPower.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==0) {
					flag=1;
					textField1.setText(textField4.getText());
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
				}
				if (flag%4==1||flag%4==3) {
					flag++;
				}
				if (flag%4==2) {
					textField2.setText(btnPower.getText());
				}
			}
		});

		btnC.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//Clear them all and set flag to 1
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				textField4.setText("");
				flag=1;
			}
		});
		btnBack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==1&&!textField1.getText(0, textField1.getText().length()).isEmpty()) {
					textField1.setText(textField1.getText(0, textField1.getText().length()-1));
				}
				if (flag%4==3&&!textField3.getText(0, textField3.getText().length()).isEmpty()) {
					textField3.setText(textField3.getText(0, textField3.getText().length()-1));
				}
				if (flag%4==0&&!textField1.getText(0, textField1.getText().length()).isEmpty()) {
					//Move focus to textField1
					flag=1;
					textField1.setText(textField4.getText());
					textField2.setText("");
					textField3.setText("");
					textField4.setText("");
					textField1.setText(textField1.getText(0, textField1.getText().length()-1));
				}
			}
		});
		btnEqual.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (flag%4==3) {
					flag++;
				}
				if (flag%4==0) {
					Double result=0.0;
					switch((char) textField2.getText().charAt(0)){
					case '+':
						result=Double.parseDouble(textField1.getText())+Double.parseDouble(textField3.getText());
						break;
					case '—':
						result=Double.parseDouble(textField1.getText())-Double.parseDouble(textField3.getText());
						break;
					case 'x':
						result=Double.parseDouble(textField1.getText())*Double.parseDouble(textField3.getText());
						break;
					case '÷':
						result=Double.parseDouble(textField1.getText())/Double.parseDouble(textField3.getText());
						break;
					case '\0':
						result=0.0;
						break;
					case '√':
						result=Math.pow(Double.parseDouble(textField1.getText()), 
								1.0/Double.parseDouble(textField3.getText()));
						break;
					case '^':
						result=Math.pow(Double.parseDouble(textField1.getText()),
								Double.parseDouble(textField3.getText()));
					}
					textField4.setText(result.toString());
				}
			}
		});
		//VerticalBox
		VBox vBox = new VBox(menuBar);
		vBox.getChildren().add(gridPane);
		vBox.setStyle(style2);

		//There are some errors on Ubuntu MATE.
		Scene scene = new Scene(vBox, 500, 280);//300+4*50=500  30+5*50=280
		primaryStage.setTitle("ZiZhuo Calculator");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
