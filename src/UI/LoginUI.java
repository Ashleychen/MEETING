package UI;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.security.KeyStore.PasswordProtection;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dataControl.DataController;

public class LoginUI extends JFrame {
	private JTextField userText;
	private JPasswordField passwordText;
	private JButton loginButton;
	private JButton	registerButton;
	
	private String userName;
	private String password;
	private int userID;
	
	private DataController dataController;
	
	public LoginUI(String s, String dbUserName, String dbPassword, String dbPort) throws ClassNotFoundException, SQLException {
		super(s);
		dataController = new DataController(dbUserName, dbPassword, dbPort);
		this.setSize(640, 320);
		this.initializeUI(dbUserName, dbPassword, dbPort);
		this.setVisible(true);
	}
	private void initializeUI(String dbUserName, String dbPassword, String dbPort) {
		JPanel panel = new JPanel();
		this.placeComponents(panel, dbUserName, dbPassword, dbPort);
		this.add(panel);
	}
	public void loginSuccess(String dbUserName, String dbPassword, String dbPort) throws ClassNotFoundException, SQLException {
		this.dispose();
		MainUI mainUI = new MainUI("会议室管理系统", userID, dbUserName, dbPassword, dbPort);
	}
	
	private void placeComponents(JPanel panel, String dbUserName, String dbPassword, String dbPort) {
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
		
		loginButton = new JButton("login");
		loginButton.setBounds(10, 120, 80, 25);
		panel.add(loginButton);
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userName = userText.getText();
				password = passwordText.getText();
				if (dataController.checkUser(userName, password) == 0) {
					JOptionPane.showMessageDialog(null, "用户名或密码不正确");
				} else {
					try {
						userID = dataController.getUId(userName, password);
						// ((LoginUI)((JButton)e.getSource()).getParent().getParent()).dispose();
						// MainUI mainUI = new MainUI("会议室管理系统", userID);
						loginSuccess(dbUserName, dbPassword, dbPort);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		registerButton = new JButton("register");
		registerButton.setBounds(120, 120, 80, 25);
		panel.add(registerButton);
		
		registerButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					SignUpUI signUpUI = new SignUpUI("注册页面", dbUserName, dbPassword, dbPort);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
