import java.awt.EventQueue;

public class Run {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				Window wnd = new Window();
				wnd.setVisible(true);
			}
		});
	}
}
