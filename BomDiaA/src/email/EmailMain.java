package email;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;

public class EmailMain {

	public static final String USERNAME = "utterly.ap@gmail.com";
	public static final String PASSWORD = "ati128mb";
	private boolean textIsHtml = false;
	String result="";

	public static void main(String[] args) {
		Properties props = new Properties();

		props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.pop3.socketFactory.fallback", "false");
		props.put("mail.pop3.socketFactory.port", "995");
		props.put("mail.pop3.port", "995");
		props.put("mail.pop3.host", "pop.gmail.com");
		props.put("mail.pop3.user", Email.USERNAME);
		props.put("mail.store.protocol", "pop3");
		props.put("mail.pop3.ssl.trust","*");
		try {
			Session session = Session.getInstance(props, null);
			Store store = session.getStore();
			store.connect("imap.gmail.com", "utterly.ap@gmail.com","ati128mb");
			Folder inbox = store.getFolder("INBOX");
			inbox.open(Folder.READ_ONLY);
			Message msg = inbox.getMessage(inbox.getMessageCount());
			Address[] in = msg.getFrom();
			for(Address address: in) {

				System.out.println("FROM: " + address.toString());

			}

			// showContent(msg);
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

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			System.out.println("Date: "+ dateFormat.format(date));
					
			System.out.println("Subject: " + msg.getSubject());
			System.out.println("Message: " + output[1]);





		} catch (Exception mex) {
			mex.printStackTrace();
		}
	}
}

