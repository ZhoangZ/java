package tcp.bis_bos;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static String SERVER_PATH = "E:\\may ao\\ubuntu 01(12.10)\\abc.txt";
	public static int PORT = 1495;
	DataOutputStream dos;
	BufferedInputStream bis;
	
	public Server() throws IOException{
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(PORT);
		System.out.println("Waitting...");
		Socket socket = ss.accept();
		System.out.println("Connected.");	
		
		File f = new File(SERVER_PATH);
		dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		bis = new BufferedInputStream(new FileInputStream(f));
		long size = f.length();
		dos.writeLong(size);
		dos.flush();
		dos.writeUTF(f.getName());
		dos.flush();
		long byteReaded = 0;
		int byteMustRead = (size-byteReaded)> 10240 ? 10240 :(int) (size-byteReaded);
		byte[] buffer = new byte[byteMustRead];
		int i;
		while(byteMustRead != 0){
			i = bis.read(buffer);
			dos.write(buffer, 0, i);
			dos.flush();
			byteReaded += i;
			byteMustRead = (size-byteReaded)> 10240 ? 10240 :(int) (size-byteReaded);
			buffer = new byte[byteMustRead];
		}
		bis.close();
		dos.flush();
		dos.close();
		socket.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Server();
	}
}
