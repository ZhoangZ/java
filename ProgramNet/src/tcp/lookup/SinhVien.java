package tcp.lookup;
public class SinhVien {
	String name;
	int mssv;
	int old;
	double score;
	
	public SinhVien(String name, int mssv, int old, double score) {
		this.name = name;
		this.mssv = mssv;
		this.old = old;
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

	public int getOld() {
		return old;
	}

	public void setOld(int old) {
		this.old = old;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public String toString(){
		return name + "-" + mssv + "-" + old +"-" + score;
	}
}
