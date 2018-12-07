package JunitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import BDA.msg.SocialMessage;

public class SocialMessageTest {
	private SocialMessage m=new SocialMessage("2018-05-12 21:34:23", "eMail", "abc@iscte.iul.pt");

	@Test
	public void testSocialMessage() {
		assertEquals(m.getClass(), BDA.msg.SocialMessage.class);
	}

	@Test
	public void testGetDate() {
		assertEquals(m.getDate(), "2018-05-12 21:34:23");
	}

	@Test
	public void testGetFrom() {
		assertEquals(m.getFrom(),"eMail");
	}

	@Test
	public void testGetContent() {
		assertEquals(m.getContent(),"abc@iscte.iul.pt");
	}

	@Test
	public void testSetDate() {
		m.setDate("2018-05-12 21:34:23");
		assertEquals(m.getDate(),"2018-05-12 21:34:23");
	}

	@Test
	public void testSetFrom() {
		m.setFrom("absd@iscte.pt");
		assertEquals(m.getFrom(),"absd@iscte.pt");
	}

	@Test
	public void testSetContent() {
		m.setContent("test");
		assertEquals(m.getContent(),"test");
	}

}
