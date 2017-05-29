package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dataControl.DataController;

public class SignUpUI extends JFrame{
	private JTextField userText;
	private JPasswordField passwordText;
	private JButton	registerButton;
	
	private String userName;
	private String password;
	
	private DataController dataController;
	
	public SignUpUI(String s, String dbUserName, String dbPassword, String dbPort) throws ClassNotFoundException, SQLException {
		super(s);
		dataController = new DataController(dbUserName, dbPassword, dbPort);
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
		
		passwordText = new JPasswordField();
		passwordText.setBounds(100, 60, 200, 35);
		panel.add(passwordText);
		
		registerButton = new JButton("register");
		registerButton.setBounds(120, 120, 80, 25);
		panel.add(registerButton);
		
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userName = userText.getText();
				password = passwordText.getText();
				int addUserValue = dataController.addUser(userName, password);
				if (addUserValue == -2) {
					JOptionPane.showMessageDialog(null, "用户已存在");
				} else if (addUserValue == -1) {
					JOptionPane.showMessageDialog(null, "注册错误");
				} else{
					JOptionPane.showMessageDialog(null, "注册成功");
					signUpSuccess();
				}
			}
		});
	}
	
	public void signUpSuccess() {
		this.dispose();
	}
}
