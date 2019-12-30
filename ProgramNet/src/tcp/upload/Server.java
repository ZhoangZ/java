package tcp.upload;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static final String SERVER_PTH = "D:\\server";

	public Server() throws IOException {
		//mở cổng server
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(1111);
		System.out.println("cho ket noi");
		//bat dau ket noi
		Socket socket = ss.accept();
		System.out.println("ket noi thanh cong");
		//mở tream lay du lieu
		DataInputStream stream = new DataInputStream(socket.getInputStream());
		// lay ten file tu stream
		String path = stream.readUTF();
		//lay kich thuoc 
		long size = stream.readLong();
		//tạo file tren server
		File f = new File(SERVER_PTH + "\\" + path);
		//tạo tream lưu du lieu
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(f));
		// sô lượng byte đã ghi vào file
		long byteReaded = 0;
		// số lượng byte cần đ�?c trong lần tiếp theo
		int byteMustRead =(int) ((size - byteReaded) > 20480 ? 20480 : size - byteReaded);
		//tạo mang byte để lấy dữ liệu
		byte [] buffer;
		//bắt đầu lấy dữ liệu và lưu vào file
		while(byteMustRead!=0){
			
			buffer= new byte[byteMustRead];
			//d�?c dữ liệu
			int i=stream.read(buffer);
			// ghi dữ liẹu
			bos.write(buffer, 0, i);
			// cập nhật lại số lượng byte đã đ�?c 
			byteReaded+=i;
			// số lượng byte cần đ�?c trong lần tiếp theo
			byteMustRead =(int) ((size - byteReaded) > 20480 ? 20480 : size - byteReaded);
		}
//		đóng file
		bos.flush();
		bos.close();

	}

	public static void main(String[] args) throws IOException {
		new Server();
	}

}
