package dethi14_1;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
	
	static int port=1099;
	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		Registry registry= LocateRegistry.createRegistry(port);
		registry.bind("/Command", new CommandProcess());
		System.out.println("Server running...");
	}

}
