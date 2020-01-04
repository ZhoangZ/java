package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDB {
	static Connection con;
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(con==null||con.isClosed()) {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			   String database="D:\\mydata.mdb";//Here database exists in the current directory  
			   
			   String url="jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};" 
			                    +"DBQ=" + database + ";DriverID=22;READONLY=true";  
			con=DriverManager.getConnection(url);
		}
		return con;
	}
	public static Statement createStatement() throws ClassNotFoundException, SQLException {
		getConnection();
		return con.createStatement();
	}
	public static  PreparedStatement prepareStatement(String sql) throws ClassNotFoundException, SQLException {
		getConnection();
		return con.prepareStatement(sql);
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Statement s =createStatement();
		String sql="SELECT ACCOUNT_NUMBER FROM ACCOUNT";
		ResultSet rs=s.executeQuery(sql);
		while(rs.next()) {
			System.out.println(rs.getString(1));
		}
		ConnectionDB.getConnection().close();
	}

}
