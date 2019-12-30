package tcp.pop3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client2 {
	
	public static void main(String[] args) throws IOException {
		Socket socket = new Socket("localhost", 1234);
		System.out.println("Connected.");
		BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
		PrintWriter out = new PrintWriter(System.out, true);
		String command, data;
		out.println(br.readLine());
		while(true){
			command = in.readLine();
			pw.println(command);
			data = br.readLine();
			out.println(data);
			if(data.equalsIgnoreCase("Dang nhap thanh cong")) break;
		}
		while(true){
			command = in.readLine();
			pw.println(command);
			if(command.equalsIgnoreCase("exit")) break;
			command = br.readLine();
			int count = Integer.parseInt(command);
			for(int i = 0; i < count; i++){
				command = br.readLine();
				out.println(command);
			}
		}
		in.close();
		out.close();
		pw.close();
		br.close();
		socket.close();
	}
}
