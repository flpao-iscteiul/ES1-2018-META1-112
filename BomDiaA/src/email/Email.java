package email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
/**
 * Date: 19/11/2018 
 * This class allows to send and receive emails
 * @author Francisco
 * 
 */
public class Email {

	/**
	 * string USERNAME for user's email
	 * 
	 * 
	 */

	public static final String USERNAME = "utterly.ap@gmail.com";
	/**
	 * string PASSWORD for user's password
	 */
	public static final String PASSWORD = "ati128mb";

	private Properties sendEProperties;

	private Properties receiveEProperties;

	private Session receiveESession;

	private Session sendESession;

	private Store rSstore;

	private Folder inbox;

	private  Message msg;

	private  Address[] in;
	/**
	 * This method allows us to receive emails
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


			Message[] messages = inbox.getMessages();
			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				System.out.println("==============================");
				System.out.println("Email #" + (i + 1));
				System.out.println("From: " + message.getFrom()[0]);
				System.out.println(showContent());
			}




		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}

	/**
	 * This method send an email
	 * @param to who is receiving this email
	 * @param subject email's subject
	 * @param bodyContent email's body content
	 * @see Properties
	 * @see Session
	 * @see Message
	 * @see Transport
	 */

	public void sendEmail(String to,String subject, String bodyContent) {
		sendEmailProperties();

		passwordAuthentication();

		try {

			transportEmail(to, subject, bodyContent);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * this method put some elements in Properties object (Send Email)
	 */
	public void sendEmailProperties() {
		sendEProperties = new Properties();
		sendEProperties.put("mail.smtp.starttls.enable", "true");
		sendEProperties.put("mail.smtp.auth", "true");
		sendEProperties.put("mail.smtp.host", "smtp.gmail.com");
		sendEProperties.put("mail.smtp.port", "587");
	}

	/**
	 * this method put some elements in Properties object (Receive Email)
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
		receiveEProperties.put("mail.pop3.ssl.trust","*");
	}
	/**
	 *  This method create a session (Receive Email)
	 */
	public void receiveEmailSession() throws MessagingException {
		receiveESession = Session.getInstance(receiveEProperties, null);
		rSstore = receiveESession.getStore();
		rSstore.connect("imap.gmail.com", "utterly.ap@gmail.com",
				"ati128mb");
	}
/**
 * 
 * @return rSstore , return a store (Send Email)
 */
	public Store getrSstore() {
		return rSstore;
	}

	/**
	 *  This method open a email inbox
	 * @throws MessagingException
	 */
	public void openInbox() throws MessagingException {
		inbox = rSstore.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);
		msg = inbox.getMessage(inbox.getMessageCount());
		in = msg.getFrom();
	}
	/**
	 * this method get a email inbox
	 * @return inbox return a email inbox
	 */

	public Folder getInbox() {
		return inbox;
	}
	
	/**
	 * This method show a email content
	 * @return content, return a email content
	 * @throws MessagingException
	 * @throws IOException
	 */

	public String showContent() throws MessagingException, IOException {
		String contentType = msg.getContentType();
		String messageContent="";

		if (contentType.contains("multipart")) {
			Multipart multiPart = (Multipart) msg.getContent();
			int numberOfParts = multiPart.getCount();
			for (int partCount = 0; partCount < numberOfParts; partCount++) {
				MimeBodyPart part = (MimeBodyPart) multiPart.getBodyPart(partCount);
				messageContent = part.getContent().toString();
			}
		}
		else if (contentType.contains("text/plain")
				|| contentType.contains("text/html")) {
			Object content = msg.getContent();
			if (content != null) {
				messageContent = content.toString();
			}
		}

		String result = messageContent.substring(messageContent.indexOf("div dir") + 1, messageContent.indexOf("</div>"));
		String[] output = result.split(">");
		String content =  "Subject: " + msg.getSubject()+ "\n"+"Message: " + output[1];
		return content;

	}
	
	/**
	 * This method do a password authentication
	 */

	public void passwordAuthentication() {
		sendESession = Session.getInstance(sendEProperties,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(USERNAME, PASSWORD);
			}
		});
	}

	/**
	 * This method create an email and send
	 * @param to who is receiving this email
	 * @param subject email's subject
	 * @param bodyContent email's bodycontent
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void transportEmail(String to, String subject,String bodyContent) throws AddressException, MessagingException {
		Message message = new MimeMessage(sendESession);
		message.setFrom(new InternetAddress(USERNAME));
		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
		message.setSubject(subject);
		message.setText(bodyContent);

		Transport.send(message);
	}

	/**
	 * this method get receive  email properties
	 * @return receiveEProperties return properties
	 */
	public Properties getProperties() {
		return receiveEProperties;
	}
	/**
	 * this method get send email properties
	 * @return sendEProperties return properties
	 */
	public Properties getSendProperties() {
		return sendEProperties;
	}




}