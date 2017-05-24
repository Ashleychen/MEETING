package UI;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.jdesktop.swingx.JXDatePicker;

public class MyRecordUI extends JPanel{
	private JLabel dateLabel;
	private JXDatePicker datePicker;
	private JLabel buildingLabel;
	private JComboBox buildingComboBox;
	private JButton searchButton;
	private JTable recordTable;
	
	public MyRecordUI() {
		// TODO Auto-generated constructor stub
		String[] columnNames = { "日期",	"开始时间", "结束时间", "会议室名称", "地域",	"楼层",	"会议室描述"};
		Object[][] data = {};
		recordTable = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(recordTable);
		this.add(scrollPane);
	}
}
