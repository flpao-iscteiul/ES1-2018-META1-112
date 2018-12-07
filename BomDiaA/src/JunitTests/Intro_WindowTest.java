package JunitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import BDA.window.Intro_Window;

public class Intro_WindowTest {
	Intro_Window w = new Intro_Window();

	@Test
	public void testGetFrame() {
		assertEquals(w.getFrame().isEnabled(), true);
	}

	@Test
	public void testIntro_Window() {
		assertEquals(w.getClass(), BDA.window.Intro_Window.class);
	}

	@Test
	public void testAddFrameContent() {
		w.addFrameContent();
		assertEquals(w.getFrame().isActive(), false);
	}

	@Test
	public void testOpen() {
		w.open();
		boolean g = w.getFrame().isVisible();
		assertEquals(g, true);
	}

	@Test
	public void testClose() {
		w.close();
		boolean g = w.getFrame().isVisible();
		assertEquals(g, false);
		assertEquals(w.getFrame().isEnabled(), false);
	}

}
