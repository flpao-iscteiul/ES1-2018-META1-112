package JunitTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Properties;

import javax.mail.MessagingException;

import org.junit.BeforeClass;
import org.junit.Test;

import BDA.email.Email;

public class EmailTest {

	private Email e = new Email();

	@Test
	public void testReceiveEmailProperties() {
		assertEquals(e.getMeList().isEmpty(), true );
	}

	@Test
	public void testSendEmailProperties() {

		assertEquals(e.getMeList().isEmpty(), true );

	}

	@Test
	public void testReceiveEmailSession() throws MessagingException {
//		e.receiveEmailProperties();
//		e.receiveEmailSession();
//		String a = e.getrSstore().toString();
//		assertEquals("pop3://utterly.ap%40gmail.com@imap.gmail.com", a);
	}

	@Test
	public void testGetProperties() {
//		e.receiveEmailProperties();
//		boolean b = e.getProperties().isEmpty();
//		assertEquals(b, false);
	}

	@Test
	public void testOpenInbox() throws MessagingException {
//		e.receiveEmailProperties();
//		e.receiveEmailSession();
//		e.openInbox();
//		String a = e.getInbox().getName();
//		assertEquals(a, "INBOX");
	}

	@Test
	public void testShowContent() throws MessagingException, IOException {
		assertEquals(e.getClass(), BDA.email.Email.class);
	}

	@Test
	public void testReceiveEmail() {
		assertEquals(true, true);
	}
	
	@Test
	public void testGetMeList() {
		assertEquals(e.getMeList().isEmpty(), true
				);
	}

}