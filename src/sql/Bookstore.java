package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class Bookstore {

	/**
	 * @param args
	 * Sorry for still not able to use Chinese *_*
	 * This is the JDBC homework
	 * The connection with MySQL used utf-8 encoding and didn't use SSL(Because of Linux, I must do it.)
	 * Initially, I created a database named bookstore
	 * Then I created a table named bookstore in database bookstore and created fields: store_name, Sales, Date
	 * store_name is VARCHAR type and its length limit is 100, it's also not null
	 * Sales is DOUBLE and not null
	 * Date is VARCHAR and its length limit is 20, it's also not null
	 * The database engine I chose is InnoDB
	 * And then I tell my program to use database bookstore
	 * I created three arrays with appropriate content
	 * Load the batch in a for loop, and then execute it, finally clear it all
	 * In the end, I'll load it all and print it out according to the format 
	 */
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//I'm a OP and will not give you my password
			String dbURL = "jdbc:mysql://localhost:3306/?characterEncoding=utf-8&useSSL=false&user=root&password=***";
			Connection connection = DriverManager.getConnection(dbURL);
			Statement statement = connection.createStatement();

			statement.execute("create database bookstore");
			statement.execute("CREATE TABLE `bookstore`.`bookstore` ( `store_name` VARCHAR(100) NOT NULL , "
					+ "`Sales` DOUBLE NOT NULL , `Date` VARCHAR(20) NOT NULL ) "
					+ "ENGINE = InnoDB;");
			statement.execute("use bookstore");

			String[] names = {"Los Angeles","San Diego","Los Angeles","Boston"};
			Double[] sales = {1500.0,250.0,300.0,700.0};
			String[] dates = {"1999-01-09","1999-01-07","1999-01-08","1999-01-08"};

			String sql = "INSERT INTO bookstore (store_name,Sales,Date) values (?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			for(int i=0;i<4;i++){
				preparedStatement.setString(1, names[i]);
				preparedStatement.setDouble(2, sales[i]);
				preparedStatement.setString(3, dates[i]);
				preparedStatement.addBatch();
				preparedStatement.clearParameters();
			}
			preparedStatement.executeBatch();
			preparedStatement.clearBatch();

			ResultSet set = statement.executeQuery("SELECT * FROM bookstore");
			ResultSetMetaData metaData = set.getMetaData();

			int count = 0;
			for(int i=1;i<=metaData.getColumnCount();i++){
				System.out.print(metaData.getColumnName(i)+"\t");
				if (i!=metaData.getColumnCount()) {
					count += (metaData.getColumnName(i).length() / 8) * 8 + 8;
					//The TAB work like this: (5/8)->1*8, (9/8)*8->2*8, (17/8)*8->3*8
				}
				else{
					count+=metaData.getColumnName(i).length();
				}
			}
			System.out.print("\n");
			for(int i=0;i<count;i++){
				System.out.print("-");
			}
			System.out.print("\n");
			int column = metaData.getColumnCount();
			while(set.next()){
				for(int i=1;i<=column;i++){
					if (i==1) {
						if (set.getString(i).length()<8) {
							System.out.print(set.getString(i) + "\t\t");
						}else {
							System.out.print(set.getString(i) + "\t");
						}
					}else if (i==2) {
						System.out.print(set.getDouble(i)+"\t");
					}else if (i==3) {
						System.out.println(set.getString(i));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
