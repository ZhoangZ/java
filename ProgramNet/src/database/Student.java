package database;

import java.util.Date;

public class Student {
	private String sid;
	private String firstName;
	private String lastName;
	private Date birthday;
	private String birthplace;

	public Student(String sid, String firstName, String lastName, Date birthday, String birthplace) {
		this.sid = sid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.birthplace = birthplace;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}
	@Override
	public String toString() {
		
		return this.sid+":"+this.firstName+":"+this.lastName+":"+this.getBirthday()+":"+this.getBirthplace();
	}
}
