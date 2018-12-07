package JunitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import BDA.app.Main;

public class MainTest {

	@Test
	public void testMain() {
		assertEquals(Main.class.isAnonymousClass(), false);
	}

}
