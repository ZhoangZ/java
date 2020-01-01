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
		String respone="";
		boolean login=false;
		while(!login) {
			System.out.println("Welcome. login <Account number> <Pin code>");
			message= br.readLine();
			stringTokenizer= new StringTokenizer(message, " ");
			key = stringTokenizer.nextToken().toUpperCase().trim();
			switch (key) {
			case "LOGIN":
				dataOutputStream.writeUTF(message);
				respone=dataInputStream.readUTF();
				System.out.println(respone);
				if(respone.equals("Log in success.")) {
					login=true;
				}
				
				break;
			case "QUIT":
				dataOutputStream.writeUTF(message);
				dataOutputStream.flush();
				System.exit(0);
				break;
	
			default:
				break;
			}
		}
		while(true) {
			System.out.println("Enter service...");
			message= br.readLine();
			stringTokenizer= new StringTokenizer(message, " ");
			key = stringTokenizer.nextToken().toUpperCase().trim();
			switch (key) {
			
			case "QUIT":
				dataOutputStream.writeUTF(message);
				dataOutputStream.flush();
				System.exit(0);
				break;
			case "GETBALANCE":
				dataOutputStream.writeUTF(message);
				dataOutputStream.flush();
				respone=dataInputStream.readUTF();
				System.out.println(respone);
				
				break;
			case "WITHDRAW":
				dataOutputStream.writeUTF(message);
				dataOutputStream.flush();
				respone=dataInputStream.readUTF();
				System.out.println(respone);
				break;
			case "DEPOSIT":
				dataOutputStream.writeUTF(message);
				dataOutputStream.flush();
				respone=dataInputStream.readUTF();
				System.out.println(respone);
				break;
			case "TRANSFER":
				dataOutputStream.writeUTF(message);
				dataOutputStream.flush();
				respone=dataInputStream.readUTF();
				System.out.println(respone);
				break;
			case "REPORT":
				dataOutputStream.writeUTF(message);
				dataOutputStream.flush();
				respone=dataInputStream.readUTF();
				System.out.println(respone);
				break;
			
	
			default:
				break;
			}
		}
	}

}
