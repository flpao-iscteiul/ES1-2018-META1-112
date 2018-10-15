package app;



import window.App_Window;
import window.Intro_Window;
import window.Login_Window;

public class Aplication extends Thread{

	
	public Aplication() {
		Intro_Window iw = new Intro_Window();
		Login_Window login = new Login_Window();
		App_Window app = new App_Window();
		iw.open();
		
		try {
			sleep(2000);
			iw.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		login.open();
		try {
			sleep(5000);
			login.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		app.open();
	}
	
	
	
}
