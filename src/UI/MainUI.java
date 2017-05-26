package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jdesktop.swingx.JXDatePicker;

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
	private JButton freeRoomButton;
	private JButton myMeetingButton;
	private JButton floorMapButton;
	private JButton noticeButton;
	private JTabbedPane floorTabbedPane;
	
	private String buildingName;
	private Date searchDate;
	private boolean isShowFree;
	private String floorName;
	
	public MainUI(String s) {
		super(s);
		this.setSize(1280, 1000);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
		this.initializeUI();
		this.setVisible(true);
	}
	private void initializeUI() {
		panel_1 = new JPanel();
		this.placeComponents();
		getContentPane().add(panel_1);
	}
	
	private void placeComboBox(String[] buildingNames) {
		buildingName = buildingNames[0];
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(buildingNames));
		comboBox.setBounds(106, 17, 150, 39);
		panel_1.add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (e.getStateChange() == ItemEvent.SELECTED) {
					buildingName = e.getItem().toString();
					System.out.println(buildingName);
				}
			}
		});
	}
	
	private void placeDatePicker() {
//		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		datePicker = new JXDatePicker();
		searchDate = new Date();
//		searchDate = dateFormat.format(date);
//		datePicker.setDate(date);
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
//				DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//				searchDate = dateFormat.format(d);
				//JOptionPane.showMessageDialog(panel_1, "获取控件中的日期 :" + d);
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
	
	private void placeFreeTimeButton() {
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
				} else if (freeTimeButton.getText().equals("显示全部时段")) {
					isShowFree = false;
					freeTimeButton.setText("显示空闲时段");
				}
			}
		});
	}
	
	private void placeMyMeetingButton() {
		myMeetingButton = new JButton("我的会议");
		myMeetingButton.setBounds(780, 17, 120, 39);
		panel_1.add(myMeetingButton);
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
	
	private void placeNoticeButton() {
		noticeButton = new JButton("通知");
		noticeButton.setBounds(1040, 17, 120, 39);
		panel_1.add(noticeButton);
		noticeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				NoticeUI noticeUI = new NoticeUI();
			}
		});
	}
	
	private void placeTabbedPane(String [] floorNames) {
		floorName = floorNames[0];
		floorTabbedPane = new JTabbedPane();
		floorTabbedPane.setBounds(0, 60, 1280, 1000);
		
		panel_1.add(floorTabbedPane);
		for (String string : floorNames) {
			floorTabbedPane.addTab(string, new JPanel());
		}
		floorTabbedPane.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				floorName = floorNames[floorTabbedPane.getSelectedIndex()];
				System.out.println(floorName);
			}
		});
	}
	private void placeComponents() {
		panel_1.setLayout(null);
		placeComboBox(new String[] {"深圳国际", "科技园1号楼", "科技园2号楼", "科技园3号楼", "科技园4号楼", "科技园5号楼", "科技园6号楼", "总部", "上研大厦"});
		placeDatePicker();
		placeLabels();
		placeFreeTimeButton();
		placeTabbedPane(new String[] {"F7", "F8", "F9", "F10", "F11", "F16", "F17", "F18", "F19", "F20", "F21", "F22", "培训教室/视频会议室"});
		placeFloorMapButton();
		placeNoticeButton();
		placeMyMeetingButton();
	}
}
