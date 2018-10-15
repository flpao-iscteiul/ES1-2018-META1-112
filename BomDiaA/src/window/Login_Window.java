package window;

import java.awt.BorderLayout;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Login_Window {

	private JFrame frame;
	private JLabel email_label;
	private JLabel pass_label;
	private JTextField email_textfield;
	private JPasswordField pass_textfield;
	private JLabel credenciais_area;
	private JPanel panel_login;

	public Login_Window() {
		addFrameContent();
	}

	public void addFrameContent() {
		frame = new JFrame("Login");
		frame.setSize(400, 150);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		email_label = new JLabel("Email");
		pass_label = new JLabel("Password");

		email_textfield = new JTextField(40);
		pass_textfield = new JPasswordField(40);

		credenciais_area = new JLabel("Escreva as credenciais: ");

		panel_login = new JPanel(new GridLayout(2, 2));

		panel_login.add(email_label);
		panel_login.add(email_textfield);
		panel_login.add(pass_label);
		panel_login.add(pass_textfield);

		frame.add(credenciais_area,BorderLayout.NORTH);
		frame.add(panel_login, BorderLayout.CENTER);
	}

	public void open() {
		frame.setVisible(true);
	}
	
	public void close(){
		frame.setVisible(false);
		frame.dispose();
	}

}
