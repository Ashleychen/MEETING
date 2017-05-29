package data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dataControl.DataController;

public class MeetingInfo {
	private Date startTime;
	private Date endTime;
	private String userName;
	private String buildingName;
	private String floorName;
	private String roomName;
	private String topicText;
	
	public MeetingInfo(Date st, Date et, String un, String bn, String fn, String rn, String tt) {
		// TODO Auto-generated constructor stub
		startTime = st;
		endTime = et;
		userName = un;
		buildingName = bn;
		floorName = fn;
		roomName = rn;
		topicText = tt;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getBuildingName() {
		return buildingName;
	}
	
	public String getFloorName() {
		return floorName;
	}
	
	public String getRoomName() {
		return roomName;
	}
	
	public String getDateStr() {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(startTime);
	}
	
	public String getWeekdayStr() {
		DateFormat dateFormat = new SimpleDateFormat("EEEE");
		return dateFormat.format(startTime);
	}
	
	public String getTimeStr(Date time) {
		DateFormat dateFormat = new SimpleDateFormat("hh:mm");
		return dateFormat.format(time);
	}
	
	public String getTopicText() {
		return topicText;
	}
}
