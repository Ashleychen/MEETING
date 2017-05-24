package dataControl;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dataController {
	private String databaseDriven = "com.mysql.jdbc.Driver";
	private String databaseURL = "jdbc:mysql://localhost:3306/meeting?useSSL=false";
	private String databaseUserName = "root";
	private String databasePassword = "123";
	private Connection dbConnection = null;
	
	public dataController() {
		// TODO Auto-generated constructor stub
		
	}
	
	//连接数据库
	private void connectToDatabase() throws ClassNotFoundException, SQLException {
		Class.forName(databaseDriven);
		dbConnection = DriverManager.getConnection(databaseURL, databaseUserName, databasePassword);
	}
	
	//插入meeting信息
	public int addMeetingRecord(String userName, ) {
		return 0;
	}
	
	//插入用户信息
	public int addUser(String userName, String passWord) {
		if (checkUser(userName, passWord) > 0) {
			return -2;
		}
		String sql = "INSERT INTO users (username, password) VALUES ('" + userName + "', '" + passWord + "')";
		Statement statement;
		try {
			statement = dbConnection.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return -1;
		}
		return 0;
	}
	
	private int checkUser(String userName, String passWord) {
		String sql = "SELECT * FROM users WHERE username='" + userName + "' AND password='" + passWord + "'";
		Statement statement;
		try {
			statement = dbConnection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			result.last();
			return result.getRow();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return 0;
		}
	}
}