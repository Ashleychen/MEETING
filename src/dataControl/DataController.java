package dataControl;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import data.MeetingInfo;
import data.RoomInfo;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataController {
	private String databaseDriven = "com.mysql.jdbc.Driver";
	private String databaseURL = "jdbc:mysql://localhost:3306/meeting?useSSL=false";
	private String databaseUserName = "root";
	private String databasePassword = "123";
	private Connection dbConnection = null;
	
	public DataController() {
		// TODO Auto-generated constructor stub
		
	}
	
	//连接数据库
	private void connectToDatabase() throws ClassNotFoundException, SQLException {
		Class.forName(databaseDriven);
		dbConnection = DriverManager.getConnection(databaseURL, databaseUserName, databasePassword);
	}
	
	//插入meeting信息
	public int addMeetingRecord(String userName) {
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
	
	public ArrayList<RoomInfo> getRoomList(String buildingName, String floorName) throws SQLException{
		ArrayList<RoomInfo> roomList = new ArrayList<RoomInfo>();
		String sql = "select * from room where room.region == '" + 
				buildingName + "'" + " and room.layer == '" + floorName + "'";
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			roomList.add(new RoomInfo(resultSet.getString("room_name"), resultSet.getInt("person_num"), 
					resultSet.getString("room_size"), resultSet.getString("position")));
		}
		return roomList;
	}
	
	public ArrayList<MeetingInfo> getMeetingList(String buildingName, String floorName, String roomName, Date searchDate) throws SQLException {
		ArrayList<MeetingInfo> meetingList = new ArrayList<MeetingInfo>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(searchDate);
		calendar.add(calendar.DATE, 1);
		Date nextDate = calendar.getTime();
		String sql = "select * from meeting left join room on"
			     + " meeting.room_id=room.r_id left join user on user.u_id=meeting.applicant_id where meeting.start_time >= '"
			     + searchDate.toString() + "'"
			     + "and meeting.start_time < '" + nextDate.toString() + "'" 
			     + "and room.region='" + buildingName + "'" 
			     + "and room.layer=" + floorName + ";";
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			meetingList.add(new MeetingInfo(resultSet.getDate("start_time"), resultSet.getDate("end_time"), resultSet.getString("user_name")));
		}
		return meetingList;
	}
	
}