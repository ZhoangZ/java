package tcp.download;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {

	static final String CLIENT_PATH = "E:\\client";
	// public Client() {
	public static void client() {
		try {
			@SuppressWarnings("resource")
			Socket socket = new Socket("127.0.0.1", 1111);
			DataOutputStream dos;
			DataInputStream dis;
			
			while (true) {
				dos = new DataOutputStream(socket.getOutputStream());
				dis = new DataInputStream(socket.getInputStream());
				
				@SuppressWarnings("resource")
				Scanner sc = new Scanner(System.in);
				System.out.println("Nhap command");
				String line = sc.nextLine();
				if(line.equals("exit")){
					break;
				}
				StringTokenizer st = new StringTokenizer(line, " ");
				String[] data = new String[3];
				int count = 0;
				while (st.hasMoreTokens()) {
					data[count++] = st.nextToken();
					if (count == 3)
						break;
			}
//				if (data[0].equals("download")) {
					String source = data[1];
					String dest = data[2];
					if (dest == null) {
						dest = source;
					}
					dos.writeUTF(source);
					System.out.println("Da gui ten file");
					long size = dis.readLong();
					System.out.println("DA doc sizefile");
					File file = new File(CLIENT_PATH+"\\"+dest);
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
					long byteReaded = 0;
					int byteMustRead = (int)((size - byteReaded) > 20480 ? 20480 : size - byteReaded);
					byte[] buffer;
					int a;
					while(byteMustRead != 0){
						buffer = new byte[byteMustRead];
						a = dis.read(buffer);
						bos.write(buffer, 0, a);
						bos.flush();
						byteReaded += a;
						byteMustRead = (int)((size - byteReaded) > 20480 ? 20480 : size - byteReaded);
					}
					bos.close();
					dis.close();
					System.out.println("Download XONG");
				}
//			}

			} catch (UnknownHostException u) {
				u.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
	Client c = new Client();
	c.client();
	}
}
