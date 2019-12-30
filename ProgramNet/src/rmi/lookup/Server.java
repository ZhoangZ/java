package rmi.lookup;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	
	public Server() throws RemoteException {
		Registry regis = LocateRegistry.createRegistry(12345);
		ISinhVien sv = new SinhVienDAO();
		regis.rebind("sv", sv);
	}
	
	public static void main(String[] args) throws RemoteException {
		new Server();
	}
}
