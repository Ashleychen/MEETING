package UI;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MapUI extends JFrame{
	String imgUrlPrefix = "./images";
	public MapUI(String buildingName, String floorName) {
		// TODO Auto-generated constructor stub
		String url = imgUrlPrefix + "/" + buildingName + "_" + floorName + ".jpg";
		JLabel imgLabel = new JLabel(new ImageIcon(url));
		JPanel imgPanel = new JPanel(new BorderLayout());
		imgPanel.add(imgLabel, BorderLayout.CENTER);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(imgPanel, BorderLayout.CENTER);
		this.setSize(400, 300);
		this.setResizable(true);
		this.setTitle("楼层地图");
		this.setVisible(true);
	}
}
