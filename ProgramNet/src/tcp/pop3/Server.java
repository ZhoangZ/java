package tcp.pop3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	String user = "", pass = "";
	
	public Server() throws IOException{
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(1234);
		System.out.println("Waiting...");
		socket = ss.accept();
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw = new PrintWriter(socket.getOutputStream(), true);
		System.out.println(br.readLine());
		pw.println("Ban hay dang nhap");
		processLogin(br, pw);
	}

	private void processLogin(BufferedReader br, PrintWriter pw) throws IOException {
		String command, action, data;
		StringTokenizer token;
		while(true){
			command = br.readLine();
			token = new StringTokenizer(command);
			action = token.nextToken();
			if(action.equalsIgnoreCase("user")){
				data = token.nextToken();
				if(UserDAO.check(data)){
					pw.println("OK");
				}else {
					pw.println("User khong tan thanh");
				}
				this.user = data;
			}
			if (action.equalsIgnoreCase("pass")) {
				data = token.nextToken();
				if(UserDAO.check(this.user, data)){
					pw.println("Dang nhap thanh cong");
					return;
				}else{
					pw.println("Dang nhap khong thanh cong");
				}
				this.pass = data;
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Server();
	}
}
