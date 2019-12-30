package tcp.lookup;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer extends Thread{
	Socket socket;
	
	public EchoServer(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		super.run();
		System.out.println("Connected.");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			String command;
			while(true){
				command = br.readLine();
				if(command.equalsIgnoreCase("exit")) break;
				pw.println("echo: " + command);
			}
			pw.close();
			br.close();
			socket.close();
		} catch (IOException e) {
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(12346);
		System.out.println("Waiting...");
		while(true){
			EchoServer es = new EchoServer(ss.accept());
			es.start();
		}
	}
}
