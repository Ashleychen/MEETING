package UI;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlockCellUI extends JPanel {
	private JLabel timeLabel;
	private JLabel userNameLabel;
	private JButton bookingButton;
	
	public BlockCellUI(String timeStr, String userName, boolean isFree) {
		timeLabel = new JLabel(timeStr);
		timeLabel.setBounds(5, 5, 80, 25);
		this.add(timeLabel);
		
		userNameLabel = new JLabel(userName);
		timeLabel.setBounds(90, 5, 80, 25);
		this.add(userNameLabel);
		
		if (isFree) {
			bookingButton = new JButton("预定");
			bookingButton.setBounds(175, 5, 80, 25);
			this.add(bookingButton);
		}
	}
}
