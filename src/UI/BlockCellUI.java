package UI;

import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataControl.DataController;

public class BlockCellUI extends JPanel {
	private JLabel timeLabel;
	private JLabel userNameLabel;
	private JButton bookingButton;
	
	private DataController dataController;
	
	public BlockCellUI(Date startTime, Date endTime, int userID, String userName, int personNum, 
		String buildingName, String floorName, String roomName, String dbUserName,
		String dbPassword, String dbPort) throws SQLException, ClassNotFoundException {
		dataController = new DataController(dbUserName, dbPassword, dbPort);
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String startTimeStr = dateFormat.format(startTime);
		String endTimeStr = dateFormat.format(endTime);
		timeLabel = new JLabel(startTimeStr + "-" + endTimeStr);
		timeLabel.setBounds(0, 0, 80, 25);
		this.add(timeLabel);
		
		if (userName.equals("")) {
			bookingButton = new JButton("预定");
			bookingButton.setBounds(175, 5, 80, 25);
			this.add(bookingButton);
			bookingButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					try {
						BookingUI bookingUI = new BookingUI(userID, personNum, startTime, buildingName,
							floorName, roomName, dbUserName, dbPassword, dbPort);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		} else {
			userNameLabel = new JLabel(userName);
			timeLabel.setBounds(90, 5, 80, 25);
			this.add(userNameLabel);
		}
	}
}
