package p3;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException {
		Registry rg = LocateRegistry.createRegistry(12346);
		Copy c = new CopyIm();
		rg.rebind("Copy", c);
	}
}
