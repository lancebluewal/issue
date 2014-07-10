import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class ReadComm {
	public static void main(String args[]) {
		/* 实例化接收串口数据的窗体类 */
		R_Frame R_win = new R_Frame();
		/* 定义窗体适配器的关闭按钮功能 */
		R_win.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		R_win.pack();
	}
}
