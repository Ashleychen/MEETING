package UI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import data.MeetingInfo;
import data.RoomInfo;
import dataControl.DataController;
import javax.swing.BoxLayout;

public class TabbedPanelUI extends Panel{
	private DataController dataController;
	
	public TabbedPanelUI(String buildingName, String floorName, Date searchDate, int userID, 
			boolean isShowFree, String dbUserName, String dbPassword, String dbPort) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		this.setLayout(new GridLayout(0, 3));
		dataController = new DataController(dbUserName, dbPassword, dbPort);
		try {
			ArrayList<RoomInfo> roomNameList = dataController.getRoomList(buildingName, floorName);
			for (RoomInfo roomInfo : roomNameList) {
				
				ArrayList<MeetingInfo> meetingList = dataController.getMeetingList(buildingName, 
						floorName, roomInfo.getRoomName(), searchDate);
				BlockUI roomBlock = new BlockUI(searchDate, buildingName, floorName, roomInfo.getRoomName(), 
						meetingList, userID, isShowFree, dbUserName, dbPassword, dbPort);
				this.add(roomBlock);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
