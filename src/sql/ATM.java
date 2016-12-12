package sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class ATM {
	HashMap<String, Account> accounts = new HashMap<String, Account>();
	Account currentAccount = null;
	Account t = new Account(null, null);

	public ATM() {
		try {
			loadSQL();

//			Class.forName("com.mysql.jdbc.Driver");
//			String dbURL = "jdbc:mysql://localhost:3306?characterEncoding=utf-8&useSSL=false&user=root&password=Wangzizhuo!@#";
//			Connection connection = DriverManager.getConnection(dbURL);
//			Statement statement = connection.createStatement();
//
//			statement.execute("create database atm;");
//			statement.execute(
//					"CREATE TABLE `atm`.`user` ( `username` VARCHAR(100) NOT NULL , `password` VARCHAR(100) NOT NULL , `balance` DOUBLE NOT NULL DEFAULT '0.0' , UNIQUE (`username`)) ENGINE = InnoDB;");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void display() {
		System.out.print("Hello! How may I help you?\n");
		menu1();
		Scanner scanner = new Scanner(System.in);
		int choice = Integer.parseInt(scanner.nextLine());
		int choice2 = 4;
		while (true) {
			switch (choice) {
			case 1:
				System.out.println("Please enter your name:");
				String name = scanner.nextLine();
				System.out.println("Please enter your password:");
				String passwd = scanner.nextLine();
				createNewAccount(name, passwd);
				// try {
				// save();
				// } catch (IOException e) {
				// e.printStackTrace();
				// }
				saveSQL();
				break;
			case 2:
				System.out.println("Please enter your name");
				String user = scanner.nextLine();
				System.out.println("Please enter your password");
				String password = scanner.nextLine();
				int hash = accounts.get(user).getPassword().hashCode();
				String Jesus = accounts.get(user).getPassword();
				if (password.hashCode() == accounts.get(user).getPassword().hashCode()) {
					currentAccount = accounts.get(user);
					while (true) {
						menu2();
						choice2 = Integer.parseInt(scanner.nextLine());
						switch (choice2) {
						case 1:
							System.out.println("Please enter the amount of money you want to deposit");
							double amount = Double.parseDouble(scanner.nextLine());
							deposit(amount);
							break;
						case 2:
							System.out.println("Please enter the amount of money you want to withdraw");
							double amount2 = Double.parseDouble(scanner.nextLine());
							withdraw(amount2);
							break;
						case 3:
							query();
							break;
						case 4:
							System.out.println("Thanks for using our service");
							break;
						default:
							break;
						}
						if (choice2 == 4) {
							break;
						}
					}
					choice = 2;
				} else {
					System.out.println("Please try again!");
				}
				break;
			default:
				break;
			}
			if (choice2 == 4 && choice != 1) {
				break;
			}
			System.out.print("Hello! How may I help you?\n");
			menu1();
			choice = Integer.parseInt(scanner.nextLine());
		}
		scanner.close();
		saveSQL();
	}

	public void menu1() {
		System.out.println("1.Create New Account\n2.Login");
	}

	public void menu2() {
		System.out.println("1.Deposit\n2.Withdraw\n3.Inquire\n4.Logout");
	}

	public void createNewAccount(String name, String passwd) {
		currentAccount = new Account(name, passwd);
		if (accounts.get(name)==null) {
			accounts.put(name, currentAccount);
			saveSQL();
			System.out.println("Account created successfully!");
		}else {
			System.out.println("User already exist!");
		}
	}

	public boolean login(String name, String passwd) {
		if (passwd == accounts.get(name).getPassword()) {
			currentAccount = accounts.get(name);
		}
		return passwd == accounts.get(name).getPassword();
	}

	public void logOut() {
		currentAccount = null;
	}

	public void deposit(double money) {
		currentAccount.setBalance(currentAccount.getBalance() + money);
		System.out.println("Current balance:" + currentAccount.getBalance());
		saveSQL();
	}

	public void withdraw(double money) {
		if (currentAccount.getBalance() >= money) {
			currentAccount.setBalance(currentAccount.getBalance() - money);
		} else {
			System.out.println("You don't have that much money!");
		}
		System.out.println("Current balance:" + currentAccount.getBalance());
		saveSQL();
	}

	public void query() {
		System.out.println(currentAccount.getUsername() + "\n" + currentAccount.getBalance() + "\n");
	}

	public void saveSQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/atm?characterEncoding=utf-8&useSSL=false&user=root&password=Wangzizhuo!@#";
			Connection connection = DriverManager.getConnection(dbURL);
			Statement statement = connection.createStatement();
			statement.execute("delete from user");
			String insert = "INSERT INTO user (username,password,balance) values (?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insert);

			Set set = accounts.keySet();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				Account temp = accounts.get(iterator.next());
				preparedStatement.setString(1, temp.getUsername());
				preparedStatement.setString(2, temp.getPassword());
				preparedStatement.setDouble(3, temp.getBalance());
				preparedStatement.addBatch();
				preparedStatement.clearParameters();
			}
			preparedStatement.executeBatch();
			preparedStatement.clearBatch();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadSQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/atm?characterEncoding=utf-8&useSSL=false&user=root&password=Wangzizhuo!@#";
			Connection connection = DriverManager.getConnection(dbURL);
			Statement statement = connection.createStatement();

			ResultSet set = statement.executeQuery("SELECT * from atm.user;");
			ResultSetMetaData metaData = set.getMetaData();
			int column = metaData.getColumnCount();
			
			String name="";
			while(set.next()){
				for(int i=1;i<=column;i++){
					if (i==1) {
						name = set.getString(i);
						t.setUsername(name);
					}else if (i==2) {
						t.setPassword(set.getString(i));
					}else if (i==3) {
						t.setBalance(set.getDouble(i));
						accounts.put(name, t);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ATM atm = new ATM();
		atm.display();
	}
}