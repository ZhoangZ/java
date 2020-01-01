package ltm1_2017;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;

public class Server {
	static int port = 12345;
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Server listening...");
		Socket socket=serverSocket.accept();
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());
		String message;
		StringTokenizer stringTokenizer;
		String key;
		PreparedStatement pre;
		ResultSet rs;
		Account account= new Account();
		boolean quit=false;
		boolean login=false;
		while(!login) {
			message= dataInputStream.readUTF();
			stringTokenizer= new StringTokenizer(message, " ");
			key = stringTokenizer.nextToken().toUpperCase().trim();
			switch (key) {
			case "QUIT":
				System.exit(0);
				break;
			case "LOGIN":
				account.setAcountNumber(stringTokenizer.nextToken());
				account.setPinCode(stringTokenizer.nextToken());
				pre=ConnectionDB.prepareStatement("SELECT ACCOUNT_NUMBER FROM ACCOUNT WHERE ACCOUNT_NUMBER=? AND PIN_CODE=?;");
				pre.setString(1, account.getAcountNumber());
				pre.setString(2, account.getPinCode());
				
				rs = pre.executeQuery();
//				rs.last();
//				System.out.println(rs.getRow());
//				if(rs.getRow()==1)
				if(rs.next()) {
					
					dataOutputStream.writeUTF("Log in success.");
					login=true;
				}
				else
					dataOutputStream.writeUTF("Log in failure.");
				pre.close();
				
				//System.out.println("Login...");
				break;
				
			default:
				break;
			}
		}
		while(!quit) {
			message= dataInputStream.readUTF();
			stringTokenizer= new StringTokenizer(message, " ");
			key = stringTokenizer.nextToken().toUpperCase().trim();
			switch (key) {
			case "QUIT":
				System.exit(0);
				break;
			case "GETBALANCE":
				dataOutputStream.writeUTF("Balance: "+Banking.getBalance(account));
				dataOutputStream.flush();
				break;
			case "WITHDRAW":
				dataOutputStream.writeUTF(Banking.withdraw(account, Integer.parseInt(stringTokenizer.nextToken()))?"Success.":"Failture");
				dataOutputStream.flush();
				break;
			case "DEPOSIT":
				dataOutputStream.writeUTF(Banking.deposit(account, Integer.parseInt(stringTokenizer.nextToken()))?"Success.":"Failture");
				dataOutputStream.flush();
				
				break;
			case "TRANSFER":
				dataOutputStream.writeUTF(Banking.transfer(account, new Account(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()))?"Success.":"Failture");
				dataOutputStream.flush();
				
				break;
			case "REPORT":
				dataOutputStream.writeUTF(Banking.report(account));
				dataOutputStream.flush();
				
				break;
			
	
			default:
				break;
			}
		}
		ConnectionDB.getConnection().close();
	}

}
