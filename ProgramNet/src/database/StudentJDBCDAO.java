package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentJDBCDAO {
	public static Student findStudentsBySID(String sid) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.DEFAULT.getConnection();
		if (con == null)
			return null;
		try {
			String sql = "SELECT first_name, last_name, birthday, birthplace FROM student WHERE sid = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, sid);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				String fn = rs.getString(1);
				String ln = rs.getString(2);
				Date bd = rs.getDate(3);
				String bp = rs.getString(4);
				Student s = new Student(sid, fn, ln, bd, bp);
				return s;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentJDBCDAO.class.getName(), e.getMessage());
			return null;
		}
	}

	public static List<Student> findStudentsByField(String field, String value)
			throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.DEFAULT.getConnection();
		List<Student> list = new ArrayList<Student>();
		if (con == null)
			return list;
		try {
			String sql = "SELECT sid, first_name, last_name, birthday, birthplace FROM student WHERE ? = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, field);
			st.setString(2, value);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String sid = rs.getString(0);
				String fn = rs.getString(1);
				String ln = rs.getString(2);
				Date bd = rs.getDate(3);
				String bp = rs.getString(4);
				Student s = new Student(sid, fn, ln, bd, bp);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentJDBCDAO.class.getName(), e.getMessage());
		}
		return list;
	}

	public static List<Student> getAllStudents() throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.DEFAULT.getConnection();
		List<Student> list = new ArrayList<Student>();
		if (con == null)
			return list;
		try {
			String sql = "SELECT sid, first_name, last_name, birthday, birthplace FROM student";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String sid = rs.getString(1);
				String fn = rs.getString(2);
				String ln = rs.getString(3);
				Date bd = rs.getDate(4);
				String bp = rs.getString(5);
				Student s = new Student(sid, fn, ln, bd, bp);
				list.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentJDBCDAO.class.getName(), e.getMessage());
		}
		return list;
	}

	public static boolean insertStudent(Student student) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.DEFAULT.getConnection();
		if (con == null)
			return false;
		if (findStudentsBySID(student.getSid()) != null) {
			return false;
		}
		try {
			String sql = "INSERT INTO student VALUES(?, ?, ?, ?, ?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, student.getSid());
			st.setString(2, student.getFirstName());
			st.setString(3, student.getLastName());
			st.setTimestamp(4, new java.sql.Timestamp(student.getBirthday().getTime()));
			st.setString(5, student.getBirthplace());
			int rs = st.executeUpdate();
			return (rs > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentJDBCDAO.class.getName(), e.getMessage());
			return false;
		}
	}

	public static boolean updateStudent(Student student) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.DEFAULT.getConnection();
		if (con == null)
			return false;
		try {
			String sql = "UPDATE student SET first_name = ?, last_name = ?, birthday = ?, birthplace = ? WHERE sid = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(5, student.getSid());
			st.setString(1, student.getFirstName());
			st.setString(2, student.getLastName());
			st.setDate(3, new java.sql.Date(student.getBirthday().getTime()));
			st.setString(4, student.getBirthplace());
			int rs = st.executeUpdate();
			return (rs > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentJDBCDAO.class.getName(), e.getMessage());
			return false;
		}
	}

	public static boolean deleteStudent(Student student) throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.DEFAULT.getConnection();
		if (con == null)
			return false;
		try {
			String sql = "DELETE FROM student WHERE sid = ?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, student.getSid());
			int rs = st.executeUpdate();
			return (rs > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentJDBCDAO.class.getName(), e.getMessage());
			return false;
		}
	}

	public static boolean deleteAllStudent() throws ClassNotFoundException, SQLException {
		Connection con = DBConnection.DEFAULT.getConnection();
		if (con == null)
			return false;
		try {
			String sql = "DELETE FROM student";
			int rs=0;

			PreparedStatement st2 = con.prepareStatement(sql);
			rs = st2.executeUpdate();
			System.out.println(rs);
			st2.close();
//			Statement st = con.createStatement();
//			rs = st.executeUpdate(sql);
//			System.out.println(rs);
//			st.close();
			
			return (rs > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentJDBCDAO.class.getName(), e.getMessage());
			return false;
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Student s1 = new Student("001", "Nguyen Van", "A", new java.util.Date(), "Can Tho");
		Student s2 = new Student("002", "Nguyen Van", "B", new java.util.Date(), "Tp. HCM");
		Student s3 = new Student("003", "Nguyen Van", "C", new java.util.Date(), "Tp. Hai Phong");
		Student s4 = new Student("003", "Nguyen Van", "D", new java.util.Date(), "Tp. Hai Phong");
		List<Student> list = getAllStudents();
		for (Student s : list) {
			System.out.println(s.toString());
		}
		System.out.println("----------------------------------------------");
		System.out.println("Insert: "+s1.getSid()+"\t"+insertStudent(s1));
		System.out.println("Insert: "+s2.getSid()+"\t"+insertStudent(s2));
		System.out.println("Insert: "+s3.getSid()+"\t"+insertStudent(s3));
		System.out.println("Update: "+s4.getSid()+"\t"+updateStudent(s4));
		//System.out.println("Delete: "+s3.getSid()+"\t"+deleteStudent(s3));
		System.out.println("----------------------------------------------");
		list = getAllStudents();
		for (Student s : list) {
			System.out.println(s.toString());
		}
		System.out.println("Delete All: "+"\t"+deleteAllStudent());
	}
}
