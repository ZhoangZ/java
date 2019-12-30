package p3;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Copy extends Remote{
	public void copy(String s) throws RemoteException;
	public byte[] sendData() throws RemoteException;
	public void closeFile() throws RemoteException;
}
