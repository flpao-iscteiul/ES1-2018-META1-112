package JunitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import BDA.app.Aplication;

public class ApplicationTest {
	Aplication a = new Aplication();
	
	@Test
	public void testStart() {
		assertEquals(a.getClass(), BDA.app.Aplication.class);
	}

}
