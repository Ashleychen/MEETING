package UI;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.MeetingInfo;
import tools.MeetingSorter;

public class BlockUI extends JPanel {
	
	public BlockUI() {
	}
	private JLabel roomNameLabel;
	private JLabel personNumLabel;
	private JLabel roomSizeLabel;
	private JLabel positionLabel;
	private JPanel listPanel;
	
	public BlockUI (Date startTime, String roomName, int personNum, String roomSizeStr, String positionStr, ArrayList<MeetingInfo> meetingList) {
		roomNameLabel = new JLabel(roomName);
		
		personNumLabel = new JLabel(Integer.toString(personNum));
		
		roomSizeLabel = new JLabel(roomSizeStr);
		
		positionLabel = new JLabel(positionStr);
		
		listPanel = new JPanel(new GridLayout(6, 1));
		
		MeetingSorter meetingSorter = new MeetingSorter();
		Collections.sort(meetingList, meetingSorter);
		Date lastTime = startTime;
		for (MeetingInfo meetingInfo : meetingList) {
			if (meetingInfo.getStartTime().compareTo(lastTime) > 0) {
				BlockCellUI cellPanel = new BlockCellUI(lastTime, meetingInfo.getStartTime(), "");
				listPanel.add(cellPanel);
				lastTime = meetingInfo.getStartTime();
			}
			BlockCellUI cellPabel = new BlockCellUI(meetingInfo.getStartTime(), meetingInfo.getEndTime(), meetingInfo.getUserName());
			listPanel.add(cellPabel);
			lastTime = meetingInfo.getEndTime();
		}
	}
	
}
