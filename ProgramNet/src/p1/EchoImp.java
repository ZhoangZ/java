package p1;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class EchoImp extends UnicastRemoteObject implements Echo{
	private static final long serialVersionUID = 1L;
	
	protected EchoImp() throws RemoteException {
		super();
	}

	public String echo(String s) throws RemoteException {
		return "Xin chao: " + s;
	}
}
