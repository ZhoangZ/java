package database.connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionAccess implements ConnectionType {
	public Connection con = null;
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if(con==null) {
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		con = DriverManager.getConnection("jdbc:odbc:newstar2");
		}
		return con;
	}

}
