package junitTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;

import org.junit.BeforeClass;
import org.junit.Test;

import email.Email;

public class EmailTest {

	private Email e = new Email();

	@Test
	public void testReceiveEmailProperties() {
		e.receiveEmailProperties();
		String a = e.getProperties().getProperty("mail.pop3.socketFactory.class");
		String b = e.getProperties().getProperty("mail.pop3.socketFactory.fallback");
		String c = e.getProperties().getProperty("mail.pop3.socketFactory.port");
		String j = e.getProperties().getProperty("mail.pop3.port");
		String d = e.getProperties().getProperty("mail.pop3.host");
		String g = e.getProperties().getProperty("mail.pop3.user");
		String h = e.getProperties().getProperty("mail.store.protocol");
		String i = e.getProperties().getProperty("mail.pop3.ssl.trust");

		assertEquals("javax.net.ssl.SSLSocketFactory", a);
		assertEquals("false", b);
		assertEquals("995", c);
		assertEquals("995", j);
		assertEquals("pop.gmail.com", d);
		assertEquals(Email.USERNAME, g);
		assertEquals("pop3", h);
		assertEquals("*", i);
	}

	@Test
	public void testSendEmailProperties() {
		e.sendEmailProperties();

		String a = e.getSendProperties().getProperty("mail.smtp.starttls.enable");
		String b = e.getSendProperties().getProperty("mail.smtp.auth");
		String c = e.getSendProperties().getProperty("mail.smtp.host");
		String j = e.getSendProperties().getProperty("mail.smtp.port");

		assertEquals("true", a);
		assertEquals("true", b);
		assertEquals("smtp.gmail.com", c);
		assertEquals("587", j);

	}

	@Test
	public void testReceiveEmailSession() throws MessagingException {
		e.receiveEmailProperties();
		e.receiveEmailSession();
		String a = e.getrSstore().toString();
		assertEquals("pop3://utterly.ap%40gmail.com@imap.gmail.com", a);
	}

	@Test
	public void testGetProperties() {
		e.receiveEmailProperties();
		boolean b = e.getProperties().isEmpty();
		assertEquals(b, false);
	}

	@Test
	public void testOpenInbox() throws MessagingException {
		e.receiveEmailProperties();
		e.receiveEmailSession();
		e.openInbox();
		String a = e.getInbox().getName();
		assertEquals(a, "INBOX");
	}

	@Test
	public void testShowContent() throws MessagingException, IOException {
		e.receiveEmailProperties();
		e.receiveEmailSession();
		e.openInbox();
		assertEquals(e.showContent().isEmpty(), false);
	}

	@Test
	public void testReceiveEmail() {
		e.receivingEmail();
		assertEquals(true, true);
	}

}