package UI;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlockCellUI extends JPanel {
	private JLabel timeLabel;
	private JLabel userNameLabel;
	private JButton bookingButton;
	
	public BlockCellUI(Date startTime, Date endTime, String userName) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		timeLabel = new JLabel(timeStr);
		timeLabel.setBounds(5, 5, 80, 25);
		this.add(timeLabel);
		
		if (userName.length() == 0) {
			bookingButton = new JButton("预定");
			bookingButton.setBounds(175, 5, 80, 25);
			this.add(bookingButton);
		} else {
			userNameLabel = new JLabel(userName);
			timeLabel.setBounds(90, 5, 80, 25);
			this.add(userNameLabel);
		}
	}
}
