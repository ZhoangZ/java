package p3;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CopyIm	extends UnicastRemoteObject implements Copy {
	BufferedInputStream bis;
	
	protected CopyIm() throws RemoteException {
		super();
	}
	
	private static final long serialVersionUID = -329587034347766967L;
	
	public void copy(String s) throws RemoteException {
		try {
			bis = new BufferedInputStream(new FileInputStream(s));
		} catch (FileNotFoundException e) {
			throw new RemoteException();
		}
	}

	public byte[] sendData() throws RemoteException {
		byte[] d = new byte[1024];
		int i;
		try {
			i = bis.read(d);
			if(i==-1) return null;
			byte[] con = new byte[i];
			System.arraycopy(d, 0, con, 0, i);
			return con;
		} catch (IOException e) {
			return null;
		}
	}

	public void closeFile() throws RemoteException {
		try {
			bis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}


