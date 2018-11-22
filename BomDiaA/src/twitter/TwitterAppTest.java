package twitter;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TwitterAppTest {
	
	TwitterApp t = new TwitterApp();

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testSendTwitter() {
		String a ="Mestradooo MEI";
		t.sendTwitter(a);
		assertEquals(a.isEmpty(), false);

	}

	@Test
	void testReceiveTwitter() {
		t.receiveTwitter();
			assertEquals(t.getOutput().isEmpty(), false);
	}

}
