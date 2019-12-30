package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionAccess implements ConnectionType {

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("sun.Jdbc.Odbc.JdbcOdbcDriver");
		Connection con = DriverManager.getConnection("jdbc:odbc:book");
		return con;
	}

}
