
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.JFrame;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8255319694373975038L;
	
	public Window() {
		init();
	}
	private void init() {
		Container container=getContentPane();
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		
		this.setTitle("Snake");
		this.setSize(416, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		add(new Surface(400));
	}
}
