package window;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;

public class App_Window {
	private JFrame frame;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu menu, view;
	private JMenuItem menuItem;
	private JCheckBoxMenuItem facebook_checkbox, twitter_checkbox, email_checkbox, blackbord_checkbox;
	private JButton date_button,source_button,from_button,subjet_button,content_button; 

	public App_Window() {
		addFrameContent();
	}

	public void addFrameContent() {
		frame = new JFrame();
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		
		menuBar = new JMenuBar();
		
		facebook_checkbox = new JCheckBoxMenuItem("Facebook");
		twitter_checkbox = new JCheckBoxMenuItem("Twitter");
		email_checkbox = new JCheckBoxMenuItem("Email");
		blackbord_checkbox = new JCheckBoxMenuItem("Blackboard");
		
		panel = new JPanel();
		
		date_button = new JButton("Date");
		source_button = new JButton("Source");
		from_button = new JButton("From");
		subjet_button = new JButton("Subject");
		content_button = new JButton("Content");
		
		menu = new JMenu("Menu");
		view = new JMenu("View");
		
		panel.setLayout(new GridLayout());
		
		view.add(facebook_checkbox);
		view.add(twitter_checkbox);
		view.add(email_checkbox);
		view.add(blackbord_checkbox);
		
				
		menuItem = new JMenuItem("SubMenu");//,se quiser uma imagem new ImageIcon("localiazaçao"));
		
		menu.add(menuItem);
		menuBar.add(menu);
		menuBar.add(view);
		
		panel.add(date_button);
		panel.add(source_button);
		panel.add(from_button);
		panel.add(subjet_button);
		panel.add(content_button);
		frame.add(panel,BorderLayout.NORTH);
		frame.setJMenuBar(menuBar);

		
	}

	public void open() {
		frame.setVisible(true);
	}
}
