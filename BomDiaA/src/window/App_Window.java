package window;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.AbstractButton;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;
import org.w3c.dom.traversal.NodeFilter;
import org.w3c.dom.traversal.NodeIterator;
import org.xml.sax.SAXException;

import email.Email;
import facebook.Connect;
import msg.Message;
import msg.XMLReaderDOM;
import twitter.TwitterApp;
import xml.XML;

public class App_Window {
	private JFrame frame;
	private JPanel panel;
	private JMenuBar menuBar,fromBar,contentBar;
	private JMenu menu, view, menuItemNew,fromMenu,contentMenu;
	private JMenuItem subMenuItemFb, subMenuItemEm, subMenuItemTw, subMenuItemBb;
	private JCheckBoxMenuItem facebook_checkbox, twitter_checkbox, email_checkbox, blackbord_checkbox;
	private JButton date_button, from_button, content_button;
	public JTextArea area;
	private static Element content;
	private static Element root;
	private static File inputFile = new File("DB.xml");
	private static Element node;
	private static String timeStamp;
	private Document doc;
	private List<Message> msgList;
	private List<Message> msgListAux;
	private boolean b, c = true;
	private JTextField fromArea,contentArea;

	public App_Window() throws ParserConfigurationException, SAXException, IOException {
		addFrameContent();
		newEmail();
		makeFacebookPost();
		newTwitter();
		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = dbF.newDocumentBuilder();
		readFile();

		// doc = dB.parse(inputFile);
		// doc.getDocumentElement().normalize();
		// System.out.println("-File Loaded into DOM view-");
		// addContent(doc, "me2@iscte-iul.pt", "eMail", "Delays");
		// readFile(doc);

	}

	public void addFrameContent() {
		frame = new JFrame();
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		area = new JTextArea();

		menuBar = new JMenuBar();
		fromBar = new JMenuBar();
		contentBar = new JMenuBar();
		

		facebook_checkbox = new JCheckBoxMenuItem("Facebook");

		facebook_checkbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();

				if (selected == true) {
					area.setText("");
					for (int i = 0; i<msgListAux.size();i++) {
						if (msgListAux.get(i).getType().contains("Facebook")) {
							msgListAux.remove(i);
						}
					}

					for (Message msg : msgList) {
						if (msg.getType().contains("Facebook")) {
							msgListAux.add(msg);
						}
					}
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString() + "\n");
					}

				}
				if (selected == false) {
					area.setText("");
					for (int i = 0; i<msgListAux.size();i++) {
						if (msgListAux.get(i).getType().contains("Facebook")) {
							msgListAux.remove(i);

						}
					}
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString());

					}

				}
			}
		});

		twitter_checkbox = new JCheckBoxMenuItem("Twitter");

		twitter_checkbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();

				if (selected == true) {
					area.setText("");
					for (int i = 0; i<msgListAux.size();i++) {
						if (msgListAux.get(i).getType().contains("Tweet")) {
							msgListAux.remove(i);
						}
					}

					for (Message msg : msgList) {
						if (msg.getType().contains("Tweet")) {
							msgListAux.add(msg);
						}
					}
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString() + "\n");
					}

				}
				if (selected == false) {
					area.setText("");
					for (int i = 0; i<msgListAux.size();i++) {
						if (msgListAux.get(i).getType().contains("Tweet")) {
							msgListAux.remove(i);

						}
					}
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString());

					}

				}
			}

		});
		email_checkbox = new JCheckBoxMenuItem("Email");
		
		email_checkbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();

				if (selected == true) {
					area.setText("");
					for (int i = 0; i<msgListAux.size();i++) {
						if (msgListAux.get(i).getType().contains("Email")) {
							msgListAux.remove(i);
						}
					}

					for (Message msg : msgList) {
						if (msg.getType().contains("Email")) {
							msgListAux.add(msg);
						}
					}
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString() + "\n");
					}

				}
				if (selected == false) {
					area.setText("");
					for (int i = 0; i<msgListAux.size();i++) {
						if (msgListAux.get(i).getType().contains("Email")) {
							msgListAux.remove(i);

						}
					}
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString());

					}

				}
				
			}
		});
		blackbord_checkbox = new JCheckBoxMenuItem("Blackboard");
		
		blackbord_checkbox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();

				if (selected == true) {
					area.setText("");
					for (int i = 0; i<msgListAux.size();i++) {
						if (msgListAux.get(i).getType().contains("Blackboard")) {
							msgListAux.remove(i);
						}
					}

					for (Message msg : msgList) {
						if (msg.getType().contains("Blackboard")) {
							msgListAux.add(msg);
						}
					}
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString() + "\n");
					}

				}
				if (selected == false) {
					area.setText("");
					for (int i = 0; i<msgListAux.size();i++) {
						if (msgListAux.get(i).getType().contains("Blackboard")) {
							msgListAux.remove(i);

						}
					}
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString());

					}

				}				
			}
		});

		panel = new JPanel();

		date_button = new JButton("Date");
		
		content_button = new JButton("Content");

		date_button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				area.setText("");
				if (b == true) {
					for (int i = msgList.size() - 1; i >= 0; i--) {
						area.setText(area.getText() + msgList.get(i).toString() + "\n");
					}
				}
				if (b == false) {
					for (Message msg : msgList) {
						area.setText(area.getText() + msg.toString() + "\n");
					}
				}
				b = !b;
			}
		});


		menu = new JMenu("Menu");
		view = new JMenu("View");
		fromMenu = new JMenu("From                                                               ");
		contentMenu= new JMenu("Content                                                             ");
		
		
		fromBar.add(fromMenu);
		contentBar.add(contentMenu);
		
		fromArea = new JTextField(20);
		fromArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==e.VK_ENTER){
					area.setText("");
					for (Message msg : msgList) {
						if (msg.getFrom().contains(fromArea.getText())) {
							area.setText(area.getText() + msg.toString()+"\n");
						}
					}
					fromArea.setText("");
				}
			}
		});
		
		contentArea = new JTextField(20);
		contentArea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==e.VK_ENTER){
					area.setText("");
					for (Message msg : msgList) {
						if (msg.getText().contains(contentArea.getText())) {
							area.setText(area.getText() + msg.toString()+"\n");
						}
					}
					contentArea.setText("");
				}				
			}
		});
		
		fromMenu.add(fromArea);
		contentMenu.add(contentArea);

		panel.setLayout(new GridLayout());

		view.add(facebook_checkbox);
		view.add(twitter_checkbox);
		view.add(email_checkbox);
		view.add(blackbord_checkbox);

		menuItemNew = new JMenu("  New  ");// ,se quiser uma imagem new ImageIcon("localiazaçao"));

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
		panel.add(fromBar);
		panel.add(contentBar);

		frame.add(panel, BorderLayout.NORTH);
		frame.add(area, BorderLayout.CENTER);
		frame.setJMenuBar(menuBar);

	}

	public void newEmail() {
		subMenuItemEm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frameEmail = new JFrame("New Email");
				frameEmail.setSize(400, 300);
				frameEmail.setLocationRelativeTo(frame);
				frameEmail.setLayout(new GridLayout(4, 4));
				frameEmail.setVisible(true);

				JLabel toLabel = new JLabel("To ");
				JLabel subjectLabel = new JLabel("Subject ");
				JLabel bodyLabel = new JLabel("Body Content");

				JTextField toText = new JTextField();
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
				panelHolder.setLayout(new GridLayout(1, 2));
				JPanel panelHolder2 = new JPanel();
				panelHolder2.setLayout(new GridLayout(1, 3));
				JPanel panelHolder3 = new JPanel();
				panelHolder3.setLayout(new GridLayout(1, 4));
				JPanel panelHolder4 = new JPanel();
				panelHolder4.setLayout(new GridLayout(1, 3));
				frameFacebook.setLayout(new GridLayout(4, 1));
				frameFacebook.setLocationRelativeTo(frame);
				frameFacebook.setVisible(true);

				JLabel postMessage = new JLabel("Insert Message ");
				JLabel postLink = new JLabel("Insert Message & Link");
				JLabel postImage = new JLabel("Insert Image Name & Directory & Message");

				JTextField simpleMessage = new JTextField();
				JTextField linkMessage = new JTextField();
				JTextField link = new JTextField();
				JTextField imageName = new JTextField();
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

						if (!simpleMessage.getText().equals(null)) {

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

						if (!link.getText().equals(null)) {
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

						if (!imageName.getText().equals(null) && !directory.getText().equals(null)) {

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
					}
				});

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
				frameTwitter.setLayout(new GridLayout(2, 2));
				frameTwitter.setVisible(true);

				JLabel msgLabel = new JLabel("  Mensagem: ");

				JTextField msgText = new JTextField();

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


	public void readFile() {
		String filePath = "DB.xml";
		File xmlFile = new File(filePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("Message");
			// now XML is loaded as Document in memory, lets convert it to Object List
			msgListAux = new ArrayList<Message>();
			msgList = new ArrayList<Message>();
			for (int i = 0; i < nodeList.getLength(); i++) {
				msgList.add(getMessage(nodeList.item(i)));
			}
			// lets print Employee list information
			for (Message msg : msgList) {
				area.setText(area.getText() + "\n" + msg.toString());
			}
		} catch (SAXException | ParserConfigurationException | IOException e1) {
			e1.printStackTrace();
		}

	}

	private static Message getMessage(Node node) {
		// XMLReaderDOM domReader = new XMLReaderDOM();
		Message msg = new Message();
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) node;
			msg.setDate(getTagValue("Date", element));
			msg.setFrom(getTagValue("From", element));
			msg.setType(getTagValue("Type", element));
			msg.setText(getTagValue("Text", element));
		}

		return msg;
	}

	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	public void open() {
		frame.setVisible(true);
	}
}