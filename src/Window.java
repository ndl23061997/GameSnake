
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class Window extends JFrame {
	public Window() {
		init();
	}
	private void init() {
		this.setTitle("Snake");
		this.setSize(400, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		add(new Surface(400));
	}
}
