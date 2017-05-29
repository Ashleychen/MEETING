package dataControl;

import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import data.MeetingInfo;
import data.RoomInfo;
import tools.MeetingSorter;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataController {
	private String databaseDriven = "com.mysql.jdbc.Driver";
	private String databaseURL = "jdbc:mysql://localhost:3306/meeting?useSSL=false&characterEncoding=utf-8";
	private String databaseUserName = "root";
	private String databasePassword = "123";
	private Connection dbConnection = null;
	
	public DataController(String dbUserName, String dbPassword, String dbPort) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		databaseUserName = dbUserName;
		databasePassword = dbPassword;
		databaseURL = "jdbc:mysql://localhost:" + dbPort + "/meeting?useSSL=false&characterEncoding=utf-8";
		connectToDatabase();
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
	
	public ArrayList<String> getBuildingNames() throws SQLException {
		String sql = "select distinct(region) from room order by region ASC;";
		Statement statement = dbConnection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		ArrayList<String> buildingNames = new ArrayList<String>();
		while (result.next()) {
			buildingNames.add(result.getString("region"));
		}
		return buildingNames;
	}
	
	public ArrayList<String> getFloorNames(String buildingName) throws SQLException {
		String sql = "select distinct(floor_name) from room WHERE region='" + buildingName  + "' order by floor_name ASC;";
		Statement statement = dbConnection.createStatement();
		ResultSet result = statement.executeQuery(sql);
		ArrayList<String> floorNames = new ArrayList<String>();
		while (result.next()) {
			floorNames.add(result.getString("floor_name"));
		}
		return floorNames;
	}
	
	//插入meeting信息
	public int addMeetingRecord(int uid, Date bookingStartTime, Date bookingendTime, int roomId, int personNum, String description) throws SQLException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sql = "INSERT INTO meeting (start_time, end_time, applicant_id, room_id, personNum, description) VALUES ('" +
				dateFormat.format(bookingStartTime) + "', '" + dateFormat.format(bookingendTime) + "', '" + uid + "', '" + roomId + "', '" + 
			personNum + "', '" + description + "')";
		Statement statement = dbConnection.createStatement();
		statement.executeUpdate(sql);
		return 0;
	}
	
	//插入用户信息
	public int addUser(String userName, String passWord) {
		if (checkUser(userName, passWord) > 0) {
			return -2;
		}
		String sql = "INSERT INTO user (user_name, password) VALUES ('" + userName + "', '" + passWord + "')";
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
		String sql = "SELECT * FROM user WHERE user_name='" + userName + "' AND password='" + passWord + "'";
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.first();
		return resultSet.getInt("u_id");
	}
	
	public String getUserName(int userID) throws SQLException {
		String sql = "SELECT * FROM user WHERE u_id=" + userID;
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.first();
		return resultSet.getString("user_name");
	}
	
	public RoomInfo getRoomInfo(String buildingName, String floorName, String roomName) throws SQLException {
		String sql = "SELECT * FROM room WHERE region='" + buildingName + "' AND floor_name='" + floorName
			+ "' AND room_name='" + roomName + "'";
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		resultSet.first();
		return new RoomInfo(resultSet.getString("room_name"), resultSet.getInt("person_num"),
			resultSet.getString("room_size"), resultSet.getString("position"));
	}
	
	public int checkUser(String userName, String passWord) {
		String sql = "SELECT * FROM user WHERE user_name='" + userName + "' AND password='" + passWord + "'";
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
		String sql = "select * from room where room.region = '" + 
				buildingName + "'" + " and room.floor_name = '" + floorName + "'";
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
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(searchDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startTime = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endTime = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sql = "select * from meeting left join room on"
			     + " meeting.room_id=room.r_id left join user on user.u_id=meeting.applicant_id where meeting.start_time >= '"
			     + dateFormat.format(startTime) + "'"
			     + "and meeting.start_time < '" + dateFormat.format(endTime) + "'" 
			     + "and room.region='" + buildingName + "'" 
			     + "and room.room_name='" + roomName + "'"
			     + "and room.floor_name='" + floorName + "';";
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			meetingList.add(new MeetingInfo(resultSet.getTimestamp("start_time"), resultSet.getTimestamp("end_time"),
				resultSet.getString("user_name"), resultSet.getString("region"), resultSet.getString("floor_name"),
				resultSet.getString("room_name"), resultSet.getString("description")));
		}
		return meetingList;
	}
	public ArrayList<MeetingInfo> getMyMeetingList(Date searchDate, String buildingName) throws SQLException {
		ArrayList<MeetingInfo> meetingList = new ArrayList<MeetingInfo>();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(searchDate);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date startTime = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endTime = calendar.getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String sql = "select * from meeting left join room on"
			     + " meeting.room_id=room.r_id left join user on user.u_id=meeting.applicant_id where meeting.start_time >= '"
			     + dateFormat.format(startTime) + "'"
			     + "and meeting.start_time < '" + dateFormat.format(endTime) + "'" 
			     + "and room.region='" + buildingName + "'";
		Statement statement = dbConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		while (resultSet.next()) {
			meetingList.add(new MeetingInfo(resultSet.getTimestamp("start_time"), resultSet.getTimestamp("end_time"),
				resultSet.getString("user_name"), resultSet.getString("region"), resultSet.getString("floor_name"),
				resultSet.getString("room_name"), resultSet.getString("description")));
		}
		MeetingSorter meetingSorter = new MeetingSorter();
		Collections.sort(meetingList, meetingSorter);
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