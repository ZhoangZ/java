package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Access {
	private static final String DB = "newstar";
	
	public static void main(String[] args) {
		try {
			// Buoc 1: Load driver
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// Buoc 2: Tao ket noi xuong CSDL: URL (jdbc:dbms:database
			Connection conn = DriverManager.getConnection("jdbc:odbc:" + DB);
			// Buoc 3: Tao Statement
			Statement stmt = conn.createStatement();
			// Buoc 4: Execute
			ResultSet rs = stmt.executeQuery("SELECT * FROM Student");
			// Buoc 5: Fetch data
			while(rs.next()) {
				String sid = rs.getString("SID");
				String fn = rs.getString("FirstName");
				String ln = rs.getString("LastName");
				String cl = rs.getString("Class");
				System.out.println("[" + sid + "]." + fn + " " + ln + ". Class:" + cl);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}}}
