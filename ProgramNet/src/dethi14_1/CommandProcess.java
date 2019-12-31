package dethi14_1;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class CommandProcess extends UnicastRemoteObject implements ICommand {
	private BufferedOutputStream bos ;
	protected CommandProcess() throws RemoteException {
		super();
	}

	
	private static final long serialVersionUID = 1L;


	@Override
	public String register(String hoten, Date dateBirth, String placeBirth) throws RemoteException {
		Date now = new Date();
		int diff = now.getYear()- dateBirth.getYear();
		if(diff<=6) {
			File file = new File("Data/info.data");
			if(!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			LinkedList<String> queue = new LinkedList<String>();
			int ms=1;
			if(file.exists()) {
				try {
					DataInputStream dis = new DataInputStream(new FileInputStream(file));
					String tmp="";
					while(dis.available()>0) {
						tmp=dis.readUTF();
						queue.offer(tmp);
						ms=Integer.parseInt(tmp.split("\t")[0])+1;
					}
					dis.close();
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} 
			queue.offer(ms+"\t"+hoten+"\t"+dateBirth+"\t"+placeBirth);
			try {
				DataOutputStream dataOutputStream= new DataOutputStream(new FileOutputStream(file));
				while(!queue.isEmpty()) {
					dataOutputStream.writeUTF(queue.poll());
				}
				dataOutputStream.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return ms+"";
		}
		return "register not success.";
	}


	@Override
	public boolean send_foto(String file_name) throws RemoteException {
		File file = new File("Data/"+file_name);
		if(!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			bos= new BufferedOutputStream(new FileOutputStream(file));
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public String view_info(String maso) throws RemoteException {
		String rs="Có lỗi gì đó";
		int ms2=0;
		try {
			
			ms2=Integer.parseInt(maso);
		} catch (NumberFormatException e) {
			return "Mã số phải là số";
		}
		
		
		File file = new File("Data/info.data");
		LinkedList<String> queue = new LinkedList<String>();
		int ms=1;
		
		if(file.exists()) {
			try {
				DataInputStream dis = new DataInputStream(new FileInputStream(file));
				String tmp="";
				while(dis.available()>0) {
					tmp=dis.readUTF();
					queue.offer(tmp);
					ms=Integer.parseInt(tmp.split("\t")[0]); 
					
					
					if(ms==ms2) {
						rs=tmp;
						break;
					}
				}
				dis.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} 
		return rs;
		
	}


	@Override
	public void quit() throws RemoteException {
		new Timer().schedule(new TimerTask() {
			
			@Override
			public void run() {
				System.exit(0);
				
			}
		}, 1000);
		
	}


	@Override
	public String getBanner() throws RemoteException {
		
		return "welcome to Registration...";
	}


	@Override
	public void write(byte[] data, int offset, int length) {
		try {
			bos.write(data, offset, length);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void close() {
		try {
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
