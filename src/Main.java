import java.sql.SQLException;

import UI.LoginUI;
import UI.MainUI;
import UI.SignUpUI;

public class Main {
	public static void main(String[] argv) throws ClassNotFoundException, SQLException {
		LoginUI loginUI = new LoginUI("LoginUI", argv[0], argv[1], argv[2]);	
	}
}
	