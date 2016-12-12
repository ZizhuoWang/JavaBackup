package atm;

public class Account {

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	private String username;
	private String password;
	private double balance;

	public Account(String name,String pass){
		this.username = name;
		this.password = pass;
		this.balance = 0;
	}
}