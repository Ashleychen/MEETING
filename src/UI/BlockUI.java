package UI;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlockUI extends JPanel {
	public BlockUI() {
	}
	private JButton mapButton;
	private JLabel personNumLabel;
	private JLabel roomSizeLabel;
	private JLabel positionLabel;
	private JPanel listPanel;
	
	public BlockUI (String roomName, String personNumStr, String roomSizeStr, String positionStr) {
		mapButton = new JButton(roomName);
		
		personNumLabel = new JLabel(personNumStr);
		
		roomSizeLabel = new JLabel(roomSizeStr);
		
		positionLabel = new JLabel(positionStr);
		
		listPanel = new JPanel(new GridLayout(6, 1));
	}
	
	public void addRecord() {
		
	}
}
