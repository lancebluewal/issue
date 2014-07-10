import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Com {

//	public static void main(String args[]) {
//		/* 实例化接收串口数据的窗体类 */
//		R_Frame R_win = new R_Frame();
//		/* 定义窗体适配器的关闭按钮功能 */
//		R_win.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
//		R_win.pack();
//}
		
		/**
		 * TODO.
		 *
		 * @param args 
		 */
		public static void main(String[] args) {
			// TODO 自动生成方法存根 
			List<String> list = new ArrayList<String>();
			int i = 0;
			while(true)
			{
				Scanner sc=new Scanner(System.in); 
				list.add(sc.nextLine());
				i++;
				if (i == 3)
				{
					break;
				}
			}
			
			for(String s : list)
			{
				System.out.println(s);
			}
			
		}
		
	

}
