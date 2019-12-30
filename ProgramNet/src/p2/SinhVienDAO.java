package p2;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SinhVienDAO extends UnicastRemoteObject implements ISinhVien {
	Connection con;
	private static final long serialVersionUID = 1L;

	public SinhVienDAO() throws RemoteException {
		try {
			Class.forName("sun.jdbc.obdc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:Student");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<SinhVien> findByName(String name) throws RemoteException {
		ArrayList<SinhVien> li = new ArrayList<>();
		Statement sta;
		try {
			sta = con.createStatement();
			String sql = "select * from student where Name = '" + name + "'";
			ResultSet re = sta.executeQuery(sql);
			while (re.next()) {
				li.add(new SinhVien(re.getString("Name"), re.getInt("Mssv"), re
						.getInt("Age"), re.getDouble("Score")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<SinhVien> findByAge(int age) throws RemoteException {
		ArrayList<SinhVien> li = new ArrayList<>();
		Statement sta;
		try {
			sta = con.createStatement();
			String sql = "select * from student where Age = " + age;
			ResultSet re = sta.executeQuery(sql);
			while (re.next()) {
				li.add(new SinhVien(re.getString("Name"), re.getInt("Mssv"), re
						.getInt("Age"), re.getDouble("Score")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

	public ArrayList<SinhVien> findMoreThanScore(double score)
			throws RemoteException {
		ArrayList<SinhVien> li = new ArrayList<>();
		Statement sta;
		try {
			sta = con.createStatement();
			String sql = "select * from student where Score > " + score;
			ResultSet re = sta.executeQuery(sql);
			while (re.next()) {
				li.add(new SinhVien(re.getString("Name"), re.getInt("Mssv"), re
						.getInt("Age"), re.getDouble("Score")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return li;
	}

}
