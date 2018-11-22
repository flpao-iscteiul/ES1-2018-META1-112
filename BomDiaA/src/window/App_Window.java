package window;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

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
import javax.swing.JTextField;

import email.Email;
import facebook.Connect;
import twitter.TwitterApp;

public class App_Window {
	private JFrame frame;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu menu, view,menuItemNew;
	private JMenuItem  subMenuItemFb, subMenuItemEm,subMenuItemTw,subMenuItemBb;
	private JCheckBoxMenuItem facebook_checkbox, twitter_checkbox, email_checkbox, blackbord_checkbox;
	private JButton date_button,source_button,from_button,subjet_button,content_button; 

	public App_Window() {
		addFrameContent();
		newEmail();
		makeFacebookPost();
		newTwitter();
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
		
				
		menuItemNew = new JMenu("  New  ");//,se quiser uma imagem new ImageIcon("localiazaçao"));
		
		subMenuItemFb = new JMenuItem("Facebook");
		subMenuItemEm = new JMenuItem("Email");
		subMenuItemTw = new JMenuItem("Twitter");
		
		menuItemNew.add(subMenuItemFb);
		menuItemNew.add(subMenuItemEm);
		menuItemNew.add(subMenuItemTw);
		
		
		menu.add(menuItemNew);
		
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
	
	public void newEmail() {
		subMenuItemEm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameEmail = new JFrame("New Email");
				frameEmail.setSize(400, 300);
				frameEmail.setLocationRelativeTo(frame);
				frameEmail.setLayout(new GridLayout(4,4));
				frameEmail.setVisible(true);
				
				JLabel toLabel = new JLabel("To ");
				JLabel subjectLabel = new JLabel("Subject ");
				JLabel bodyLabel = new JLabel("Body Content");
				
				JTextField toText= new JTextField();
				JTextField subjectText = new JTextField();
				JTextField bodyText = new JTextField();
				
				JButton sendButton = new JButton("Send");
				sendButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						Email w = new Email();
						w.sendEmail(toText.getText(), subjectText.getText(), bodyText.getText());
					}
				});
				
				frameEmail.add(toLabel);
				frameEmail.add(toText);
				frameEmail.add(subjectLabel);
				frameEmail.add(subjectText);
				frameEmail.add(bodyLabel);
				frameEmail.add(bodyText);
				frameEmail.add(sendButton);
				
			}
		});
	}
	
	public void makeFacebookPost() {
		subMenuItemFb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameFacebook = new JFrame("New Facebook Post");
				frameFacebook.setSize(1000, 200);
				JPanel panelHolder = new JPanel();    
				panelHolder.setLayout(new GridLayout(1,2));
				JPanel panelHolder2 = new JPanel();    
				panelHolder2.setLayout(new GridLayout(1,3));
				JPanel panelHolder3 = new JPanel();    
				panelHolder3.setLayout(new GridLayout(1,4));
				JPanel panelHolder4 = new JPanel();    
				panelHolder4.setLayout(new GridLayout(1,3));
				frameFacebook.setLayout(new GridLayout(4, 1));
				frameFacebook.setLocationRelativeTo(frame);
				frameFacebook.setVisible(true);

				JLabel postMessage = new JLabel("Insert Message ");
				JLabel postLink = new JLabel("Insert Message & Link");
				JLabel postImage = new JLabel("Insert Image Name & Directory & Message");

				JTextField simpleMessage= new JTextField();
				JTextField linkMessage = new JTextField();
				JTextField link = new JTextField();
				JTextField imageName= new JTextField();
				JTextField directory = new JTextField();
				JTextField imageMessage = new JTextField();

				JButton sendPost = new JButton("SendMessage");
				JButton sendPost2 = new JButton("SendLink");
				JButton sendPost3 = new JButton("SendImage");
				sendPost.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Connect c = new Connect();
						c.establishConnection();

						if(!simpleMessage.getText().equals(null)) {

							c.publishMessageStatus(simpleMessage.getText());
							simpleMessage.setText(null);
						}
					}
				});

				sendPost2.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Connect c = new Connect();
						c.establishConnection();

						if(!link.getText().equals(null)) {
							System.out.println("Trying to publish");
							c.publishLinkStatus(linkMessage.getText(), link.getText());
							System.out.println("Done");
							linkMessage.setText(null);
							link.setText(null);
						}

					}
				});

				sendPost3.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Connect c = new Connect();
						c.establishConnection();

						if(!imageName.getText().equals(null) && !directory.getText().equals(null)) {

							try {
								c.publishImageStatus(imageName.getText(), directory.getText(), imageMessage.getText());
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							imageName.setText(null);
							directory.setText(null);
							imageMessage.setText(null);

						}
					}});

				panelHolder.add(postMessage);
				panelHolder.add(simpleMessage);
				panelHolder2.add(postLink);
				panelHolder2.add(linkMessage);
				panelHolder2.add(link);
				panelHolder3.add(postImage);
				panelHolder3.add(imageName);
				panelHolder3.add(directory);
				panelHolder3.add(imageMessage);
				panelHolder4.add(sendPost);
				panelHolder4.add(sendPost2);
				panelHolder4.add(sendPost3);
				frameFacebook.add(panelHolder);
				frameFacebook.add(panelHolder2);
				frameFacebook.add(panelHolder3);
				frameFacebook.add(panelHolder4);
			}
		});
	}

	public void newTwitter() {
		subMenuItemTw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameTwitter = new JFrame("New Twitter");
				frameTwitter.setSize(400, 300);
				frameTwitter.setLocationRelativeTo(frame);
				frameTwitter.setLayout(new GridLayout(2,2));
				frameTwitter.setVisible(true);
				
				JLabel msgLabel = new JLabel("  Mensagem: ");
				
				JTextField msgText= new JTextField();
				
				
				JButton sendButton = new JButton("Send");
				sendButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						TwitterApp w = new TwitterApp();
						w.sendTwitter(msgText.getText());
					}
				});
				
				frameTwitter.add(msgLabel);
				frameTwitter.add(msgText);
				
				frameTwitter.add(sendButton);
				
			}
		});
	}
	
	public void open() {
		frame.setVisible(true);
	}
}