import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class SendComm {
	public static void main(String args[]) {
		S_Frame S_win = new S_Frame();
		S_win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		S_win.pack();
	}
}
