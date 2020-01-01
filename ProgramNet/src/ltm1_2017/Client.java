package ltm1_2017;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

public class Client {
	static int port = 12345;
	static String host = "127.0.0.1";
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket= new Socket(host, port);
		System.out.println("Connected.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());
		String key="";
		StringTokenizer stringTokenizer=null;
		String message;
		
		message= br.readLine();
		stringTokenizer= new StringTokenizer(message, " ");
		key = stringTokenizer.nextToken().toUpperCase().trim();
		switch (key) {
		case "QUIT":
			dataOutputStream.writeUTF(message);
			dataOutputStream.flush();
			break;

		default:
			break;
		}
		
	}

}
