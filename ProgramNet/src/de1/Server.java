package de1;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Server {
	public static int port1 = 2000;
	public static int port2 = 3000;
	public static ArrayList<User> listUser;
	
	public static void main(String[] args) throws IOException {
		listUser = new ArrayList<User>();
		listUser.add(new User("hoang", "123"));
		final ServerSocket serverSocket1= new ServerSocket(port1);
		final ServerSocket serverSocket2 = new ServerSocket(port2);
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					Socket socket =serverSocket1.accept();
					InputStream inet =socket.getInputStream();
					OutputStream onet = socket.getOutputStream();
					DataOutputStream dos= new DataOutputStream(onet);
					DataInputStream dis = new  DataInputStream(inet);
					dos.writeUTF("Welcome!");
					boolean quit = false;
					String TEN= "";
					String MATKHAU= "";
					while(!quit) {
						
						String [] mess= dis.readUTF().split(" ");
						//System.out.println(Arrays.toString(mess));
						String key =mess[0].toUpperCase();
						
						switch (key) {
							case "SEND":
								if(TEN!=""&&MATKHAU!="") {
									System.out.println("Service SEND");
									
									Socket socket2= serverSocket2.accept();
									File file=new File(mess[2]);
									if(!file.getParentFile().exists()) {
										file.getParentFile().mkdirs();
									}
									FileOutputStream fos =new FileOutputStream(file);
									sendFile(socket2.getInputStream(), fos);
									fos.close();
									
									System.out.println("Success");
									
									
								}
								break;
							case "GET":
								if(TEN!=""&&MATKHAU!="") {
									System.out.println("Service GET");
									
									Socket socket2= serverSocket2.accept();
									File file=new File(mess[1]);
									FileInputStream fis =new FileInputStream(file);
									sendFile( fis,socket2.getOutputStream());
									fis.close();
									socket2.getOutputStream().close();
									System.out.println("Success");
								}
								break;
							case "QUIT":
								quit=true;
								System.out.println("Exit.");
								break;
							case "TEN":
								if(MATKHAU=="") {
									String tmp= mess[1];
									String respone="Sai tên";
									for(User u: listUser) {
										if(u.ten.equals(tmp)) {
											TEN=tmp;
											respone="Tên đúng";
											break;
										}
									}
									dos.writeUTF(respone);
								}
								break;
							case "MATKHAU":
								if(TEN!="") {
									String tmp= mess[1];
									String respone="Sai mật khẩu";
									for(User u: listUser) {
										if(u.ten.equals(TEN)&&u.matkhau.equals(tmp)) {
											MATKHAU=tmp;
											respone="Đăng nhập thành công";
											break;
										}
									}
									dos.writeUTF(respone);
								}
								break;
		
							default:
								break;
							}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		thread1.run();
		
		
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
