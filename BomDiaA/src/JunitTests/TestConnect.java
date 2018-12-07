package JunitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.mail.internet.HeaderTokenizer.Token;

import org.junit.jupiter.api.Test;

import com.restfb.FacebookClient;
import com.restfb.types.User;

import BDA.facebook.Connect;

class TestConnect {
	Connect test = new Connect();

	
	@Test
	void testEstablishConnection() {
		assertEquals(test.getClass(),BDA.facebook.Connect.class);	}

	@Test
	void testPrintPosts() {
		String a ="posts";
		assertEquals(a.isEmpty(),false);
}

	@Test
	void testGetAccessToken() {
		String t =test.getAccessToken();
		assertEquals(t.isEmpty(),false);
	}

	@Test
	void testGetFacebookClient() {
		FacebookClient a=test.getFacebookClient();
		assertEquals(test.getClass(), BDA.facebook.Connect.class);
	}

	@Test
	void testPublishMessageStatus() {
		assertEquals(test.getClass(),BDA.facebook.Connect.class);
	}

	@Test
	void testPublishLinkStatus() {
		String t = test.getAccessToken();
		assertEquals(t.isEmpty(),false);	
		}

//	@Test
//	void testPublishImageStatus() {
//		test.publishLinkStatus("abc", "www.youtube.com");
//		assertEquals(test.getMessage().isEmpty(),false);	}

	@Test
	void testGetUserInfo() {
		String t = test.getAccessToken();
		assertEquals(t.isEmpty(),false);	}

	@Test
	void testGetCounter() {
		 int a=test.getCounter();
		 assertEquals(a, test.getCounter());
	}

//	@Test
//	void testGetPostSize() {
//		int a =test.getPostSize();
//		assertEquals(test.getClass(), BDA.facebook.Connect.class);
//	}

	@Test
	void testGetUserPublicationIDCounter() {
		int w =test.getUserPublicationIDCounter();
			assertEquals(w, test.getUserPublicationIDCounter());
	}

	@Test
	void testGetPublicationCounter() {
		
	}

	


}
