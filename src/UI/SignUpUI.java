package UI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SignUpUI extends JFrame{
	private JTextField userText;
	private JTextField passwordText;
	private JButton	registerButton;
	
	public SignUpUI(String s) {
		super(s);
		this.setSize(640, 320);
		this.initializeUI();
		this.setVisible(true);
	}

	private void initializeUI() {
		JPanel panel = new JPanel();
		this.placeComponents(panel);
		this.add(panel);
	}
	
	private void placeComponents(JPanel panel) {
		panel.setLayout(null);
		
		JLabel userLabel = new JLabel("UserName:");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 60, 80, 25);
		panel.add(passwordLabel);
		
		userText = new JTextField();
		userText.setBounds(100, 20, 200, 35);
		panel.add(userText);
		
		passwordText = new JTextField();
		passwordText.setBounds(100, 60, 200, 35);
		panel.add(passwordText);
		
		registerButton = new JButton("register");
		registerButton.setBounds(120, 120, 80, 25);
		panel.add(registerButton);
	}
}
