package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import com.mysql.jdbc.Statement;

public class Test {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String dbURL = "jdbc:mysql://localhost:3306/atm?characterEncoding=utf-8&useSSL=false&user=root&password=Wangzizhuo!@#";
			Connection connection = DriverManager.getConnection(dbURL);
			
			String query = "SELECT * FROM user";
			java.sql.Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
				System.out.print(resultSetMetaData.getColumnName(i)+"\t");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
