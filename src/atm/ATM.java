package atm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Savepoint;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class ATM {
	static HashMap<String,Account> accounts = new HashMap<String,Account>();
	Account currentAccount=null;
	Account t = new Account(null, null);
	public ATM(){
		try {
			load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void display(){
		System.out.print("Hello! How may I help you?\n");
		menu1();
		Scanner scanner = new Scanner(System.in);
		int choice = Integer.parseInt(scanner.nextLine());
		int choice2=4;
		while(true){
			switch (choice) {
			case 1:
				System.out.println("Please enter your name:");
				String name = scanner.nextLine();
				System.out.println("Please enter your password:");
				String passwd = scanner.nextLine();
				createNewAccount(name, passwd);
				try {
					save();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				System.out.println("Please enter your name");
				String user = scanner.nextLine();
				System.out.println("Please enter your password");
				String password = scanner.nextLine();
				if (password.hashCode()==accounts.get(user).getPassword().hashCode()) {
					currentAccount=accounts.get(user);
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
						if (choice2==4) {
							break;
						}
					}
					choice=2;
				}
				else{
					System.out.println("Please try again!");
				}
				break;
			default:
				break;
			}
			if (choice2==4&&choice!=1) {
				break;
			}
			System.out.print("Hello! How may I help you?\n");
			menu1();
			choice = Integer.parseInt(scanner.nextLine());
		}
		scanner.close();
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void menu1(){
		System.out.println("1.Create New Account\n2.Login");
	}
	public void menu2(){
		System.out.println("1.Deposit\n2.Withdraw\n3.Inquire\n4.Logout");
	}
	public void createNewAccount(String name,String passwd){
		currentAccount = new Account(name, passwd);
		accounts.put(name, currentAccount);
		System.out.println("Account created successfully!");
	}
	public boolean login(String name,String passwd){
		if(passwd==accounts.get(name).getPassword()){
			currentAccount=accounts.get(name);
		}
		return passwd==accounts.get(name).getPassword();
	}
	public void logOut(){
		currentAccount = null;
	}
	public void deposit(double money){
		currentAccount.setBalance(currentAccount.getBalance()+money);
		System.out.println("Current balance:"+currentAccount.getBalance());
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void withdraw(double money){
		if (currentAccount.getBalance()>=money) {
			currentAccount.setBalance(currentAccount.getBalance() - money);
		}
		else{
			System.out.println("You don't have that much money!");
		}
		System.out.println("Current balance:"+currentAccount.getBalance());
		try {
			save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void query(){
		System.out.println(currentAccount.getUsername()+"\n"
				+currentAccount.getBalance()+"\n");
	}
	public void save()throws IOException{
		FileOutputStream fileOutputStream = new FileOutputStream("/home/wang/list.txt",false);
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream,"UTF-8");
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

		Set set = accounts.keySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			Account temp = accounts.get(iterator.next());
			bufferedWriter.write(temp.getUsername()+"\n"
					+temp.getPassword()+"\n"+temp.getBalance()+"\n");
		}
		bufferedWriter.flush();
		bufferedWriter.close();
	}
	public void load()throws IOException{
		File file = new File("/home/wang/list.txt");
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		int count = 0;
		String temp;
		while((temp=bufferedReader.readLine())!=null){
			if(count%3==0){
				t.setUsername(temp);
				count++;
				continue;
			}
			if(count%3==1){
				t.setPassword(temp);
				count++;
				continue;
			}
			if (count%3==2) {
				t.setBalance(Double.parseDouble(temp));
				accounts.put(t.getUsername(), t);
				count++;
				continue;
			}
		}
		bufferedReader.close();
	}
	
	public void addInterest(double rate){
		Set set = accounts.keySet();
		Iterator iterator = set.iterator();
		while(iterator.hasNext()){
			Account temp = accounts.get(iterator.next());
			temp.setBalance(temp.getBalance()*(1+rate));
		}
		
		try {
			save();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		ATM atm = new ATM();
		Thread thread = new Thread(new Interest(atm,0.01));
		thread.start();
		atm.display();	
	}
}