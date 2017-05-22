package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
	private JTabbedPane typeTabbedPane;
	
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
	
	private void placeComponents() {
		panel_1.setLayout(null);
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"深圳国际", "科技园1号楼", "科技园2号楼", "科技园3号楼", "科技园4号楼", "科技园5号楼", "科技园6号楼", "总部", "上研大厦"}));
		comboBox.setBounds(116, 17, 118, 39);
		panel_1.add(comboBox);
		
		JLabel label = new JLabel("切换区域：");
		label.setBounds(33, 24, 71, 23);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("切换日期：");
		label_1.setBounds(261, 24, 71, 23);
		panel_1.add(label_1);
		
		datePicker = new JXDatePicker();
		Date date = new Date();
		datePicker.setDate(date);
		datePicker.setBounds(320, 17, 118, 39);
		panel_1.add(datePicker);
		
		searchButton = new JButton("搜索");
		searchButton.setBounds(450, 17, 50, 39);
		panel_1.add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Date d = datePicker.getDate();
				JOptionPane.showMessageDialog(panel_1, "获取控件中的日期 :" + d);
			}
		});
		
		freeTimeButton = new JButton("显示空闲时段");
		freeTimeButton.setBounds(520, 17, 120, 39);
		panel_1.add(freeTimeButton);
		freeTimeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (freeTimeButton.getText().equals("显示空闲时段")) {
					freeTimeButton.setText("显示全部时段");
				} else if (freeTimeButton.getText().equals("显示全部时段")) {
					freeTimeButton.setText("显示空闲时段");
				}
			}
		});
		
		freeRoomButton = new JButton("显示空闲会议室");
		freeRoomButton.setBounds(650, 17, 120, 39);
		panel_1.add(freeRoomButton);
		
		myMeetingButton = new JButton("我的会议");
		myMeetingButton.setBounds(780, 17, 120, 39);
		panel_1.add(myMeetingButton);
		
		floorMapButton = new JButton("本楼层地图");
		floorMapButton.setBounds(910, 17, 120, 39);
		panel_1.add(floorMapButton);
		
		noticeButton = new JButton("通知");
		noticeButton.setBounds(1040, 17, 120, 39);
		panel_1.add(noticeButton);
		
		typeTabbedPane = new JTabbedPane();
		typeTabbedPane.setBounds(0, 60, 1280, 1000);
		String[] typeStrings = {"F7", "F8", "F9", "F10", "F11", "F16", "F17", "F18", "F19", "F20", "F21", "F22", "培训教室/视频会议室"};
		for (String string : typeStrings) {
			typeTabbedPane.addTab(string, new JPanel());
		}
		panel_1.add(typeTabbedPane);
	}
}
