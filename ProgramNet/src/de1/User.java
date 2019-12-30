package de1;

public class User {
	String ten;
	String matkhau;
	
	public User(String ten, String matkhau) {
		super();
		this.ten = ten;
		this.matkhau = matkhau;
	}

	
	@Override
	public String toString() {
		return this.ten+":"+this.matkhau;
	}

}
