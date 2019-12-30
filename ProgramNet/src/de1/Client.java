package de1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static String host="127.0.0.1";
	public static int port1 =2000;
	public static int port2 =3000;
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket(host, port1);
		InputStream inet =socket.getInputStream();
		OutputStream onet = socket.getOutputStream();
		DataInputStream dis = new  DataInputStream(inet);
		DataOutputStream dos= new DataOutputStream(onet);
		System.out.println(dis.readUTF());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean quit= false;
		boolean signIn= false;
		while(!quit) {
			String command=br.readLine();
			String commands[]=command.split(" ");
			String key =commands[0].toUpperCase();	
			//System.out.println(key);
			switch (key) {
				case "SEND":
					if(!signIn) {
						break;
					}
					{
					dos.writeUTF(command);
					System.out.println("Service SEND");
					Socket socket2 = new Socket(host, port2);
					OutputStream os2 =socket2.getOutputStream();
					File file = new File(commands[1]);
					//System.out.println(file.exists());
					FileInputStream fis = new FileInputStream(file);
					sendFile(fis, os2);
					fis.close();
					os2.close();
					socket2.close();
					System.out.println("Success");
					}
					break;
				case "GET":
					if(!signIn) {
						break;
					}
					dos.writeUTF(command);
					System.out.println("Service GET");
					{
						Socket socket2= new Socket(host, port2);
						File file=new File(commands[2]);
						if(!file.getParentFile().exists()) {
							file.getParentFile().mkdirs();
						}
						FileOutputStream fos =new FileOutputStream(file);
						sendFile(socket2.getInputStream(), fos);
						fos.close();
						socket2.close();
						System.out.println("Success");
					}
					break;
				case "QUIT":
					System.out.println("Exit.");
					dos.writeUTF(command);
					quit=true;
					break;
				case "TEN":
				case "MATKHAU":
					if(signIn) {
						break;
					}
					dos.writeUTF(command);
					String tmp=dis.readUTF();
					System.out.println(tmp);
					if(tmp.equalsIgnoreCase("Đăng nhập thành công")) {
						signIn=true;
					}
					break;

				default:
					
					break;
				}
		}
		
	}
	private static void sendFile(InputStream fis, OutputStream os2) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(os2);
		int len = 0;
		byte data[] = new byte[1024*1];
		while ((len = bis.read(data))!=-1) {
			bos.write(data, 0, len);
			bos.flush();
			
		}
		
		
	}
}
