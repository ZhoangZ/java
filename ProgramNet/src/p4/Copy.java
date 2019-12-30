package p4;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Copy extends Remote{
	public int openFile(String filename) throws RemoteException;
	public byte[] readFile (int index) throws RemoteException;
	void closeFile(int index) throws RemoteException;
}
