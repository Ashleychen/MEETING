package UI;

import java.awt.GridLayout;
import java.security.KeyStore.PasswordProtection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class LoginUI extends JFrame {
	private JTextField userNameTextField;
	private JTextField passwordTextField;
	private JButton loginButton;
	private JButton	registerButton;
	
	public LoginUI() {
		userNameTextField = new JTextField();
		passwordTextField = new JTextField();
		loginButton = new JButton("Login");
		registerButton = new JButton("Regist");
		
		this.getContentPane().setLayout(new GridLayout(4, 1));
		this.getContentPane().add(userNameTextField);
		this.getContentPane().add(passwordTextField);
		this.getContentPane().add(loginButton);
		this.getContentPane().add(registerButton);
	}
}
