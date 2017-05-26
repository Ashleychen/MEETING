package UI;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import data.MeetingInfo;

public class BlockUI extends JPanel {
	public BlockUI() {
	}
	private JButton mapButton;
	private JLabel personNumLabel;
	private JLabel roomSizeLabel;
	private JLabel positionLabel;
	private JPanel listPanel;
	
	public BlockUI (String roomName, int personNum, String roomSizeStr, String positionStr, ArrayList<MeetingInfo> meetingList) {
		mapButton = new JButton(roomName);
		
		personNumLabel = new JLabel(Integer.toString(personNum));
		
		roomSizeLabel = new JLabel(roomSizeStr);
		
		positionLabel = new JLabel(positionStr);
		
		listPanel = new JPanel(new GridLayout(6, 1));
		
		for (MeetingInfo meetingInfo : meetingList) {
			
		}
	}
	
	public void addRecord() {
		
	}
}
