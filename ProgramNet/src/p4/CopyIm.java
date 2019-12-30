package p4;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CopyIm extends UnicastRemoteObject implements Copy {
	private static final long serialVersionUID = 1L;
	private ArrayList<BufferedInputStream> fileList = new ArrayList<>();
	
	protected CopyIm() throws RemoteException {
		super();
	}
	
	public int openFile(String file) throws RemoteException {
		BufferedInputStream bis;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			fileList.add(bis);
		} catch (FileNotFoundException e) {
		return -1;
		}
		return fileList.size()-1;
	}

	public byte[] readFile(int index) throws RemoteException {
		byte[] data = new byte[1024];
		int c;
		try {
			if((c=fileList.get(index).read(data))!=-1){
				if(c==1024) return data;
				else{
					 byte[] tmp =null;
	                  System.arraycopy(data, 0, tmp, 0, c);
	                  return tmp;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeFile(int index) throws RemoteException {
		try {
			fileList.get(index).close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
