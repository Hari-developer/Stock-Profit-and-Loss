package Com.ta.Stock;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class UserPass extends Calculate

{
	static String user;
	static String pass;

	static Properties prob = new Properties();

	static void Userpas() throws IOException {
		input = JOptionPane.showInputDialog("UserName");
		UserName = String.valueOf(input);

		if (prob.keySet().contains(UserName))

		{
			String pass = prob.getProperty(String.valueOf(UserName));
			input = JOptionPane.showInputDialog("password");
			Password = String.valueOf(input);
			if (Password.equals(pass)) {
				JOptionPane.showMessageDialog(null, "Login Sucess", "Login", JOptionPane.INFORMATION_MESSAGE);

				Choose();
			}
			else
			    {
				JOptionPane.showMessageDialog(null, "Not Match", "Error", JOptionPane.INFORMATION_MESSAGE);
			    }
				} 
		else {
			JOptionPane.showMessageDialog(null, "UserName Not Found", "Error", JOptionPane.INFORMATION_MESSAGE);

		}

	}

	public static void main(String args[]) throws IOException {

		FileInputStream fin = new FileInputStream("C:\\New folder\\Stock-Management\\src\\Com\\ta\\Stock\\Stock.properties");
		prob.load(fin);
		Userpas();

	}
}
