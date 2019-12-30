package tcp.lookup;
import java.util.ArrayList;

public class SinhVienDAO {
	static ArrayList<SinhVien> list = new ArrayList<>();
	static {
		list.add(new SinhVien("aaa", 1234, 20, 10));
		list.add(new SinhVien("bbb", 1235, 21, 9));
		list.add(new SinhVien("ccc", 1236, 22, 9));
		list.add(new SinhVien("ddd", 1237, 20, 5));
		list.add(new SinhVien("aaa", 1238, 19, 10));
		list.add(new SinhVien("ddd", 1239, 21, 9));
		list.add(new SinhVien("eee", 1230, 21, 9));
	}

	public static ArrayList<SinhVien> findByName(String name) {
		ArrayList<SinhVien> re = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equalsIgnoreCase(name))
				re.add(list.get(i));
		}
		return re;
	}

	public static ArrayList<SinhVien> findByAge(int old) {
		ArrayList<SinhVien> re = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getOld() == old)
				re.add(list.get(i));
		}
		return re;
	}

	public static ArrayList<SinhVien> findByScore(double score) {
		System.out.println(score);
		ArrayList<SinhVien> re = new ArrayList<>();
		for(int i = 0; i < list.size(); i++){
			if(Math.abs(list.get(i).getScore())< 0.1)
				re.add(list.get(i));
		}
		return re;
	}
}
