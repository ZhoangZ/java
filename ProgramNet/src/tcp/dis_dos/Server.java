package tcp.dis_dos;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static String SERVER_PACK = "d:\\test\\server";
	public static int PORT = 1236;
	DataInputStream dis;
	DataOutputStream dos;
	
	public Server() throws IOException{
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(PORT);
		System.out.println("Waiting...");
		Socket socket = ss.accept();
		System.out.println("Connect.");
		File s = new File(SERVER_PACK);
		dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		copy(s);
		dos.writeInt(0);
		dos.flush();
		dos.close();
		dis.close();
	}

	public void copy(File f) throws IOException {
		if(f.isFile()){
			dis = new DataInputStream(new BufferedInputStream(new FileInputStream(f)));
			dos.writeInt(1);
			dos.flush();
			dos.writeLong(f.length());
			dos.flush();
			dos.writeUTF(f.getPath().substring(SERVER_PACK.length(), f.getPath().length()));
			dos.flush();
			byte[] buffer = new byte[1024];
			int i;
			while((i = dis.read(buffer))!= -1){
				dos.write(buffer, 0, i);
				dos.flush();
			}
		}else{
			dos.writeInt(2);
			dos.flush();
			dos.writeUTF(f.getPath().substring(SERVER_PACK.length(), f.getPath().length()));
			dos.flush();
			for (File f1 : f.listFiles()){
				copy(f1);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new Server();
	}
}
