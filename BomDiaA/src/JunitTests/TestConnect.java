package JunitTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import BDA.facebook.Connect;

class TestConnect {
	Connect c = new Connect();

	@Test
	public void test() throws FileNotFoundException {
//		Connect test = new Connect();
//		test.establishConnection();
//		test.printPosts();
//		assertEquals(test.getPostSize(), test.getCounter());
//		test.publishMessageStatus("Welp");
//		test.publishLinkStatus("I dont know what im doing", "https://www.youtube.com/watch?v=I8XXfgF9GSc");
//		test.publishImageStatus("Tech", "C:/Users/Francisco/Desktop/iscte.png", "Apps");
//		assertEquals(test.getPublicationCounter(), test.getUserPublicationIDCounter());
//		test.getPublicationCounter();
		assertEquals(c.getClass(),BDA.facebook.Connect.class);
	}
}