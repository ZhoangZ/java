package p2;
import java.io.Serializable;

public class SinhVien implements Serializable{
	private static final long serialVersionUID = 1L;
	private String name;
	private int mssv;
	private int age;
	private double score;
	
	public SinhVien(String name, int mssv, int age, double score) {
		this.name = name;
		this.mssv = mssv;
		this.age = age;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMssv() {
		return mssv;
	}

	public void setMssv(int mssv) {
		this.mssv = mssv;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public String toString(){
		return "Ten: " + name +", mssv: " + mssv +", tuoi: "+ age + ", diem: "+score;
	}
}
