package p4;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	
	public static void main(String[] args) throws RemoteException, NotBoundException, IOException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 12346);
		Copy copy = (Copy) reg.lookup("copy");
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("d:\\test\\1.txt")));
		int index = copy.openFile("d:\\test\\2.txt");
		byte[] data;
		while((data = copy.readFile(index))!=null){
			bos.write(data);
		}
		bos.close();
		copy.closeFile(index);
	}
}
