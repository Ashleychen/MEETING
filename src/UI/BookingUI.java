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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.JXDatePicker;

import dataControl.DataController;

public class BookingUI extends Panel{
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
	
	private DataController dataController;
	
	public BookingUI(String organizerName, String psw, int personNum, Date startDate,  String bn, String fn, String rn) {
		super();
		dataController = new DataController();
		userName = organizerName;
		password = psw;
		buildingName = bn;
		floorName = fn;
		roomName = rn;
		organizerNameLabel = new JLabel("会议组织者");
		organizerNameLabel.setBounds(10, 20, 80, 25);
		this.add(organizerNameLabel);
		
		organizerNameTextLabel = new JLabel(organizerName);
		organizerNameTextLabel.setBounds(100, 20, 200, 35);
		this.add(organizerNameTextLabel);
		
		topicLabel = new JLabel("会议主题");
		topicLabel.setBounds(10, 45, 80, 25);
		this.add(topicLabel);
		
		topicTextArea = new JTextArea("请填写会议用途！", 7, 15);
		topicTextArea.setLineWrap(true);
		topicScrollPane = new JScrollPane(topicTextArea);
		topicScrollPane.setBounds(100, 45, 300, 200);
		this.add(topicScrollPane);
		
		personNumLabel = new JLabel("与会人数");
		personNumLabel.setBounds(10, 255, 80, 25);
		this.add(personNumLabel);
		
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
				memberNum = (int)(e.getSource());
			}
		});
		
		startDateLabel = new JLabel("开始日期");
		startDateLabel.setBounds(10, 260, 80, 25);
		this.add(startDateLabel);
		
		int minHour = 8;
		int maxHour = 19;
		int minMinute = 0;
		int maxMinute = 59;
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		calendar.set(Calendar.HOUR_OF_DAY, minHour);
		calendar.set(Calendar.MINUTE, minMinute);
		calendar.set(Calendar.SECOND, 0);
		bookingStartTime = calendar.getTime();
		bookingEndTime = calendar.getTime();
		startDatePicker = new JXDatePicker();
		startDatePicker.setDate(bookingStartTime);
		startDatePicker.setBounds(100, 260, 118, 39);
		this.add(startDatePicker);
		
		startDateButton = new JButton("选择");
		startDateButton.setBounds(230, 260, 50, 35);
		this.add(startDateButton);
		startDateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(startDatePicker.getDate());
				calendar.set(Calendar.HOUR_OF_DAY, minHour);
				calendar.set(Calendar.MINUTE, minMinute);
				calendar.set(Calendar.SECOND, 0);
				bookingStartTime = calendar.getTime();
				bookingEndTime = calendar.getTime();
				//JOptionPane.showMessageDialog(this, "获取控件中的日期 :" + d);
			}
		});
		
		startTimeLabel = new JLabel("开始时间");
		startTimeLabel.setBounds(10, 310, 80, 25);
		this.add(startTimeLabel);
		
		SpinnerNumberModel hourSpinnerModel = new SpinnerNumberModel(minHour, minHour, maxHour, 1);
		SpinnerNumberModel minuteSpinnerModel = new SpinnerNumberModel(minMinute, minMinute, maxMinute, 1);
		
		startHourSpinner = new JSpinner(hourSpinnerModel);
		startHourSpinner.setBounds(100, 310, 80, 25);
		this.add(startHourSpinner);
		startHourSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int startHour = (int)(e.getSource());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(bookingStartTime);
				calendar.set(Calendar.HOUR_OF_DAY, startHour);
				bookingStartTime = calendar.getTime();
			}
		});
		startHourLabel = new JLabel("时");
		startHourLabel.setBounds(190, 310, 80, 25);
		this.add(startHourLabel);
		
		startMinuteSpinner = new JSpinner(minuteSpinnerModel);
		startMinuteSpinner.setBounds(280, 310, 80, 25);
		this.add(startHourSpinner);
		startMinuteSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int startMinute = (int)(e.getSource());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(bookingStartTime);
				calendar.set(Calendar.MINUTE, startMinute);
				bookingStartTime = calendar.getTime();
			}
		});
		startMinuteLabel = new JLabel("分");
		startHourLabel.setBounds(370, 310, 80, 25);
		this.add(startMinuteLabel);
		
		endTimeLabel = new JLabel("结束时间");
		endTimeLabel.setBounds(10, 345, 80, 25);
		this.add(endTimeLabel);
		
		endHourSpinner = new JSpinner(hourSpinnerModel);
		endHourSpinner.setBounds(100, 345, 80, 25);
		this.add(endHourSpinner);
		endHourSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int endHour = (int)(e.getSource());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(bookingEndTime);
				calendar.set(Calendar.HOUR_OF_DAY, endHour);
				bookingEndTime = calendar.getTime();
			}
		});
		endHourLabel = new JLabel("时");
		endHourLabel.setBounds(190, 345, 80, 25);
		this.add(endHourLabel);
		
		endMinuteSpinner = new JSpinner(minuteSpinnerModel);
		endMinuteSpinner.setBounds(280, 345, 80, 25);
		this.add(endMinuteSpinner);
		endMinuteSpinner.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				int endMinute = (int)(e.getSource());
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(bookingEndTime);
				calendar.set(Calendar.MINUTE, endMinute);
				bookingEndTime = calendar.getTime();
			}
		});
		endMinuteLabel = new JLabel("分");
		endMinuteLabel.setBounds(370, 345, 80, 25);
		this.add(endMinuteLabel);
		
		submitButton = new JButton("提交");
		submitButton.setBounds(1100, 900, 80, 25);
		this.add(submitButton);
		submitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (dataController.checkUser(userName, password) == 0) {
					JOptionPane.showMessageDialog(null, "用户不存在");
				} else if (bookingStartTime.compareTo(bookingEndTime) >= 0) {
					JOptionPane.showMessageDialog(null, "预定时间设置错误");
				} else {
					try {
						int uid = dataController.getUId(userName, password);
						int roomId = dataController.getRoomId(buildingName, floorName, roomName);
						dataController.addMeetingRecord(uid, bookingStartTime, bookingEndTime, roomId, memberNum, topicTextArea.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
