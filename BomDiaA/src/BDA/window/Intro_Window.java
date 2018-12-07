package BDA.window;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * Opening window class This class defines the graphical user interface for the
 * greeting window
 * 
 * @author Francisco
 * @version Release
 */
public class Intro_Window {

	public JFrame getFrame() {
		return frame;
	}

	/**
	 * Baseline frame on all the other objects are mounted on
	 */
	private JFrame frame;
	/**
	 * Text are on which the greeting message is written
	 */
	private JTextArea bdaLabel;

	/**
	 * This method initializes the opening window
	 */
	public Intro_Window() {
		addFrameContent();
	}

	/**
	 * This method adds the content on the window frame
	 */
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

	/**
	 * This method sets the opening window's visibility to true
	 */
	public void open() {
		frame.setVisible(true);
	}

	/**
	 * This method assures that after the window is closed it stays disabled
	 */
	public void close() {
		frame.setVisible(false);
		frame.disable();
	}

}
