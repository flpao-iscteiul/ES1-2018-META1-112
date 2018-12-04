package app;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import window.App_Window;
import window.Intro_Window;


public class Aplication extends Thread{

	
	public Aplication() {
		Intro_Window iw = new Intro_Window();
		App_Window app;
		try {
			app = new App_Window();
			app.open();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		iw.open();
		
		try {
			sleep(2000);
			iw.close();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
