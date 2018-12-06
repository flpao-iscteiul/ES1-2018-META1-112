package window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.traversal.DocumentTraversal;

import email.Email;
import facebook.Connect;
import twitter.TwitterApp;
//import twitter.TwitterApp;


public class App_Window {
	private JFrame frame;
	private JPanel panel;
	private JMenuBar menuBar;
	private JMenu menu, view,menuItemNew;
	private JMenuItem  subMenuItemFb, subMenuItemEm,subMenuItemTw,subMenuItemBb;
	private JCheckBoxMenuItem facebook_checkbox, twitter_checkbox, email_checkbox, blackbord_checkbox;
	private JButton date_button,source_button,from_button,subjet_button,content_button; 
	public JTextArea area;
	private static Element content;
	private static Element root;
	private static File inputFile = new File("DB.xml");
	private static Element node;
	private static String timeStamp;



	public App_Window() throws Exception {
		addFrameContent();
		newEmail();
		makeFacebookPost();
		Connect c = new Connect();
		c.establishConnection();
		c.printPosts();
	//	newTwitter();
		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = dbF.newDocumentBuilder();
		Document doc = dB.parse(inputFile);
		doc.getDocumentElement().normalize();
		System.out.println("-File Loaded into DOM view-");
		addContent(doc, "me2@iscte-iul.pt", "eMail", "Delays");
		for(int i = 0; i <= c.getPostSize() -1; i++) {
		addContent(doc, c.getFrom().get(i), "Facebook", c.getMessage().get(i));
		}
		readFile(doc);
		saveFile(doc);
	}

	public void addFrameContent() {
		frame = new JFrame();
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		area = new JTextArea();
		
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
		frame.add(area, BorderLayout.CENTER);
		frame.setJMenuBar(menuBar);

		
	}
	public static void saveFile(Document doc) throws Exception {
		System.out.println("Saving XML document.");
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(new FileOutputStream("DB.xml"));
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
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
	
	public void readFile(Document doc) {
		String s,t=null;
		DocumentTraversal tr = (DocumentTraversal) doc;
		NodeList csList = doc.getElementsByTagName("Content");
		for (int i = 0; i < csList.getLength(); i++) {
			Node cs = csList.item(i);
			if (cs.getNodeType() == Node.ELEMENT_NODE) {
				Element contents = (Element) cs;
				String date = contents.getAttribute("date");
				System.out.println("Content date: " + date);
				//area.setText();
				NodeList cList = contents.getChildNodes();
				for (int j = 0; j < cList.getLength(); j++) {
					Node content = cList.item(j);
					if (content.getNodeType() == Node.ELEMENT_NODE) {
						Element c = (Element) content;
						System.out.println(c.getTagName() + ": " + c.getTextContent());

						area.setText(area.getText()+c.getTagName() + "  :  " + c.getTextContent()+"   ");
					}
				}
				area.setText(area.getText()+"\n"+"\n");
			}
		}
	}
	
	public  void addContent(Document doc, String from, String type, String contentText) {
		System.out.println("-Adding New Content Element-");
		root = doc.getDocumentElement();
		root.appendChild(getContent(doc, getTime(), from, type, contentText));
	}
	private Node getContent(Document doc, String time, String from, String type, String contentText) {
		content = doc.createElement("Content");
		content.setAttribute("Content", time);
		content.appendChild(getContentElements(doc, content, "From", from));
		content.appendChild(getContentElements(doc, content, "Type", type));
		content.appendChild(getContentElements(doc, content, "Content", contentText));
		return content;
	}
	
	private Node getContentElements(Document doc, Element element, String name, String value) {
		node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
	
	public static String getTime() {
		timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}
	
	public void open() {
		frame.setVisible(true);
	}
}