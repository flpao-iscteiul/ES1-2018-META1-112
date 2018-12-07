package JunitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import BDA.msg.Message;

public class MessageTest {
	Message m = new Message();
	
	
	@Test
	public void testToString() {
		m.setDate("2018-05-12 21:34:23");
		m.setFrom("asd@iscte-iul.pt");
		m.setType("eMail");
		m.setText("test");
		assertEquals(m.toString().isEmpty(),false);
	}

	@Test
	public void testGetDate() {
		m.setDate("2018-05-12 21:34:23");	
		assertEquals(m.getDate().isEmpty(),false);
		}

	@Test
	public void testGetFrom() {
		m.setFrom("asd@iscte-iul.pt");
		assertEquals(m.getFrom().isEmpty(),false);

	}

	@Test
	public void testGetType() {
		m.setType("eMail");
		assertEquals(m.getType().isEmpty(),false);

	}

	@Test
	public void testGetText() {
		m.setText("test");
		assertEquals(m.getText().isEmpty(),false);

	}

	@Test
	public void testSetDate() {
		m.setDate("2018-05-12 21:34:23");	
		assertEquals(m.getDate(), "2018-05-12 21:34:23");
	}

	@Test
	public void testSetFrom() {
		m.setFrom("asd@iscte-iul.pt");
		assertEquals(m.getFrom(),"asd@iscte-iul.pt");
	}

	@Test
	public void testSetType() {
		m.setType("eMail");
		assertEquals(m.getType(),"eMail");
	
	}

	@Test
	public void testSetText() {
		m.setText("test");
		assertEquals(m.getText(),"test");
	
	}

}
