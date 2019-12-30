package tcp.upload;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {
	public Client() throws UnknownHostException, IOException {
		// kết nối tới server
		@SuppressWarnings("resource")
		Socket socket = new Socket("localhost", 1111);
		// đưa dữ liệu và stream trong socket
		DataOutputStream stream = new DataOutputStream(socket.getOutputStream());
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		// lấy dòng comment từ bàn phím
		String line = s.nextLine();
		StringTokenizer tk = new StringTokenizer(line, " ");
		String data[] = new String[3];
		int count = 0;
		// phân tích dòng comment là lấy thông tin cho vào mang data
		while (tk.hasMoreTokens()) {
			data[count++] = tk.nextToken();
			if (count == 3)
				break;
		}
		// nếu comment là copy thì thực hiện
		if (data[0].equals("copy")) {
			// lấy đư�?ng dẫn file nguồn
			String source = data[1];
			String des = data[2];
		
		
			// tạo file nguồn
			File f = new File(source);
			if (des == null)
				des = f.getName();
			// kiểm tra file ton tai
			if (f.exists()) {
				// gửi tên file cho server
				stream.writeUTF(des);
				stream.flush();
				// gửi dung lượng file gửi qua server
				stream.writeLong(f.length());
				stream.flush();
				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(f));
				byte[] buffer = new byte[20480];
				int i = -1;
				// gửi dữ liệu của file server
				while ((i = bis.read(buffer)) != -1) {
					stream.write(buffer, 0, i);
					stream.flush();
				}
				// đóng file
				bis.close();
			}}
		}

//	}

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		new Client();
	}}
