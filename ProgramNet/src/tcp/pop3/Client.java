package tcp.pop3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		Socket socket = new Socket("localhost", 1234);
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
		PrintWriter out = new PrintWriter(System.out, true);
		System.out.println("Connected.");
		pw.println("Connected.");
		System.out.println(br.read());
		String data;
		while(true){
			data = in.readLine();
			pw.println(data);
			data = br.readLine();
			out.println(data);
		}
	}
}
