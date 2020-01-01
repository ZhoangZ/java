package ltm1_2017;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {
	static int port = 12345;
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Server listening...");
		Socket socket=serverSocket.accept();
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());
		String message;
		StringTokenizer stringTokenizer;
		String key;
		boolean login=false;
		while(!login) {
			message= dataInputStream.readUTF();
			stringTokenizer= new StringTokenizer(message, " ");
			key = stringTokenizer.nextToken().toUpperCase().trim();
			switch (key) {
			case "QUIT":
				System.exit(0);
				break;
				
			default:
				break;
			}
		}
	}

}
