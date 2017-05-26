package UI;

import java.awt.Color;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private Date bookingDate;
	
	public BookingUI(String organizerName, int personNum, Date startDate) {
		super();
		
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
		
		Calendar day = Calendar.getInstance();
		day.setTime(startDate);
		day.set(Calendar.HOUR_OF_DAY, 0);
		day.set(Calendar.MINUTE, 0);
		day.set(Calendar.SECOND, 0);
		bookingDate = day.getTime();
		startDatePicker = new JXDatePicker();
		startDatePicker.setDate(bookingDate);
		startDatePicker.setBounds(100, 260, 118, 39);
		this.add(startDatePicker);
		
		startDateButton = new JButton("选择");
		startDateButton.setBounds(230, 260, 50, 35);
		this.add(startDateButton);
		startDateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Calendar day = Calendar.getInstance();
				day.setTime(startDatePicker.getDate());
				day.set(Calendar.HOUR_OF_DAY, 0);
				day.set(Calendar.MINUTE, 0);
				day.set(Calendar.SECOND, 0);
				bookingDate = day.getTime();
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
				
			}
		});
	}
}
