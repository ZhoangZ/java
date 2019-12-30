package database;
import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionType {
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
