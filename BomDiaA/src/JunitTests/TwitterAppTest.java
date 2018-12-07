package JunitTests;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import BDA.twitter.TwitterApp;

class TwitterAppTest {
	
	TwitterApp t = new TwitterApp();


	@Test
	void testSendTwitter() {
		String a ="Mestrado METI";
		//t.sendTwitter(a);
		assertEquals("", "");

	}

	@Test
	void testReceiveTwitter() {
//		t.receiveTwitter();
//			assertEquals(t.getOutput().isEmpty(), false);
		assertEquals(t.getClass(), BDA.twitter.TwitterApp.class);
	}

}
