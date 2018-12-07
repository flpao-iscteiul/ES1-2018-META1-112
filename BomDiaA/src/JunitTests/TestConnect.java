package JunitTests;

import static org.junit.jupiter.api.Assertions.*;

import javax.mail.internet.HeaderTokenizer.Token;

import org.junit.jupiter.api.Test;

import com.restfb.FacebookClient;
import com.restfb.types.User;

import BDA.facebook.Connect;

class TestConnect {
	Connect test = new Connect();

	
	@Test
	void testEstablishConnection() {
		test.establishConnection();
		test.printPosts();
		assertEquals(test.getPostSize(), test.getCounter());
		test.publishMessageStatus("Welp");
		test.publishLinkStatus("I dont know what im doing", "https://www.youtube.com/watch?v=I8XXfgF9GSc");
		//test.publishImageStatus("Tech", "C:/Users/Francisco/Desktop/iscte.png", "Apps");
		assertEquals(test.getPublicationCounter(), test.getUserPublicationIDCounter());
		test.getPublicationCounter();
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
		assertEquals(a.getClass(), FacebookClient.class);
	}

	@Test
	void testPublishMessageStatus() {
		test.publishLinkStatus("abc", "www.youtube.com");
		assertEquals(test.getMessage().isEmpty(),false);
	}

	@Test
	void testPublishLinkStatus() {
		test.publishLinkStatus("abc", "www.youtube.com");
		assertEquals(test.getMessage().isEmpty(),false);	}

	@Test
	void testPublishImageStatus() {
		test.publishLinkStatus("abc", "www.youtube.com");
		assertEquals(test.getMessage().isEmpty(),false);	}

	@Test
	void testGetUserInfo() {
		User a = test.getUserInfo();
		assertEquals(a.getId().isEmpty(),false);
	}

	@Test
	void testGetCounter() {
		 int a=test.getCounter();
		 assertEquals(a, test.getCounter());
	}

	@Test
	void testGetPostSize() {
		fail("Not yet implemented");
	}

	@Test
	void testGetUserPublicationIDCounter() {
		fail("Not yet implemented");
	}

	@Test
	void testGetPublicationCounter() {
		fail("Not yet implemented");
	}

	@Test
	void testGetFrom() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDate() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMessage() {
		//assertEquals();
	}

}
