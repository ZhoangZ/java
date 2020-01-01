package ltm1_2017;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
	static Connection con;
	private static void createConnection() throws ClassNotFoundException, SQLException {
		if(con==null||con.isClosed()) {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:LTMANG");
		}
	}
	public static Statement createStatement() throws ClassNotFoundException, SQLException {
		createConnection();
		return con.createStatement();
	}
	public static  PreparedStatement prepareStatement(String sql) throws ClassNotFoundException, SQLException {
		createConnection();
		return con.prepareStatement(sql);
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Statement s =createStatement();
		String sql="SELECT ACCOUNT_NUMBER FROM ACCOUNT";
		ResultSet rs=s.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
		
	}

}
