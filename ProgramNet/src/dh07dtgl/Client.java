package dh07dtgl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;



public class Client {
	static String host = "127.0.0.1";
	static int port = 2000;
	public static void main(String[] args) throws IOException {
		DatagramSocket datagramSocket = new DatagramSocket();
		int length=1024*5;
		byte [] bufSend = new byte[length];
		byte [] bufReceice = new byte[length];
		DatagramPacket datagramPacketSend = new DatagramPacket(bufReceice, length);
		DatagramPacket datagramPacketReceice = new DatagramPacket(bufSend, length);
		boolean quit= false;
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		
		while(!quit) {
			String message= br.readLine();
			//System.out.println(message);
			ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(byteOutputStream);
			dataOutputStream.writeUTF(message);
			dataOutputStream.close();
			byte[] tmp=byteOutputStream.toByteArray();
			datagramPacketSend.setAddress(InetAddress.getByName(host));
			datagramPacketSend.setData(tmp);
			datagramPacketSend.setPort(port);
			datagramPacketSend.setLength(tmp.length);
			datagramSocket.send(datagramPacketSend);
			String messArrays [] = message.split(" ");
			switch (messArrays[0].trim().toUpperCase()) {
			case "QUIT":
				quit=true;
				break;
			case "XOSO":
				datagramSocket.receive(datagramPacketReceice);
				ByteArrayInputStream byteInputStream= new ByteArrayInputStream(datagramPacketReceice.getData(), 0, datagramPacketReceice.getLength() );
				DataInputStream dataInputStream = new DataInputStream(byteInputStream);
				String mess=dataInputStream.readUTF();
				System.out.println(mess);
				break;	
			default:
				break;
			}
		}
		br.close();
		
	}
}
