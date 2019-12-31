package dh07dtgl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;

public class Server {
	static int port = 2000;

	public static void main(String[] args) throws IOException {
		DatagramSocket datagramSocket = new DatagramSocket(port);
		int length=1024*5;
		byte [] bufSend = new byte[length];
		byte [] bufReceice = new byte[length];
		DatagramPacket datagramPacketSend = new DatagramPacket(bufReceice, length);
		DatagramPacket datagramPacketReceice = new DatagramPacket(bufSend, length);
		boolean quit=false;
		
		while(!quit) {
			datagramSocket.receive(datagramPacketReceice);
			ByteArrayInputStream byteInputStream= new ByteArrayInputStream(datagramPacketReceice.getData(), 0, datagramPacketReceice.getLength() );
			DataInputStream dataInputStream = new DataInputStream(byteInputStream);
			String mess=dataInputStream.readUTF();
			System.out.println(mess);
			String messArrays [] = mess.split(" ");
			switch (messArrays[0].trim().toUpperCase()) {
			case "QUIT":
				quit=true;
				break;
			case "XOSO":
				List<KetQuaXoSo>list =KetQuaXoSo.loadFile("D:/Test/kqxs.txt");
				String message= list.get(0).toString();
				ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
				DataOutputStream dataOutputStream = new DataOutputStream(byteOutputStream);
				dataOutputStream.writeUTF(message);
				dataOutputStream.close();
				byte[] tmp=byteOutputStream.toByteArray();
				
				datagramPacketSend.setData(tmp);
				datagramPacketSend.setLength(tmp.length);
				//datagramPacketSend.setSocketAddress(datagramPacketReceice.getSocketAddress());
				datagramPacketSend.setSocketAddress(datagramPacketReceice.getSocketAddress());
				//datagramPacketSend.setPort(port);
				//System.out.println(Arrays.toString(tmp));
				//System.out.println(datagramPacket.getAddress());
				//datagramPacket.setAddress(datagramPacket.getAddress());
				datagramSocket.send(datagramPacketSend);
				break;

			default:
				break;
			}
			
		}
	}

}
