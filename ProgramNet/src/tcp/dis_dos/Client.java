package tcp.dis_dos;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	public static String SERVER_PACK ="d:\\test\\client";
	public static int SERVER_PORT = 1236;
	DataInputStream dis;
	BufferedOutputStream bos;
	
	public Client() throws IOException{
		@SuppressWarnings("resource")
		Socket socket = new Socket("127.0.0.1", SERVER_PORT);
		System.out.println("Connected.");
		File d = new File(SERVER_PACK);
		if(!d.exists()) d.mkdirs();
		dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		long size;
		String name;
		long byteReaded = 0;
		long byteMustRead;
		byte[] buffer;
		int i;
		while(true){
			byteReaded = 0;
			if((i = dis.readInt()) == 1){
				size = dis.readLong();
				name = dis.readUTF();
				byteMustRead = (size-byteReaded) > 20480 ? 20480 : size-byteReaded;
				System.out.println(byteMustRead);
				buffer = new byte[(int) byteMustRead];
				bos = new BufferedOutputStream(new FileOutputStream(SERVER_PACK + name));
				System.out.println(SERVER_PACK + name);
				while(byteMustRead != 0){
					i = dis.read(buffer);
					bos.write(buffer, 0, i);
					byteReaded += i;
					byteMustRead = (size - byteReaded) > 20480 ? 20480 : size - byteReaded;
				}
				bos.flush();
				bos.close();
			}else if(i == 2){
				name = dis.readUTF();
				File f = new File(SERVER_PACK + name);
				f.mkdirs();
			}else{
				dis.close();
				break;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Client();
	}
}
