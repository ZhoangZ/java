package rmi.general;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
	public static void main(String[] args) throws RemoteException, NotBoundException {
		Registry reg = LocateRegistry.getRegistry("127.0.0.1", 12345);
		Echo e = (Echo) reg.lookup("echo");
		System.out.println(e.echo("LTM"));
	}
}
