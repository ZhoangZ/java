package dethi14_1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface ICommand extends Remote {
	String register(String hoten, Date dateBirth, String placeBirth) throws RemoteException;
	boolean send_foto(String file_name)throws RemoteException;
	String view_info(String maso) throws RemoteException;
	void quit() throws RemoteException;
	String getBanner() throws RemoteException;
	void write(byte[] data, int offset, int length)throws RemoteException;
	void close()throws RemoteException;
	
}
