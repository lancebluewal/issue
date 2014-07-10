import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.comm.CommPortIdentifier;
import javax.comm.PortInUseException;
import javax.comm.SerialPort;
import javax.comm.UnsupportedCommOperationException;
//可以使用开源的RXTX 

public class S_Frame extends Frame implements Runnable,ActionListener {
	/* 检测系统中可用的通讯端口类 */
	static CommPortIdentifier portId;
	/* Enumeration 为枚举型类,在util中 */
	static Enumeration portList;
	OutputStream outputStream;
	/* RS-232的串行口 */
	SerialPort serialPort;
	Thread readThread;
	Panel p = new Panel();
	TextField in_message = new TextField("打开COM1,波特率9600,数据位8,停止位1.");
	TextArea out_message = new TextArea();
	Button btnOpen = new Button("打开串口, 发送数据");
	Button btnClose = new Button("关闭串口, 停止发送数据");
	byte data[] = new byte[10240];
	/* 设置判断要是否关闭串口的标志 */
	boolean mark;

	/* 安排窗体 */
	S_Frame() {
		super("串口发送数据");
		setSize(200, 200);
		setVisible(true);
		add(out_message, "Center");
		add(p, "North");
		p.add(btnOpen);
		p.add(btnClose);
		add(in_message, "South");
		btnOpen.addActionListener(this);
		btnClose.addActionListener(this);
	} // R_Frame() end

	/* 点击按扭打开串口. */
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == btnClose) {
			serialPort.close(); // 关闭串口
			mark = true; // 用于中止线程的run()方法
			in_message.setText("串口COM1已经关闭,停止发送数据.");
		} else {
			mark = false;
			/* 从文本区按字节读取数据 */
			data = out_message.getText().getBytes();
			/* 打开串口 */
			start();
			in_message.setText("串口COM1已经打开,正在每2秒钟发送一次数据.....");
		}
	} // actionPerformed() end

	/* 打开串口,并调用线程发送数据 */
	public void start() {
		/* 获取系统中所有的通讯端口 */
		portList = CommPortIdentifier.getPortIdentifiers();
		/* 用循环结构找出串口 */
		while (portList.hasMoreElements()) {
			/* 强制转换为通讯端口类型 */
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				if (portId.getName().equals("COM1")) {
					/* 打开串口 */
					try {
						serialPort = (SerialPort) portId.open("ReadComm", 2000);
					} catch (PortInUseException e) {
					}
					/* 设置串口输出流 */
					try {
						outputStream = serialPort.getOutputStream();
					} catch (IOException e) {
					}
				} // if end
			} // if end
		} // while end
		/* 调用线程发送数据 */
		try {
			readThread = new Thread(this);
			// 线程负责每发送一次数据，休眠2秒钟
			readThread.start();
		} catch (Exception e) {
		}
	} // start() end

	/* 发送数据,休眠2秒钟后重发 */
	public void run() {
		/* 设置串口通讯参数 */
		try {
			serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		} catch (UnsupportedCommOperationException e) {
		}
		/* 发送数据流(将数组data[]中的数据发送出去) */
		try {
			outputStream.write(data);
		} catch (IOException e) {
		}
		/* 发送数据后休眠2秒钟,然后再重发 */
		try {
			Thread.sleep(2000);
			if (mark) {
				return; // 结束run方法,导致线程死亡
			}
			start();
		} catch (InterruptedException e) {
		}
	} // run() end
} // 类S_Frame end

