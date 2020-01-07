package ltm1_2017;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Banking {
	// Xác định tiền trong tài khoản
	static int getBalance(Account account) throws ClassNotFoundException, SQLException {
		PreparedStatement pre= ConnectionDB.prepareStatement("SELECT BALANCE FROM ACCOUNT WHERE ACCOUNT_NUMBER=?");
		pre.setString(1, account.getAcountNumber());
		ResultSet rs= pre.executeQuery();
		int rs2=0;
		if(rs.next()) {
			rs2= rs.getInt(1);
		}
		return rs2;
	}

	//Rút tiền từ tài khoản
	static boolean withdraw(Account account, int amount) throws ClassNotFoundException, SQLException {
		int balance= getBalance(account)-amount;
		
		if(balance<0) {
			return false;
		}
		PreparedStatement pre= ConnectionDB.prepareStatement("UPDATE ACCOUNT SET BALANCE=? WHERE ACCOUNT_NUMBER=?");
		pre.setInt(1, balance);
		pre.setString(2, account.getAcountNumber());
		boolean rs=pre.executeUpdate()==1;
		pre= ConnectionDB.prepareStatement("INSERT INTO LOG(ACCOUNT_NUMBER,NGAY, THAO_TAC,SO_LUONG, TONG) VALUES(?,?,?,?,?);");
		pre.setString(1, account.getAcountNumber());
		pre.setDate(2, new java.sql.Date(new Date().getTime()));
		pre.setString(3, "RUT  ");
		pre.setInt(4, amount);
		pre.setInt(5, amount);
		pre.executeUpdate();
		return rs;
	}

	//Gửi tiền vào tài khoản
	static boolean deposit(Account account, int amount) throws ClassNotFoundException, SQLException {
		int balance= getBalance(account)+amount;
		PreparedStatement pre= ConnectionDB.prepareStatement("UPDATE ACCOUNT SET BALANCE=? WHERE ACCOUNT_NUMBER=?");
		pre.setInt(1, balance);
		pre.setString(2, account.getAcountNumber());
		boolean rs=pre.executeUpdate()==1;
		pre= ConnectionDB.prepareStatement("INSERT INTO LOG(ACCOUNT_NUMBER,NGAY, THAO_TAC,SO_LUONG, TONG) VALUES(?,?,?,?,?);");
		pre.setString(1, account.getAcountNumber());
		pre.setDate(2, new java.sql.Date(new Date().getTime()));
		pre.setString(3, "GUI  ");
		pre.setInt(4, amount);
		pre.setInt(5, amount);
		pre.executeUpdate();
		return rs;
	}

	//Chuyển khoản
	static boolean transfer(Account fromAccount, Account toAccount, int amount) throws ClassNotFoundException, SQLException {
//		Access không có transaction 
		Connection c=ConnectionDB.getConnection();
//		try {
			
			c.setAutoCommit(false);
//		} catch (Exception e) {
//			System.out.println("Error");
//			return false;
//		}
//		
		if(!withdraw(fromAccount, amount)) {
			c.rollback();
			return false;
		}
		if(!deposit(toAccount, amount)) {
			c.rollback();
			//deposit(fromAccount, amount);
			return false;
		}
		c.setAutoCommit(true);
		c.commit();
		PreparedStatement pre = ConnectionDB.prepareStatement("INSERT INTO LOG(ACCOUNT_NUMBER,NGAY, THAO_TAC,SO_LUONG, TONG) VALUES(?,?,?,?,?);");
		pre.setString(1, fromAccount.getAcountNumber());
		pre.setDate(2, new java.sql.Date(new Date().getTime()));
		pre.setString(3, "CHUYEN");
		pre.setInt(4, amount);
		pre.setInt(5, amount);
		pre.executeUpdate();
		return true;
	}

	//In nhật ký
	static String report(Account account) throws ClassNotFoundException, SQLException {
		PreparedStatement pre= ConnectionDB.prepareStatement("SELECT NGAY, THAO_TAC,SO_LUONG, TONG FROM LOG WHERE ACCOUNT_NUMBER=?");
		pre.setString(1, account.getAcountNumber());
		ResultSet rs= pre.executeQuery();
		StringBuffer sb= new StringBuffer();
		DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		sb.append("NGAY"+"\t        ");
		sb.append("THAO TAC"+"\t");
		sb.append("SO LUONG"+"\t");
		sb.append("TONG");
		sb.append(System.getProperty("line.separator"));
		while(rs.next()) {
			sb.append(dateFormat.format(rs.getDate(1))+"\t");
			sb.append(rs.getString(2)+"\t        ");
			sb.append(rs.getString(3)+"\t        ");
			sb.append(rs.getString(4));
			sb.append(System.getProperty("line.separator"));
		}
		pre.close();
		sb.delete(sb.lastIndexOf(System.getProperty("line.separator")), sb.length());
		//sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//System.out.println(report(new Account("AA12345")));
		System.out.println(deposit(new Account("AA12345"),100000000));
		ConnectionDB.getConnection().close();
	}

}
