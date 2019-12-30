package p4;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	public static void main(String[] args) throws RemoteException {
		Registry reg = LocateRegistry.createRegistry(12346);
		Copy copy = new CopyIm();
		reg.rebind("copy", copy);
	}
}
