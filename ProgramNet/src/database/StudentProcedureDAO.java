package database;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentProcedureDAO {
	public static Connection con = null;
	public static Student findStudentsBySID(String sid) throws ClassNotFoundException, SQLException {
		if (con == null) 
			con = DBConnection.DEFAULT.getConnection();
		try {
			String call = "CALL find_student_by_sid(?)";
			CallableStatement st = con.prepareCall(call);
			st.setString(1, sid);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				String fn = rs.getString(0);
				String ln = rs.getString(1);
				Date bd = rs.getDate(2);
				String bp = rs.getString(3);
				Student s = new Student(sid, fn, ln, bd, bp);
				return s;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentProcedureDAO.class.getName(), e.getMessage());
			return null;
		}
	}
	public static List<Student> findStudentsByField(String field, String value) throws ClassNotFoundException, SQLException {
		List<Student> list = new ArrayList<Student>();
		if (con == null)
		con = DBConnection.DEFAULT.getConnection();
		try {
			String call = "CALL find_student_by_field(?, ?)";
			CallableStatement st = con.prepareCall(call);
			st.setString(1, field);
			st.setString(2, value);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
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
			MyLogger.singleton.log(StudentProcedureDAO.class.getName(), e.getMessage());
		}
		return list;
	}
public static List<Student> getAllStudents() throws ClassNotFoundException, SQLException {
		if (con == null) 
			con = DBConnection.DEFAULT.getConnection();
		List<Student> list = new ArrayList<Student>();
		try {
			String call = "CALL get_all_students()";
			CallableStatement st = con.prepareCall(call);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
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
			MyLogger.singleton.log(StudentProcedureDAO.class.getName(), e.getMessage());
		}
		return list;
	}
	public static boolean insertStudent(Student student) throws ClassNotFoundException, SQLException {
		if (con == null) 
		con = DBConnection.DEFAULT.getConnection();
		try {
			String call = "CALL insert_student(?, ?, ?, ?, ?)";
			CallableStatement st = con.prepareCall(call);
			st.setString(1, student.getSid());
			st.setString(2, student.getFirstName());
			st.setString(3, student.getLastName());
			st.setDate(4, new java.sql.Date(student.getBirthday().getTime()));
			st.setString(5, student.getBirthplace());
			System.out.println(st.toString());
			int rs = st.executeUpdate();
			System.out.println("RS:" + rs);
			return (rs > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentProcedureDAO.class.getName(), e.getMessage());
			return false;
		}
	}
	public static boolean updateStudent(Student student) throws ClassNotFoundException, SQLException {
		if (con == null) 
		con = DBConnection.DEFAULT.getConnection();
		try {
			String call = "CALL update_student(?, ?, ?, ?, ?)";
			CallableStatement st = con.prepareCall(call);
			st.setString(1, student.getSid());
			st.setString(2, student.getFirstName());
			st.setString(3, student.getLastName());
			st.setDate(4, new java.sql.Date(student.getBirthday().getTime()));
			st.setString(5, student.getBirthplace());
			int rs = st.executeUpdate();
			return (rs > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentProcedureDAO.class.getName(), e.getMessage());
			return false;
		}
	}
	public static boolean deleteStudent(Student student) throws ClassNotFoundException, SQLException {
		if (con == null) 
		con = DBConnection.DEFAULT.getConnection();
		try {
			String call = "CALL delete_student(?)";
			CallableStatement st = con.prepareCall(call);
			st.setString(1, student.getSid());
			int rs = st.executeUpdate();
			return (rs > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentProcedureDAO.class.getName(), e.getMessage());
			return false;
		}
	}
	public static boolean deleteAllStudent() throws ClassNotFoundException, SQLException {
		if (con == null) 
		con = DBConnection.DEFAULT.getConnection();
		try {
			String call = "CALL delete_all_student()";
			CallableStatement st = con.prepareCall(call);
			int rs = st.executeUpdate();
			return (rs > 0);
		} catch (SQLException e) {
			e.printStackTrace();
			MyLogger.singleton.log(StudentProcedureDAO.class.getName(), e.getMessage());
			return false;
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Student s1 = new Student("001", "Nguyen Van", "A", new java.util.Date(), "Can Tho");
		Student s2 = new Student("002", "Nguyen Van", "B", new java.util.Date(), "Tp. HCM");
		Student s3 = new Student("002", "Nguyen Van", "C", new java.util.Date(), "Tp. HCM");
		deleteAllStudent();
		insertStudent(s1);
		insertStudent(s2);
		updateStudent(s3);
		deleteStudent(s3);
	}}
