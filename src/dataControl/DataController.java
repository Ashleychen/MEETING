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
	
	public int getRoomId(String buildingName, String floorName, String roomName) throws SQLException {
		String sql = "SELECT r_id FROM room WHERE region='" + buildingName + 
			"' AND floor_name='" + floorName + "' AND room_name='" + roomName + "'";
		Statement statement = dbConnection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		result.first();
		return result.getInt("r_id");
	}
	
	//插入meeting信息
	public int addMeetingRecord(int uid, Date bookingStartTime, Date bookingendTime, int roomId, int personNum, String description) throws SQLException {
		String sql = "INSERT INTO meeting (start_time, end_time, applicant_id, room_id, personNum, description) VALUES ('" +
			bookingStartTime.toString() + "', '" + bookingendTime.toString() + "', '" + uid + "', '" + roomId + "', '" + 
			personNum + "', '" + description + "')";
		Statement statement = dbConnection.createStatement();
		statement.executeQuery(sql);
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
	public int getUId(String userName, String passWord) throws SQLException {
		String sql = "SELECT * FROM users WHERE username='" + userName + "' AND password='" + passWord + "'";
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.first();
		return resultSet.getInt("u_id");
	}
	
	public int checkUser(String userName, String passWord) {
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
	
	public String getNotice() throws SQLException {
		String sql = "select * from notice";
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.last();
		return resultSet.getString("context");
	}
}