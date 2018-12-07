package BDA.email;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import BDA.msg.SocialMessage;

/**
 * Date: 19/11/2018 This class allows to send and receive emails
 * 
 * @author Francisco
 * @version Release
 */
public class Email {

	/**
	 * String to be used as username for the users email
	 */
	public static final String USERNAME = "utterly.ap@gmail.com";
	/**
	 * String to be used as the users password
	 */
	public static final String PASSWORD = "ati128mb";
	/**
	 * Sent email content properties, such as protocols to be used
	 */
	private Properties sendEProperties;
	/**
	 * Receiving email properties, such as hosts and protocols to be used
	 */
	private Properties receiveEProperties;
	/**
	 * Session setup to receive emails
	 */
	private Session receiveESession;
	/**
	 * Session setup to send emails
	 */
	private Session sendESession;
	/**
	 * Store session to receive emails
	 */
	private Store rSstore;
	/**
	 * Folder to be used as fetching point for further emails
	 */
	private Folder inbox;
	/**
	 * Email message constructor to later be used for the xml file database0
	 */
	private Message msg;
	/**
	 * List of Email messages to be called in the application window
	 */
	private List<SocialMessage> meList = new ArrayList<>();

	/**
	 * This method is used to receive email's by establishing a connection with the
	 * mail server host
	 * 
	 * @see Properties
	 * @see Session
	 * @see Store
	 * @see Folder
	 * @see Message
	 * @see Address
	 * @see Multipart
	 * @see MimeBodyPart
	 */
	public void receivingEmail() {
		receiveEmailProperties();
		try {
			receiveEmailSession();
			openInbox();
			showContent();
		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}

	/**
	 * This method send customized email to the address of your choosing
	 * 
	 * @param to          email's recipient
	 * @param subject     email's subject
	 * @param bodyContent email's body content
	 * @see Properties
	 * @see Session
	 * @see Message
	 * @see Transport
	 */
	public void sendEmail(String to, String subject, String bodyContent) {
		sendEmailProperties();
		passwordAuthentication();
		try {
			transportEmail(to, subject, bodyContent);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method serves as a protocol initiator in the email's properties
	 */
	public void sendEmailProperties() {
		sendEProperties = new Properties();
		sendEProperties.put("mail.smtp.starttls.enable", "true");
		sendEProperties.put("mail.smtp.auth", "true");
		sendEProperties.put("mail.smtp.host", "smtp.gmail.com");
		sendEProperties.put("mail.smtp.port", "587");
	}

	/**
	 * This method establishes which protocols are accepted in receiving emails
	 */
	public void receiveEmailProperties() {
		receiveEProperties = new Properties();
		receiveEProperties.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		receiveEProperties.put("mail.pop3.socketFactory.fallback", "false");
		receiveEProperties.put("mail.pop3.socketFactory.port", "995");
		receiveEProperties.put("mail.pop3.port", "995");
		receiveEProperties.put("mail.pop3.host", "pop.gmail.com");
		receiveEProperties.put("mail.pop3.user", Email.USERNAME);
		receiveEProperties.put("mail.store.protocol", "pop3");
		receiveEProperties.put("mail.pop3.ssl.trust", "*");
	}

	/**
	 * This method creates a sessionin wich to receive emails
	 * 
	 * @throws MessagingException session error when session error ocurs
	 */
	public void receiveEmailSession() throws MessagingException {
		receiveESession = Session.getInstance(receiveEProperties, null);
		rSstore = receiveESession.getStore();
		rSstore.connect("imap.gmail.com", "utterly.ap@gmail.com", "ati128mb");
	}

	/**
	 * This method opens an email inbox
	 * 
	 * @throws MessagingException open inbox error
	 */
	public void openInbox() throws MessagingException {
		inbox = rSstore.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);
		if(inbox.getMessageCount()>=0) {
		msg = inbox.getMessage(inbox.getMessageCount());
		}
	}

	/**
	 * This method shows an email's content
	 * 
	 * @throws MessagingException           email content error
	 * @throws IOException                  email content error
	 * @throws ParserConfigurationException when parser fails
	 * @throws SAXException                 when SAX fails
	 */

	public void showContent() throws MessagingException, IOException, ParserConfigurationException, SAXException {
		
		String contentType = msg.getContentType();
		String messageContent = "";

		if (contentType.contains("multipart")) {
			Multipart multiPart = (Multipart) msg.getContent();
			int numberOfParts = multiPart.getCount();
			for (int partCount = 0; partCount < numberOfParts; partCount++) {
				MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
				messageContent = part.getContent().toString();
			}
		} else if (contentType.contains("text/plain") || contentType.contains("text/html")) {
			Object content = msg.getContent();
			if (content != null) {
				messageContent = content.toString();
			}
		}

		Message[] messages = inbox.getMessages();
		String result = messageContent.substring(messageContent.indexOf("div dir") + 1,
				messageContent.indexOf("</div>"));
		String[] output = result.split(">");
		DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
		DocumentBuilder dB = dbF.newDocumentBuilder();
		Document doc = dB.parse("DB.xml");
		doc.getDocumentElement().normalize();

		for (int i = 0; i < messages.length; i++) {
			Message message = messages[i];
			String content = "Subject: " + msg.getSubject() + "\n" + "Message: " + output[1];
			SocialMessage me = new SocialMessage(
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()),
					message.getFrom()[0].toString(), content);
			meList.add(me);
		}
		
		
	}

	/**
	 * This method does a password authentication check
	 */
	public void passwordAuthentication() {
		sendESession = Session.getInstance(sendEProperties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
	}

	/**
	 * This method creates an email and respectively sends it to the specified
	 * address
	 * 
	 * @param to          email's recipient
	 * @param subject     email's subject
	 * @param bodyContent email's content
	 * @throws AddressException   send error
	 * @throws MessagingException send error
	 */
	public void transportEmail(String to, String subject, String bodyContent)
			throws AddressException, MessagingException {
		Message message = new MimeMessage(sendESession);
		message.setFrom(new InternetAddress(USERNAME));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(subject);
		message.setText(bodyContent);

		Transport.send(message);
	}

	/**
	 * This method returns an email inbox
	 * 
	 * @return inbox returns the email's inbox
	 */
	public Folder getInbox() {
		return inbox;
	}

	/**
	 * This method returns the email's Store parameter
	 * 
	 * @return rSstore , returns a store (Send Email)
	 * @see Store
	 */
	public Store getrSstore() {
		return rSstore;
	}

	/**
	 * This method returns the receiving email's session properties
	 * 
	 * @return receiveEProperties returns Properties
	 * @see Properties
	 */
	public Properties getProperties() {
		return receiveEProperties;
	}

	/**
	 * This method returns the sending email's session properties
	 * 
	 * @return sendEProperties return Properties
	 * @see Properties
	 */
	public Properties getSendProperties() {
		return sendEProperties;
	}

	/**
	 * This method returns the email list to be called in the application window
	 * 
	 * @return meList return EMail List
	 * @see List
	 */
	public List<SocialMessage> getMeList() {
		return meList;
	}
}