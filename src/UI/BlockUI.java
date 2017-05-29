package UI;

import java.awt.GridLayout;
import java.beans.PropertyChangeSupport;
import java.nio.file.attribute.AclEntry.Builder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.MeetingInfo;
import data.RoomInfo;
import dataControl.DataController;
import tools.MeetingSorter;

public class BlockUI extends JPanel {
	
	private JLabel roomNameLabel;
	private JLabel personNumLabel;
	private JLabel roomSizeLabel;
	private JLabel positionLabel;
	private JPanel listPanel;
	
	private DataController dataController;
	
	private Date startTimeOfDay;
	private Date endTimeOfDay;
	
	public BlockUI (Date startTime, String buildingName, String floorName, 
		String roomName, ArrayList<MeetingInfo> meetingList, int userID, boolean isShowFree,
		String dbUserName, String dbPassword, String dbPort) throws SQLException, ClassNotFoundException {
		dataController = new DataController(dbUserName, dbPassword, dbPort);
		RoomInfo roomInfo = dataController.getRoomInfo(buildingName, floorName, roomName);
		this.setLayout(null);
		this.setBorder(BorderFactory.createEtchedBorder());
		roomNameLabel = new JLabel(roomName);
		roomNameLabel.setSize(60, 20);
		roomNameLabel.setLocation(10, 10);
		this.add(roomNameLabel);
		personNumLabel = new JLabel(String.valueOf(roomInfo.getPersonNum()));
		personNumLabel.setLocation(80, 10);
		personNumLabel.setSize(60, 20);
		this.add(personNumLabel);
		roomSizeLabel = new JLabel(roomInfo.getRoomSize());
		roomSizeLabel.setSize(60, 20);
		roomSizeLabel.setLocation(110, 10);
		this.add(roomSizeLabel);
		positionLabel = new JLabel(roomInfo.getPosition());
		positionLabel.setSize(60, 20);
		positionLabel.setLocation(160, 10);
		this.add(positionLabel);
		listPanel = new JPanel(new GridLayout(0, 1));
		listPanel.setSize(250, 300);
		listPanel.setLocation(10, 30);
		this.add(listPanel);
		MeetingSorter meetingSorter = new MeetingSorter();
		Collections.sort(meetingList, meetingSorter);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startTime);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		startTimeOfDay = calendar.getTime();
		calendar.set(Calendar.HOUR_OF_DAY, 19);
		endTimeOfDay = calendar.getTime();
		Date lastTime = startTimeOfDay;
		for (MeetingInfo meetingInfo : meetingList) {
			if (meetingInfo.getStartTime().compareTo(lastTime) > 0) {
				BlockCellUI cellPanel = new BlockCellUI(lastTime, meetingInfo.getStartTime(), userID, "", 
						roomInfo.getPersonNum(), buildingName, floorName, roomName, dbUserName, dbPassword, dbPort);
				listPanel.add(cellPanel);
				lastTime = meetingInfo.getStartTime();
			}
			if (!isShowFree) {
				BlockCellUI cellPabel = new BlockCellUI(meetingInfo.getStartTime(), meetingInfo.getEndTime(), userID,
					meetingInfo.getUserName(), roomInfo.getPersonNum(), buildingName, floorName, roomName,
					dbUserName, dbPassword, dbPort);
				listPanel.add(cellPabel);
				lastTime = meetingInfo.getEndTime();
			}
		}
		if (lastTime.compareTo(endTimeOfDay) < 0) {
			BlockCellUI blockCellUI = new BlockCellUI(lastTime, endTimeOfDay, userID, "", roomInfo.getPersonNum(), 
					buildingName, floorName, roomName, dbUserName, dbPassword, dbPort);
			listPanel.add(blockCellUI);
		}
	}
	
}
