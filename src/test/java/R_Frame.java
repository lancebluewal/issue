import java.awt.Button;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.SerialPortEvent;
import javax.comm.SerialPortEventListener;
import javax.comm.UnsupportedCommOperationException;

public class R_Frame extends Frame implements Runnable, ActionListener,
		SerialPortEventListener {
	/* 检测系统中可用的通讯端口类 */
	static CommPortIdentifier portId;
	/* Enumeration 为枚举型类,在java.util中 */
	static Enumeration portList;
	InputStream inputStream;
	/* 声明RS-232串行端口的成员变量 */
	SerialPort serialPort;
	Thread readThread;
	String str = "";
	TextField out_message = new TextField("上面文本框显示接收到的数据");
	TextArea in_message = new TextArea();
	Button btnOpen = new Button("open Port");

	/* 建立窗体 */
	R_Frame() {
		super("串口接收数据");
		setSize(200, 200);
		setVisible(true);
		btnOpen.addActionListener(this);
		add(out_message, "South");
		add(in_message, "Center");
		add(btnOpen, "North");
	} // R_Frame() end

	/* 点击按扭所触发的事件：打开串口,并监听串口. */
	public void actionPerformed(ActionEvent event) {
		/* 获取系统中所有的通讯端口 */
		portList = CommPortIdentifier.getPortIdentifiers();
		/* 用循环结构找出串口 */
		while (portList.hasMoreElements()) {
			/* 强制转换为通讯端口类型 */
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals("COM1")) {
					try {
						serialPort = (SerialPort) portId.open("ReadComm", 2000);
						out_message.setText("已打开端口COM1 ,正在接收数据..... ");
					} catch (PortInUseException e) {
					}
					/* 设置串口监听器 */
					try {
						serialPort.addEventListener(this);
					} catch (TooManyListenersException e) {
					}
					/* 侦听到串口有数据,触发串口事件 */
					serialPort.notifyOnDataAvailable(true);
				} // if end
			} // if end
		} // while end
		readThread = new Thread(this);
		readThread.start(); // 线程负责每接收一次数据休眠20秒钟
	} // actionPerformed() end

	/* 接收数据后休眠20秒钟 */
	public void run() {
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
		}
	} // run() end

	/* 串口监听器触发的事件，设置串口通讯参数，读取数据并写到文本区中 */
	public void serialEvent(SerialPortEvent event) {
		/* 设置串口通讯参数：波特率、数据位、停止位、奇偶校验 */
		try {
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
		}
		byte[] readBuffer = new byte[20];
		try {
			inputStream = serialPort.getInputStream();
		} catch (IOException e) {
		}
		try {
			/* 从线路上读取数据流 */
			while (inputStream.available() > 0) {
				int numBytes = inputStream.read(readBuffer);
			} // while end
			str = new String(readBuffer);
			/* 接收到的数据存放到文本区中 */
			in_message.append(str + "/n");
			System.out.println(str);
		} catch (IOException e) {
		}
	} // serialEvent() end
} // 类R_Frame end

