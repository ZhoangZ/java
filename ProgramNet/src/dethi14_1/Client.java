package dethi14_1;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Client {
	static String host="127.0.0.1";
	static int port=1099;
	public static void main(String[] args) throws  NotBoundException, IOException {
		Registry registry = LocateRegistry.getRegistry(host, port);
		ICommand iCommand= (ICommand) registry.lookup("/Command");
		System.out.println(iCommand.getBanner());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String message = "";
		boolean quit= false;
		String ms="";
		String response="";
		while(!quit) {
			message = br.readLine();
			StringTokenizer stringTokenizer = new StringTokenizer(message, "|");
			String key = stringTokenizer.nextToken().toUpperCase().trim();
			switch (key) {
			case "REGISTER":
				if(stringTokenizer.countTokens()!=3) {
					System.out.println("Thiếu param");
					break;
				}
				try {
					String hoten=stringTokenizer.nextToken();
					String dateBirthStr=stringTokenizer.nextToken();
					String placeBirth=stringTokenizer.nextToken();
					SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd/MM/yyyy");
					Date dateBirth = simpleDateFormat.parse(dateBirthStr);
					response=iCommand.register(hoten, dateBirth, placeBirth);
					if(response.equals("register not success.")) {
						System.out.println(response);
						break;
					}
					ms= response;
					System.out.println("MS: "+response);
					System.out.println("REGISTER success.");
				} catch (ParseException e) {
					System.out.println("Date incorect syntax.");
					break;
				}
				break;
			case "SEND_FOTO":
				if(ms=="") {
					System.out.println("registrer not complete.");
					break;
				}
				if(stringTokenizer.countTokens()!=1) {
					System.out.println("Thiếu param");
					break;
				}
				File file= new File(stringTokenizer.nextToken());
				if(!file.exists()) {
					System.out.println("File not found.");
					break;
				}
				 if(iCommand.send_foto(ms+file.getName().substring(file.getName().lastIndexOf('.')))) {
					 BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
					 int len=0;
					 byte[] data = new byte[1024*5];
					 while((len= bis.read(data))!=-1) {
						 iCommand.write(data, 0, len);
					 }
					 bis.close();
					 iCommand.close();
					 System.out.println("SEND_FOTO success.");
				 }
				break;
			case "VIEW_INFO":
				if(stringTokenizer.countTokens()!=1) {
					System.out.println("Thiếu param");
					break;
				}
				 response=iCommand.view_info(stringTokenizer.nextToken());
				System.out.println(response);
//				System.out.println("VIEW_INFO success.");
				break;
			case "QUIT":
				quit=true;
				iCommand.quit();
				System.out.println("Exit.");
				break;
				
			default:
				System.out.println("Incorect syntax.");
				break;
			}
		}
	}
}
