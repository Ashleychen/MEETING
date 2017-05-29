package UI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXDatePicker;

import data.MeetingInfo;
import dataControl.DataController;

public class MyRecordUI extends JFrame{
	private JLabel dateLabel;
	private JXDatePicker datePicker;
	private JLabel buildingLabel;
	private JComboBox buildingComboBox;
	private JButton searchButton;
	private JTable recordTable;
	private DefaultTableModel recordTableModel;
	
	private Date searchDate;
	private String buildingName;
	
	private DataController dataController;
	public MyRecordUI(Date sd, String[] buildingNames, String dbUserName, String dbPassword, String dbPort) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated constructor stub
		this.setVisible(true);
		this.setSize(700, 400);
		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(null);
		dataController = new DataController(dbUserName, dbPassword, dbPort);
		searchDate = sd;
		buildingName = buildingNames[0];
		String[] columnNames = { "日期",	"开始时间", "结束时间", "会议室名称", "地域",	"楼层",	"会议室描述"};
		Object[][] data = {};
		recordTableModel = new DefaultTableModel(data, columnNames);
		recordTable = new JTable(recordTableModel);
		JScrollPane scrollPane = new JScrollPane(recordTable);
		scrollPane.setSize(700, 200);
		panel.add(scrollPane);
		
		ArrayList<MeetingInfo> myMeetingInfos = dataController.getMyMeetingList(searchDate, buildingName);
		loadTable(myMeetingInfos);
		
		dateLabel = new JLabel("会议日期：");
		dateLabel.setSize(80, 20);
		dateLabel.setLocation(10, 250);
		panel.add(dateLabel);
		datePicker = new JXDatePicker();
		datePicker.setDate(searchDate);
		datePicker.setBounds(80, 240, 150, 39);
		panel.add(datePicker);
		buildingLabel = new JLabel("地域：");
		buildingLabel.setSize(80, 20);
		buildingLabel.setLocation(10, 300);
		panel.add(buildingLabel);
		buildingComboBox = new JComboBox();
		buildingComboBox.setModel(new DefaultComboBoxModel(buildingNames));
		buildingComboBox.setBounds(79, 300, 150, 39);
		panel.add(buildingComboBox);
		buildingComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					buildingName = e.getItem().toString();
				}
			}
		});
		
		searchButton = new JButton("搜索");
		searchButton.setSize(100, 25);
		searchButton.setLocation(600, 340);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					ArrayList<MeetingInfo> myMeetingInfos = dataController.getMyMeetingList(searchDate, buildingName);
					loadTable(myMeetingInfos);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		panel.add(searchButton);
	}
	
	public void loadTable(ArrayList<MeetingInfo> meetingList) {
		recordTableModel.setRowCount(0);
		for (MeetingInfo meetingInfo : meetingList) {
			String[] meetingArr = new String[]{meetingInfo.getDateStr(), meetingInfo.getTimeStr(meetingInfo.getStartTime()),
				meetingInfo.getTimeStr(meetingInfo.getEndTime()), meetingInfo.getRoomName(), meetingInfo.getBuildingName(),
				meetingInfo.getFloorName(), meetingInfo.getTopicText()};
			recordTableModel.addRow(meetingArr);
		}
		//recordTable.invalidate();
	}
}
