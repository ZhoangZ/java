package database;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentProcedureODBC {
	public static boolean dropProcedure(String procedure) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.DEFAULT.getConnection();
		if (con == null) return false;
		String drop = "DROP PROCEDURE IF EXISTS " + procedure;
		Statement st = null;
	    try {
	    	st = con.createStatement();
	        st.execute(drop);
	        return true;
	    } catch (SQLException e) {
	    	MyLogger.singleton.log(StudentProcedureODBC.class.getName(), e.getMessage());
	    	e.printStackTrace();
	    	return false;
	    }
	}
	/**
 CREATE PROC procProductsAddItem(inProductName VARCHAR(40), inSupplierID LONG, inCategoryID LONG) " & _

"AS INSERT INTO Products (ProductName, SupplierID, CategoryID) " & _

"Values (inProductName, inSupplierID, inCategoryID);"

"CREATE PROC procProductsUpdateItem(inProductID LONG, " & _
" inProductName VARCHAR(40)) " & _

"AS UPDATE Products SET ProductName = inProductName " & _

" WHERE ProductID = inProductID;"
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static boolean createInsertProcedure() throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.DEFAULT.getConnection();
		if (con == null) return false;
		dropProcedure("insert_student");
		//CREATE PROCEDURE Sales_By_Country [Beginning Date] DateTime, [Ending Date] DateTime;
		String insert_pro = "CREATE PROCEDURE insert_student(id CHAR(3), IN fn VARCHAR(30), IN ln VARCHAR(10), IN bd DATE, IN bp VARCHAR(30)) ";
		insert_pro += " AS ";
		insert_pro += " INSERT INTO student(sid, first_name, last_name, birthday, birthplace) ";
		insert_pro += " VALUES(id, fn, ln, bd, bp); ";
		try {
			Statement st = con.createStatement();
			st.executeUpdate(insert_pro);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentProcedureODBC.class.getName(), e.getMessage());
			return false;
		}
}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		createInsertProcedure();
		//dropProcedure("insert_student");
	}}
