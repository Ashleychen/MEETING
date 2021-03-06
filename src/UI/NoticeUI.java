package UI;

import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dataControl.DataController;

public class NoticeUI extends JFrame{
	private JTextArea noticeTextArea;
	private JScrollPane noticeScrollPane;
	private DataController dataController;
	public NoticeUI(String dbUserName, String dbPassword, String dbPort) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		Double areaWidth = 500d;
		Double areaHeight = 400d;
		Dimension dimension =  Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((int)(dimension.getWidth() - areaWidth) / 2, (int)(dimension.getHeight() - areaHeight) / 2, areaWidth.intValue(), areaHeight.intValue());
		noticeTextArea = new JTextArea(7, 15);
		noticeTextArea.setLineWrap(true);
		noticeScrollPane = new JScrollPane(noticeTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.add(noticeScrollPane);
		this.setVisible(true);
		dataController = new DataController(dbUserName, dbPassword, dbPort);
		noticeTextArea.setText(dataController.getNotice());
	}
}
