package UI;

import java.awt.GridLayout;
import java.awt.Panel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import data.MeetingInfo;
import data.RoomInfo;
import dataControl.DataController;

public class TabbedPanelUI extends Panel{
	private DataController dataController;
	
	public TabbedPanelUI(String buildingName, String floorName, Date searchDate) {
		// TODO Auto-generated constructor stub
		this.setLayout(new GridLayout(3, 3));
		dataController = new DataController();
		try {
			ArrayList<RoomInfo> roomNameList = dataController.getRoomList(buildingName, floorName);
			for (RoomInfo roomInfo : roomNameList) {
				ArrayList<MeetingInfo> meetingList = dataController.getMeetingList(buildingName, 
						floorName, roomInfo.getRoomName(), searchDate);
				BlockUI roomBlock = new BlockUI(roomInfo.getRoomName(), roomInfo.getPersonNum(), 
						roomInfo.getRoomSize(), roomInfo.getPosition(), meetingList);
				this.add(roomBlock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
