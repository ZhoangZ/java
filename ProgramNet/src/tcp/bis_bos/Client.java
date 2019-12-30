package tcp.bis_bos;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	public static String CLIENT_PATH = "d:\\test\\client";
	public static int PORT = 1495;
	DataInputStream dis;
	BufferedOutputStream bos;
	
	public Client() throws IOException{
		@SuppressWarnings("resource")
		Socket socket = new Socket("127.0.0.1", PORT);
		System.out.println("Ket noi thanh cong.");
		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		long size = dis.readLong();
		String name = dis.readUTF();
		bos = new BufferedOutputStream(new FileOutputStream(CLIENT_PATH +"\\" + name));
		long byteReaded = 0;
		int byteMustRead = (size-byteReaded) > 10240 ? 10240 : (int) (size-byteReaded);
		byte[] buffer = new byte[byteMustRead];
		int i;
		while(byteMustRead != 0){
			i = dis.read(buffer);
			bos.write(buffer, 0, i);
			byteReaded += i;
			byteMustRead = (size-byteReaded)> 10240 ? 10240 : (int) (size-byteReaded);
			buffer = new byte[byteMustRead];
		}
		bos.flush();
		bos.close();
		dis.close();
	}
	
	public static void main(String[] args) throws IOException {
		new Client();
	}
}
