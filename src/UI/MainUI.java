package UI;

import java.awt.ScrollPane;
import java.awt.dnd.DnDConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.JXDatePicker;

import dataControl.DataController;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MainUI extends JFrame{
	private JComboBox placeComboBox;
	private JPanel panel_1;
	private JXDatePicker datePicker;
	private JButton searchButton;
	private JButton freeTimeButton;
	private JButton myMeetingButton;
	private JButton floorMapButton;
	private JButton noticeButton;
	private JTabbedPane floorTabbedPane;
	
	private ArrayList<String> buildingNames;
	private String buildingName;
	private Date searchDate;
	private boolean isShowFree;
	private String floorName;
	private int userID;
	
	private DataController dataController;
	
	public MainUI(String s, int uid, String dbUserName, String dbPassword, String dbPort) throws SQLException, ClassNotFoundException {
		super(s);
		dataController = new DataController(dbUserName, dbPassword, dbPort);
		userID = uid;
		buildingNames = dataController.getBuildingNames();
		buildingName = buildingNames.get(0);
		this.setSize(1280, 1000);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
		this.initializeUI(dbUserName, dbPassword, dbPort);
		this.setVisible(true);
	}
	private void initializeUI(String dbUserName, String dbPassword, String dbPort) throws SQLException, ClassNotFoundException {
		this.placeComponents(dbUserName, dbPassword, dbPort);
		getContentPane().add(panel_1);
	}
	
	private void placeComboBox() {
		JComboBox comboBox = new JComboBox();
		String[] buildingNameArr = buildingNames.toArray(new String[buildingNames.size()]);
		comboBox.setModel(new DefaultComboBoxModel(buildingNameArr));
		comboBox.setBounds(106, 17, 150, 39);
		panel_1.add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					buildingName = e.getItem().toString();
				}
			}
		});
	}
	
	private void placeDatePicker(String dbUserName, String dbPassword, String dbPort) {
//		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		datePicker = new JXDatePicker();
		searchDate = new Date();
//		searchDate = dateFormat.format(date);
		datePicker.setDate(searchDate);
		datePicker.setBounds(330, 17, 150, 39);
		panel_1.add(datePicker);
		
		searchButton = new JButton("搜索");
		searchButton.setBounds(490, 17, 50, 39);
		panel_1.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				searchDate = datePicker.getDate();
				try {
					floorTabbedPane.removeAll();
					ArrayList<String> floorNames = dataController.getFloorNames(buildingName);
					floorName = floorNames.get(0);
					for (String floorName : floorNames) {
						ScrollPane scrollPane = new ScrollPane();
						scrollPane.add(new TabbedPanelUI(buildingName, floorName, searchDate, userID, isShowFree,
								dbUserName, dbPassword, dbPort));
						floorTabbedPane.addTab(floorName, scrollPane);
					}
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});
	}
	
	private void placeLabels() {
		JLabel label = new JLabel("切换区域：");
		label.setBounds(33, 24, 71, 23);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("切换日期：");
		label_1.setBounds(271, 24, 71, 23);
		panel_1.add(label_1);
	}
	
	private void placeFreeTimeButton(String dbUserName, String dbPassword, String dbPort) {
		isShowFree =false;
		freeTimeButton = new JButton("显示空闲时段");
		freeTimeButton.setBounds(620, 17, 120, 39);
		panel_1.add(freeTimeButton);
		freeTimeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (freeTimeButton.getText().equals("显示空闲时段")) {
					isShowFree = true;
					freeTimeButton.setText("显示全部时段");
					try {
						floorTabbedPane.removeAll();
						placeTabbedPane(dbUserName, dbPassword, dbPort);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else if (freeTimeButton.getText().equals("显示全部时段")) {
					isShowFree = false;
					freeTimeButton.setText("显示空闲时段");
					try {
						floorTabbedPane.removeAll();
						placeTabbedPane(dbUserName, dbPassword, dbPort);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
	}
	
	private void placeMyMeetingButton(String dbUserName, String dbPassword, String dbPort) {
		myMeetingButton = new JButton("我的会议");
		myMeetingButton.setBounds(780, 17, 120, 39);
		panel_1.add(myMeetingButton);
		myMeetingButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String[] l = buildingNames.toArray(new String[buildingNames.size()]);
					MyRecordUI myRecordUI = new MyRecordUI(searchDate, l, dbUserName, dbPassword, dbPort);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	
	private void placeFloorMapButton() {
		floorMapButton = new JButton("本楼层地图");
		floorMapButton.setBounds(910, 17, 120, 39);
		panel_1.add(floorMapButton);
		floorMapButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MapUI mapUI = new MapUI(buildingName, floorName);
			}
		});
	}
	
	private void placeNoticeButton(String dbUserName, String dbPassword, String dbPort) {
		noticeButton = new JButton("通知");
		noticeButton.setBounds(1040, 17, 120, 39);
		panel_1.add(noticeButton);
		noticeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					NoticeUI noticeUI = new NoticeUI(dbUserName, dbPassword, dbPort);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
	private void initializeTabbedPane() {
		floorTabbedPane = new JTabbedPane();
		floorTabbedPane.setBounds(0, 60, 1280, 940);	
		panel_1.add(floorTabbedPane);
	}
	private void placeTabbedPane(String dbUserName, String dbPassword, String dbPort) throws SQLException, ClassNotFoundException {
		ArrayList<String> floorNames = dataController.getFloorNames(buildingName);
		floorName = floorNames.get(0);
		for (String floorName : floorNames) {
			ScrollPane scrollPane = new ScrollPane();
			scrollPane.add(new TabbedPanelUI(buildingName, floorName, searchDate, userID, isShowFree,
					dbUserName, dbPassword, dbPort));
			floorTabbedPane.addTab(floorName, scrollPane);
		}
		floorTabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
//				floorName = floorNames.get(floorTabbedPane.getSelectedIndex());
			}
		});
	}
	private void placeComponents(String dbUserName, String dbPassword, String dbPort) throws SQLException, ClassNotFoundException {
		panel_1 = new JPanel();
		panel_1.setLayout(null);
		placeComboBox();
		placeDatePicker(dbUserName, dbPassword, dbPort);
		placeLabels();
		placeFreeTimeButton(dbUserName, dbPassword, dbPort);
		initializeTabbedPane();
		placeTabbedPane(dbUserName, dbPassword, dbPort);
		placeFloorMapButton();
		placeNoticeButton(dbUserName, dbPassword, dbPort);
		placeMyMeetingButton(dbUserName, dbPassword, dbPort);
	}
}
