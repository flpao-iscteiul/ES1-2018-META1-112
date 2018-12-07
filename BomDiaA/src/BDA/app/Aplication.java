package BDA.app;

import BDA.window.App_Window;
import BDA.window.Intro_Window;

/**
 * Application class
 * This class initiates the graphical user interfaces
 * @author Francisco
 * @version Release
 */
public class Aplication extends Thread {

	/**
	 * Method used to open the graphical user interfaces in a specific order
	 */
	public void start() {
		Intro_Window iw = new Intro_Window();
		App_Window app;
		try {
			app = new App_Window();
			iw.open();

			sleep(2000);
			iw.close();
			app.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
