package data;

import java.util.Date;

public class MeetingInfo {
	private Date startTime;
	private Date endTime;
	private String userName;
	
	public MeetingInfo(Date st, Date et, String un) {
		// TODO Auto-generated constructor stub
		startTime = st;
		endTime = et;
		userName = un;
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
}
