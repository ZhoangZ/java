package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CSDL {
	public static void main(String[] args) {
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			String database ="jdbc:odbc:newstar";
			Connection conn;
			conn = DriverManager.getConnection(database);
			String sql = "SELECT * from Student";
			Statement s = conn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			while(rs.next()){
				System.out.print(rs.getString(1)+ " ");
				System.out.print(rs.getString(2)+ " ");
				System.out.println(rs.getString(3));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage()+"\n Class not found Exception.");
		}
	}
}
