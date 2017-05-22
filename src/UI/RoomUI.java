package UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RoomUI extends JPanel{
	private JButton roomNameButton;
	private JLabel personNumLabel;
	private JLabel roomSizeLabel;
	private JLabel positionLabel;
	
	public RoomUI(int width, int height, String roomName, int personNum, String roomSize, String position) {
		super();
		this.setSize(width, height);
		
		roomNameButton = new JButton(roomName);
		roomNameButton.setBounds(5, 5, 60, 25);
		this.add(roomNameButton);
		
		personNumLabel = new JLabel(Integer.toString(personNum) + "人");
		personNumLabel.setBounds(70, 5, 40, 35);
		this.add(personNumLabel);
		
		roomSizeLabel = new JLabel(roomSize);
		roomSizeLabel.setBounds(115, 5, 40, 35);
		this.add(roomSizeLabel);
		
		positionLabel = new JLabel(position);
		positionLabel.setBounds(160, 5, 40, 35);
		this.add(positionLabel);
		
	}
}
