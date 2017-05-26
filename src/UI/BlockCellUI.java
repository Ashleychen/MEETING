package UI;

import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BlockCellUI extends JPanel {
	private JLabel timeLabel;
	private JLabel userNameLabel;
	private JButton bookingButton;
	
	public BlockCellUI(Date startTime, Date endTime, String userName, String password, int personNum, Date searchDate,
		String buildingName, String floorName, String roomName) {
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String startTimeStr = dateFormat.format(startTime);
		String endTimeStr = dateFormat.format(endTime);
		timeLabel = new JLabel(startTimeStr + "-" + endTimeStr);
		timeLabel.setBounds(5, 5, 80, 25);
		this.add(timeLabel);
		
		if (userName.length() == 0) {
			bookingButton = new JButton("预定");
			bookingButton.setBounds(175, 5, 80, 25);
			this.add(bookingButton);
			bookingButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					BookingUI bookingUI = new BookingUI(userName, password, personNum, searchDate, buildingName,
						floorName, roomName);
				}
			});
		} else {
			userNameLabel = new JLabel(userName);
			timeLabel.setBounds(90, 5, 80, 25);
			this.add(userNameLabel);
		}
	}
}
