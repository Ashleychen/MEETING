package UI;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.JXDatePicker;

import dataControl.DataController;

public class BookingUI extends JFrame{
	private JLabel organizerNameLabel;
	private JLabel organizerNameTextLabel;
	private JLabel topicLabel;
	private JTextArea topicTextArea;
	private JScrollPane topicScrollPane;
	private JLabel personNumLabel;
	private JSpinner personNumSpinner;
	private JLabel startDateLabel;
	private JXDatePicker startDatePicker;
	private JButton startDateButton;
	private JLabel startTimeLabel;
	private JSpinner startHourSpinner;
	private JLabel startHourLabel;
	private JSpinner startMinuteSpinner;
	private JLabel startMinuteLabel;
	private JLabel endTimeLabel;
	private JSpinner endHourSpinner;
	private JLabel endHourLabel;
	private JSpinner endMinuteSpinner;
	private JLabel endMinuteLabel;
	private JButton submitButton;
	
	private String topicText;
	private int memberNum;
	private Date bookingStartTime;
	private Date bookingEndTime;
	private String userName;
	private String password;
	private String buildingName;
	private String floorName;
	private String roomName;
	private int startHour;
	private int endHour;
	private int startMinute;
	private int endMinute;
	
	private DataController dataController;
	
	public BookingUI(int userID, int personNum, Date startDate,  String bn, String fn, String rn,
			String dbUserName, String dbPassword, String dbPort) throws SQLException, ClassNotFoundException {
		super();
		this.setVisible(true);
		this.setSize(450, 500);
		
		JPanel panel = new JPanel();
		this.placeComponents(panel, userID, personNum, startDate, bn, fn, rn, dbUserName, dbPassword, dbPort);
		getContentPane().add(panel);
	}
	
	public void placeComponents(JPanel panel, int userID, int personNum, Date startDate, 
			String bn, String fn, String rn, String dbUserName,
			String dbPassword, String dbPort) throws SQLException, ClassNotFoundException {
		panel.setLayout(null);
		dataController = new DataController(dbUserName, dbPassword, dbPort);
		userName = dataController.getUserName(userID);
		buildingName = bn;
		floorName = fn;
		roomName = rn;
		organizerNameLabel = new JLabel("会议组织者");
		organizerNameLabel.setBounds(10, 20, 80, 25);
		panel.add(organizerNameLabel);
		
		organizerNameTextLabel = new JLabel(userName);
		organizerNameTextLabel.setBounds(100, 20, 200, 35);
		getContentPane().add(organizerNameTextLabel);
		
		topicLabel = new JLabel("会议主题");
		topicLabel.setBounds(10, 45, 80, 25);
		panel.add(topicLabel);
		
		topicTextArea = new JTextArea("请填写会议用途！", 7, 15);
		topicTextArea.setLineWrap(true);
		topicScrollPane = new JScrollPane(topicTextArea);
		topicScrollPane.setBounds(100, 45, 300, 200);
		panel.add(topicScrollPane);
		
		personNumLabel = new JLabel("与会人数");
		personNumLabel.setBounds(10, 255, 80, 25);
		panel.add(personNumLabel);
		
		SpinnerNumberModel personNumSpinnerModel = new SpinnerNumberModel(personNum, 1, personNum, 1);
		memberNum = personNum;
		personNumSpinner = new JSpinner(personNumSpinnerModel);
		personNumSpinner.setBounds(100, 255, 80, 25);
		JFormattedTextField formattedTextField = ((JSpinner.DefaultEditor)personNumSpinner.getEditor()).getTextField();
		formattedTextField.setEditable(false);
		formattedTextField.setBackground(Color.white);
		personNumSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				memberNum = (int) ((JSpinner)(e.getSource())).getValue();
			}
		});
		panel.add(personNumSpinner);
		
		startDateLabel = new JLabel("开始日期");
		startDateLabel.setBounds(10, 280, 80, 25);
		panel.add(startDateLabel);
		
		int minHour = 8;
		int maxHour = 19;
		int minMinute = 0;
		int maxMinute = 59;
		startHour = minHour;
		startMinute = minMinute;
		endHour = maxHour;
		endMinute = minMinute;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(Calendar.HOUR_OF_DAY, minHour);
		calendar.set(Calendar.MINUTE, minMinute);
		calendar.set(Calendar.SECOND, 0);
		bookingStartTime = calendar.getTime();
		bookingEndTime = calendar.getTime();
		startDatePicker = new JXDatePicker();
		startDatePicker.setDate(bookingStartTime);
		startDatePicker.setBounds(100, 280, 118, 39);
		panel.add(startDatePicker);
		
		
		startTimeLabel = new JLabel("开始时间");
		startTimeLabel.setBounds(10, 330, 80, 25);
		panel.add(startTimeLabel);
		
		SpinnerNumberModel startHourSpinnerModel = new SpinnerNumberModel(minHour, minHour, maxHour, 1);
		SpinnerNumberModel startMinuteSpinnerModel = new SpinnerNumberModel(minMinute, minMinute, maxMinute, 1);
		
		startHourSpinner = new JSpinner(startHourSpinnerModel);
		startHourSpinner.setBounds(100, 330, 80, 25);
		panel.add(startHourSpinner);
		startHourSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				startHour = (int) ((JSpinner)(e.getSource())).getValue();
			}
		});
		startHourLabel = new JLabel("时");
		startHourLabel.setBounds(190, 330, 80, 25);
		panel.add(startHourLabel);
		
		startMinuteSpinner = new JSpinner(startMinuteSpinnerModel);
		startMinuteSpinner.setBounds(280, 330, 80, 25);
		panel.add(startMinuteSpinner);
		startMinuteSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				startMinute = (int) ((JSpinner)(e.getSource())).getValue();
			}
		});
		startMinuteLabel = new JLabel("分");
		startMinuteLabel.setBounds(370, 330, 80, 25);
		panel.add(startMinuteLabel);
		
		SpinnerNumberModel endHourSpinnerModel = new SpinnerNumberModel(minHour, minHour, maxHour, 1);
		SpinnerNumberModel endMinuteSpinnerModel = new SpinnerNumberModel(minMinute, minMinute, maxMinute, 1);

		endTimeLabel = new JLabel("结束时间");
		endTimeLabel.setBounds(10, 365, 80, 25);
		panel.add(endTimeLabel);
		
		endHourSpinner = new JSpinner(endHourSpinnerModel);
		endHourSpinner.setBounds(100, 365, 80, 25);
		panel.add(endHourSpinner);
		endHourSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				endHour = (int) ((JSpinner)(e.getSource())).getValue();
			}
		});
		endHourLabel = new JLabel("时");
		endHourLabel.setBounds(190, 365, 80, 25);
		panel.add(endHourLabel);
		
		endMinuteSpinner = new JSpinner(endMinuteSpinnerModel);
		endMinuteSpinner.setBounds(280, 365, 80, 25);
		panel.add(endMinuteSpinner);
		endMinuteSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				endMinute = (int) ((JSpinner)(e.getSource())).getValue();
			}
		});
		endMinuteLabel = new JLabel("分");
		endMinuteLabel.setBounds(370, 365, 80, 25);
		panel.add(endMinuteLabel);
		
		submitButton = new JButton("提交");
		submitButton.setBounds(300, 440, 80, 25);
		panel.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(startDatePicker.getDate());
				calendar.set(Calendar.HOUR_OF_DAY, startHour);
				calendar.set(Calendar.MINUTE, startMinute);
				bookingStartTime = calendar.getTime();
				calendar.set(Calendar.HOUR_OF_DAY, endHour);
				calendar.set(Calendar.MINUTE, endMinute);
				bookingEndTime = calendar.getTime();
				if (bookingStartTime.compareTo(bookingEndTime) >= 0) {
					JOptionPane.showMessageDialog(null, "预定时间设置错误");
				//} else if (dataController.checkUser(userName, password) == 0) {
					//JOptionPane.showMessageDialog(null, "用户不存在");
				} else {
					try {
						int uid = userID;
						int roomId = dataController.getRoomId(buildingName, floorName, roomName);
						topicText = topicTextArea.getText();
						dataController.addMeetingRecord(uid, bookingStartTime, bookingEndTime, roomId, memberNum, topicText);
						JOptionPane.showMessageDialog(null, "预定成功");
						bookSuccess();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	public void bookSuccess() {
		this.dispose();
	}
	
			
}
