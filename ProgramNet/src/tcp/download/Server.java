package tcp.download;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static final String SERVER_PTH = "e:\\server";

	public Server() throws IOException {
		//mở cổng server
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(1111);
		System.out.println("cho ket noi");
		//bat dau ket noi
		while(true){
		Socket socket = ss.accept();
		System.out.println("ket noi thanh cong");
		//mở tream lay du lieu
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream stream = new DataInputStream(socket.getInputStream());
		// lay ten file tu stream
		String path = stream.readUTF();
		System.out.println("Da lay duoc ten file");
		File file = new File(SERVER_PTH + "\\" + path);

			dos.writeLong(file.length());
			dos.flush();
System.out.println("Da gui size file");
		DataInputStream bis = new DataInputStream(
					new FileInputStream(file));
			byte[] buffer = new byte[20480];
			int a = -1;
			while ((a = bis.read(buffer)) != -1) {
				dos.write(buffer, 0, a);
				dos.flush();
			}
			dos.close();
			bis.close();System.out.println("Da gui file xuong");
		}
		}
	
	public static void main(String[] args) throws IOException {
		new Server();
	}
}
