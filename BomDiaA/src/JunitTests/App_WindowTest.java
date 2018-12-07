package JunitTests;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import BDA.email.Email;
import BDA.facebook.Connect;
import BDA.twitter.TwitterApp;
import BDA.window.App_Window;

public class App_WindowTest {
	App_Window a = new App_Window();
	
	@Test
	public void testApp_Window() {
		assertEquals(a.getClass(),BDA.window.App_Window.class);
	}
	
	@Test
	public void testAddFrameContent() {
		a.addFrameContent();
		assertEquals(a.getClass(),BDA.window.App_Window.class);

	}
	

	@Test
	public void testNewEmail() {
		Email e = new Email();
		a.newEmail();
		assertEquals(e.getClass(), BDA.email.Email.class);
	}

	@Test
	public void testMakeFacebookPost() {
		Connect e = new Connect();
		a.makeFacebookPost();
		assertEquals(e.getClass(),BDA.facebook.Connect.class);
	}

	@Test
	public void testNewTwitter() {
		TwitterApp t = new TwitterApp();
		a.newTwitter();
		assertEquals(t.getClass(), BDA.twitter.TwitterApp.class);
	}

	@Test
	public void testReadFile() {
		a.readFile();
		assertEquals(a.getMsgList().isEmpty(),false);
		
	}

	@Test
	public void testAddMessage() {
		assertEquals(a.getMsgList().isEmpty(),false );
	}

	@Test
	public void testGetTime() {
		assertEquals(a.getTime().isEmpty(), false);
	}

	@Test
	public void testSaveFile() {
		assertEquals(a.getClass(),BDA.window.App_Window.class);
	}

	@Test
	public void testWriteToFile() {
		try {
			a.writeToFile();
			assertEquals(a.getMsgList().isEmpty(),false);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testOpen() {
		assertEquals(a.getMsgList().isEmpty(),false);

	}

}
