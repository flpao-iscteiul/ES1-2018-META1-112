package BDA.window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.mail.MessagingException;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import BDA.email.Email;
import BDA.facebook.Connect;
import BDA.msg.Message;
import BDA.msg.SocialMessage;
import BDA.twitter.TwitterApp;

/**
 * Main application window class This class is used to initialize and manage the
 * graphical user's main interface
 * 
 * @author Francisco
 * @version Release
 */
public class App_Window {

	/**
	 * Main frame in wich all the other objects will be mounted on
	 */
	private JFrame frame;
	/**
	 * Panel on which the menu and buttons are mounted
	 */
	private JPanel panel;
	/**
	 * Respective menus to mount on the panel
	 */
	private JMenu menu, view, menuItemNew, fromMenu, contentMenu;
	/**
	 * Respective menu bars for improveduser interface interaction
	 */
	private JMenuBar menuBar, fromBar, contentBar;
	/**
	 * Respective menu items for improved user interface interaction
	 */
	private JMenuItem subMenuItemFb, subMenuItemEm, subMenuItemTw;
	/**
	 * Respective check boxes for improved user interface interaction
	 */
	private JCheckBoxMenuItem facebook_checkbox, twitter_checkbox, email_checkbox, blackbord_checkbox;
	/**
	 * Respective buttons for improved user interface interaction
	 */
	private JButton date_button;
	/**
	 * Search bar fields
	 */
	private JTextField fromField, contentField;
	/**
	 * Scroll bar
	 */
	private JScrollPane scroll;
	/**
	 * Main staging are where the database will be printed on
	 */
	private JTextArea area;
	/**
	 * Respective Element nodes where information is stored in the XML file
	 * 
	 * @see Element
	 */
	private static Element msg, root, node;
	/**
	 * Time stamp to be used as Node id in the XML file with the specific format
	 * yyyy-MM-dd HH:mm:ss
	 */
	private static String timeStamp;
	/**
	 * Message Lists used to store messages
	 */
	private List<Message> msgList, msgListAux;
	public List<Message> getMsgList() {
		return msgList;
	}

	/**
	 * XML file database
	 */
	private Document doc;
	/**
	 * Date sorting auxiliary boolean
	 */
	private boolean b;

	/**
	 * Application Window constructor
	 * 
	 * @throws ParserConfigurationException when parser is not configurated
	 * @throws SAXException                 when SAX fails
	 * @throws IOException                  when IO fails
	 * @throws MessagingException           when message fails
	 */
	public App_Window()  {
		msgListAux = new ArrayList<Message>();
		msgList = new ArrayList<Message>();
		addFrameContent();
		newEmail();
		makeFacebookPost();
		newTwitter();
		try {
			writeToFile();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch bloc
			e.printStackTrace();
		}
		readFile();

	}

	/**
	 * Method used to create and manipulate the main frame
	 */
	public void addFrameContent() {
		frame = new JFrame();
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		area = new JTextArea();
		scroll = new JScrollPane(area);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		menuBar = new JMenuBar();
		fromBar = new JMenuBar();
		contentBar = new JMenuBar();

		facebook_checkbox = new JCheckBoxMenuItem("Facebook");

		facebook_checkbox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
				boolean selected = abstractButton.getModel().isSelected();
area.setText("");
				if (selected == true) {
					area.setText("");
					for (int i = 0; i < msgListAux.size(); i++) {
						if (msgListAux.get(i).getType().contains("Facebook")) {
							msgListAux.remove(i);
						}
					}
					for (Message msg : msgList) {
						if (msg.getType().contains("Facebook")) {
							msgListAux.add(msg);
						}
					}
					area.setText("");
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString() + "\n");
					}

				}
				if (selected == false) {
					area.setText("");
					for (int i = 0; i < msgListAux.size(); i++) {
						if (msgListAux.get(i).getType().contains("Facebook")) {
							msgListAux.remove(i);

						}
					}
					area.setText("");
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString()+ "\n");

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
				area.setText("");

				if (selected == true) {
					area.setText("");
					for (int i = 0; i < msgListAux.size(); i++) {
						if (msgListAux.get(i).getType().contains("Tweet")) {
							msgListAux.remove(i);
						}
					}

					for (Message msg : msgList) {
						if (msg.getType().contains("Tweet")) {
							msgListAux.add(msg);
						}
					}
					area.setText("");
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString() + "\n");
					}

				}
				if (selected == false) {
					area.setText("");
					for (int i = 0; i < msgListAux.size(); i++) {
						if (msgListAux.get(i).getType().contains("Tweet")) {
							msgListAux.remove(i);

						}
					}
					area.setText("");
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString()+ "\n");

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
				area.setText("");

				if (selected == true) {
					area.setText("");
					for (int i = 0; i < msgListAux.size(); i++) {
						if (msgListAux.get(i).getType().contains("eMail")) {
							msgListAux.remove(i);
						}
					}

					for (Message msg : msgList) {
						if (msg.getType().contains("eMail")) {
							msgListAux.add(msg);
						}
					}
					area.setText("");
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString() + "\n");
					}

				}
				if (selected == false) {
					area.setText("");
					for (int i = 0; i < msgListAux.size(); i++) {
						if (msgListAux.get(i).getType().contains("eMail")) {
							msgListAux.remove(i);

						}
					}
					area.setText("");
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString()+"\n");

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
				area.setText("");

				if (selected == true) {
					area.setText("");
					for (int i = 0; i < msgListAux.size(); i++) {
						if (msgListAux.get(i).getType().contains("Blackboard")) {
							msgListAux.remove(i);
						}
					}

					for (Message msg : msgList) {
						if (msg.getType().contains("Blackboard")) {
							msgListAux.add(msg);
						}
					}
					area.setText("");
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString() + "\n");
					}

				}
				if (selected == false) {
					area.setText("");
					for (int i = 0; i < msgListAux.size(); i++) {
						if (msgListAux.get(i).getType().contains("Blackboard")) {
							msgListAux.remove(i);

						}
					}
					area.setText("");
					for (Message msg : msgListAux) {
						area.setText(area.getText() + msg.toString()+ "\n");

					}

				}
			}
		});

		panel = new JPanel();

		date_button = new JButton("Date");

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
		contentMenu = new JMenu("Content                                                             ");

		fromBar.add(fromMenu);
		contentBar.add(contentMenu);

		fromField = new JTextField(20);
		fromField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					area.setText("");
					for (Message msg : msgList) {
						if (msg.getFrom().contains(fromField.getText())) {
							area.setText(area.getText() + msg.toString() + "\n");
						}
					}
					fromField.setText("");
				}
			}
		});

		contentField = new JTextField(20);
		contentField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					area.setText("");
					for (Message msg : msgList) {
						if (msg.getText().contains(contentField.getText())) {
							area.setText(area.getText() + msg.toString() + "\n");
						}
					}
					contentField.setText("");
				}
			}
		});

		fromMenu.add(fromField);
		contentMenu.add(contentField);

		panel.setLayout(new GridLayout());

		view.add(facebook_checkbox);
		view.add(twitter_checkbox);
		view.add(email_checkbox);
		view.add(blackbord_checkbox);

		menuItemNew = new JMenu("  New  ");

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
		frame.add(scroll, BorderLayout.CENTER);
		frame.setJMenuBar(menuBar);
	}

	/**
	 * Email interaction with the graphical user interface
	 */
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

	/**
	 * Facebook interaction with the graphical user interface
	 */
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
							c.publishLinkStatus(linkMessage.getText(), link.getText());
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

	/**
	 * Twitter interaction with the graphical user interface
	 */
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

	/**
	 * This method parses the XML database file onto a DOM View to later be
	 * manipulated
	 */
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

			
			for (int i = 0; i < nodeList.getLength(); i++) {
				msgList.add(getMessage(nodeList.item(i)));
			}
			for (Message msg : msgList) {
				area.setText(area.getText() + "\n" + msg.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to get a certain node from de XML database file as a
	 * Message object
	 * 
	 * @param node selected node to be transformed to Message object
	 * @return Message
	 */
	private static Message getMessage(Node node) {
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

	/**
	 * Method used to get the tag value from an Element node
	 * 
	 * @param tag     tag selected to extract the value from
	 * @param element selected element to extract the tag value from
	 * @return tag value
	 */
	private static String getTagValue(String tag, Element element) {
		NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nodeList.item(0);
		return node.getNodeValue();
	}

	/**
	 * This method adds a Message to the XML database file
	 * 
	 * @param doc         database destination
	 * @param from        message address
	 * @param type        message type
	 * @param contentText message contents
	 */
	public static void addMessage(Document doc, String from, String type, String contentText) {
		root = doc.getDocumentElement();
		root.appendChild(getMessage(doc, getTime(), from, type, contentText));
	}

	/**
	 * Returns the time stamp to be used as Node attribute in the XML file with the
	 * specific format yyyy-MM-dd HH:mm:ss
	 * 
	 * @return timeStamp in the specified format
	 */
	public static String getTime() {
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	/**
	 * This method returns a specific Message node from the XML database file to be
	 * manipulated as a Node
	 * 
	 * @param doc         source XML database file
	 * @param time        message date
	 * @param from        message address
	 * @param type        message type
	 * @param contentText message contents as text
	 * @return message Node
	 * @see Node
	 */
	private static Node getMessage(Document doc, String time, String from, String type, String contentText) {
		msg = doc.createElement("Message");
		msg.appendChild(getMessageElements(doc, msg, "Date", time));
		msg.appendChild(getMessageElements(doc, msg, "From", from));
		msg.appendChild(getMessageElements(doc, msg, "Type", type));
		msg.appendChild(getMessageElements(doc, msg, "Text", contentText));
		return msg;
	}

	/**
	 * This method returns a specific Message Element node from the XML database
	 * file to be manipulated as a Node
	 * 
	 * @param doc     source XML database file
	 * @param element Element Node
	 * @param name    Element name
	 * @param value   Element value
	 * @return message Node
	 * @see Node
	 */
	private static Node getMessageElements(Document doc, Element element, String name, String value) {
		node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}

	/**
	 * 
	 * This method saves the DOM structure of the XML file in memory to the
	 * database.
	 * 
	 * @param doc this interface represents the entire XML document. Conceptually,
	 *            it is the root of the document tree, and provides the primary
	 *            access to the document's data.
	 * @throws Exception failed to save XML file
	 */
	public static void saveFile(Document doc) throws Exception {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(new FileOutputStream("DB.xml"));
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
	}

	/**
	 * 
	 * This method saves the DOM structure of the XML file in memory to the
	 * database.
	 * 
	 * @throws ParserConfigurationException when parser is not configurated
	 * @throws SAXException                 when SAX fails
	 * @throws IOException                  when IO fails
	 */
	public void writeToFile() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = dbF.newDocumentBuilder();
		doc = dB.parse("DB.xml");
		doc.getDocumentElement().normalize();
		Email em = new Email();
		// TwitterApp t = new TwitterApp();

//		Connect c = new Connect();
//		c.establishConnection();
//		for(int i = 0; i<c.getPostSize();i++ ) {
//			addContent(doc,c.getFrom().get(i), "Facebook",c.getMessage().get(i));
//		}
		try {
//			em.receivingEmail();
//			for (SocialMessage me : em.getMeList()) {
//				addMessage(doc, me.getFrom(), "eMail", me.getContent());
//			}
//			t.receiveTwitter();
//			for(MessageEmail twi: t.getTwitterList()) {
//				addMessage(doc, twi.getFrom(), "Twitter", twi.getContent());
//			}

			saveFile(doc);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * This method makes the main frame visible
	 */
	public void open() {
		frame.setVisible(true);
	}
}