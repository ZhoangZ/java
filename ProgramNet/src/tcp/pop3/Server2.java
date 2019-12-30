package tcp.pop3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

import tcp.lookup.SinhVien;
import tcp.lookup.SinhVienDAO;

public class Server2 {
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	String user = "", pass = "";
	
	public Server2() throws IOException{
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(1234);
		System.out.println("Waiting...");
		socket = ss.accept();
		System.out.println("Connected.");
		while(true){
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(), true);
			pw.println("Hay dang nhap");
			processLogin(br, pw);
			process(br, pw);
			br.close();
			pw.close();
			socket.close();
		}
	}

	private void process(BufferedReader br, PrintWriter pw) throws IOException {
		String command;
		StringTokenizer token;
		String action, data;
		while(true){
			command = br.readLine();
			token = new StringTokenizer(command);
			action = token.nextToken();
			if(action.equalsIgnoreCase("user")){
				data = token.nextToken();
				this.user = data;
				if(UserDAO.check(this.user)){
					pw.println("OK");
				}else{
					pw.println("User khong ton tai");
				}
				continue;
			}else if(action.equalsIgnoreCase("pass")){
				data = token.nextToken();
				this.pass = data;
				if(UserDAO.check(this.user, this.pass)){
					pw.println("Dang nhap thanh cong");
					return;
				}else{
				pw.println("Dang nhap khong thanh cong");
				}
			}else continue;
		}
	}

	private void processLogin(BufferedReader br, PrintWriter pw) throws IOException {
		String command;
		ArrayList<SinhVien> list = null;
		while(true){
			command = br.readLine();
			StringTokenizer token = new StringTokenizer(command, " ");
			String action = token.nextToken();
			if(action.equalsIgnoreCase("findbyname")){
				list = SinhVienDAO.findByName(token.nextToken());
			}else if(action.equalsIgnoreCase("findbyage")){
				list = SinhVienDAO.findByAge(Integer.parseInt(token.nextToken()));
			}else if(action.equalsIgnoreCase("findbyscore")){
				list = SinhVienDAO.findByScore(Double.parseDouble(token.nextToken()));
			}else if(action.equalsIgnoreCase("exit")) break;
			pw.println(list.size());
			for(SinhVien sinhVien : list){
				pw.println(sinhVien.toString());
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Server2();
	}
}
