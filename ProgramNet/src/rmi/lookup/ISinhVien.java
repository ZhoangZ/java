package rmi.lookup;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface ISinhVien extends Remote{
	public ArrayList<SinhVien> findByName(String name) throws RemoteException;
	public ArrayList<SinhVien> findByAge(int age) throws RemoteException;
	public ArrayList<SinhVien> findMoreThanScore(double score) throws RemoteException;
}
