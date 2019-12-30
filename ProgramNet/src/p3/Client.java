package p3;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client{
	
	public static void main(String[] args) throws NotBoundException, IOException {
		Registry r = LocateRegistry.createRegistry(12346);
		Copy c = (Copy) r.lookup("Copy");
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d:\\test\\1.txt"));
		c.copy("d:\\test\\2.txt");
		byte[] data;
		while((data = c.sendData())!= null){
			bos.write(data);
		}
		c.closeFile();
		bos.close();
	}
}
