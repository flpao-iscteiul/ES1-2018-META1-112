package app;

import window.App_Window;
import window.Intro_Window;


public class Aplication extends Thread{

	
	public Aplication() {
		Intro_Window iw = new Intro_Window();
		App_Window app = new App_Window();
		iw.open();
		
		try {
			sleep(2000);
			iw.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		app.open();
	
	}
	
}
