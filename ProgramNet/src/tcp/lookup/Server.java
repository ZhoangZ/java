package tcp.lookup;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Server {
	Socket socket;
	BufferedReader br;
	PrintWriter pw;
	
	public Server() throws IOException{
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(12346);
		System.out.println("Waiting...");
		socket = ss.accept();
		System.out.println("Connected.");
		
		while(true){
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(socket.getOutputStream(), true);
			process(br, pw);
			br.close();
			pw.close();
			socket.close();
		}
	}

	private void process(BufferedReader br, PrintWriter pw) throws IOException {
		String commmand;
		ArrayList<SinhVien> list = null;
		while(true){
			commmand = br.readLine();
			StringTokenizer token = new StringTokenizer(commmand, " ");
			String action = token.nextToken();
			if (action.equalsIgnoreCase("findbyname")) {
                list=SinhVienDAO.findByName(token.nextToken());
            } else if (action.equalsIgnoreCase("findbyage")) {
                list=SinhVienDAO.findByAge(Integer.parseInt(token.nextToken()));
            } else if (action.equalsIgnoreCase("findbyscore")) {
                list=SinhVienDAO.findByScore(Double.parseDouble(token.nextToken()));
			}else if(action.equalsIgnoreCase("exit"))
				break;
			pw.println(list.size());
			for (SinhVien sinhVien : list){
				pw.println(sinhVien.toString());
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Server();
	}
}
