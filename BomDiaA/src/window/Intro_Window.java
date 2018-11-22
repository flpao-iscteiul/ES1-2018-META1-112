package window;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class Intro_Window {

	private JFrame frame;
	private JLabel label;
	private JTextArea bdaLabel;

	public Intro_Window() {
		addFrameContent();
	}

	public void addFrameContent() {
		frame = new JFrame();
		frame.setSize(400, 200);
		frame.setLocationRelativeTo(frame);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		bdaLabel = new JTextArea();
		bdaLabel.setFont(new Font("Arial", Font.BOLD, 30));
		bdaLabel.setText("       Bom Dia Academia");
		

		frame.add(bdaLabel, BorderLayout.CENTER);
	}

	public void open() {
		frame.setVisible(true);
	}
	
	public void close(){
		frame.setVisible(false);
		frame.disable();
	}

}
