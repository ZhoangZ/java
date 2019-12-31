package dh07dtgl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class KetQuaXoSo {
	String tinh;
	String giaiDacBiet;
	String giaiNhat;
	String giaiNhi;
	String giaiBa;
	String giaiTu;
	String giaiNam;
	String giaiSau;
	String giaiBay;
	public String getTinh() {
		return tinh;
	}
	public void setTinh(String tinh) {
		this.tinh = tinh;
	}
	public String getGiaiDacBiet() {
		return giaiDacBiet;
	}
	public void setGiaiDacBiet(String giaiDacBiet) {
		this.giaiDacBiet = giaiDacBiet;
	}
	public String getGiaiNhat() {
		return giaiNhat;
	}
	public void setGiaiNhat(String giaiNhat) {
		this.giaiNhat = giaiNhat;
	}
	public String getGiaiNhi() {
		return giaiNhi;
	}
	public void setGiaiNhi(String giaiNhi) {
		this.giaiNhi = giaiNhi;
	}
	public String getGiaiBa() {
		return giaiBa;
	}
	public void setGiaiBa(String giaiBa) {
		this.giaiBa = giaiBa;
	}
	public String getGiaiTu() {
		return giaiTu;
	}
	public void setGiaiTu(String giaiTu) {
		this.giaiTu = giaiTu;
	}
	public String getGiaiNam() {
		return giaiNam;
	}
	public void setGiaiNam(String giaiNam) {
		this.giaiNam = giaiNam;
	}
	public String getGiaiSau() {
		return giaiSau;
	}
	public void setGiaiSau(String giaiSau) {
		this.giaiSau = giaiSau;
	}
	public String getGiaiBay() {
		return giaiBay;
	}
	public void setGiaiBay(String giaiBay) {
		this.giaiBay = giaiBay;
	}
	public KetQuaXoSo(String tinh, String giaiDacBiet, String giaiNhat, String giaiNhi, String giaiBa, String giaiTu,
			String giaiNam, String giaiSau, String giaiBay) {
		super();
		this.tinh = tinh;
		this.giaiDacBiet = giaiDacBiet;
		this.giaiNhat = giaiNhat;
		this.giaiNhi = giaiNhi;
		this.giaiBa = giaiBa;
		this.giaiTu = giaiTu;
		this.giaiNam = giaiNam;
		this.giaiSau = giaiSau;
		this.giaiBay = giaiBay;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(""+this.tinh);
		sb.append(System.getProperty("line.separator"));
		sb.append("Giải đặc biệt: "+this.giaiDacBiet);
		sb.append(System.getProperty("line.separator"));
		sb.append("Giải nhất: "+this.giaiNhat);
		sb.append(System.getProperty("line.separator"));
		sb.append("Giải nhì: "+this.giaiNhi);
		sb.append(System.getProperty("line.separator"));
		sb.append("Giải ba: "+this.giaiBa);
		sb.append(System.getProperty("line.separator"));
		sb.append("Giải tư: "+this.giaiTu);
		sb.append(System.getProperty("line.separator"));
		sb.append("Giải năm: "+this.giaiNam);
		sb.append(System.getProperty("line.separator"));
		sb.append("Giải sáu: "+this.giaiSau);
		sb.append(System.getProperty("line.separator"));
		sb.append("Giải bảy: "+this.giaiBay);
		return sb.toString();
	}

	public KetQuaXoSo() {
	}
	public static List<KetQuaXoSo> loadFile(String path) throws IOException {
		List<KetQuaXoSo> list = new ArrayList<KetQuaXoSo>();
		File f = new File(path);
		if(!f.exists()) {
			System.out.println("File not found.");
			return list;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
		String line = "";
		while((line = br.readLine())!=null&&line.indexOf(':')==-1&&line.trim()!="") {
			KetQuaXoSo kqxs= new KetQuaXoSo();
		kqxs.setTinh(line);
		kqxs.setGiaiDacBiet(br.readLine().split(":")[1].trim());
		kqxs.setGiaiNhat(br.readLine().split(":")[1].trim());
		kqxs.setGiaiNhi(br.readLine().split(":")[1].trim());
		kqxs.setGiaiBa(br.readLine().split(":")[1].trim());
		kqxs.setGiaiTu(br.readLine().split(":")[1].trim());
		kqxs.setGiaiNam(br.readLine().split(":")[1].trim());
		kqxs.setGiaiSau(br.readLine().split(":")[1].trim());
		kqxs.setGiaiBay(br.readLine().split(":")[1].trim());
		list.add(kqxs);
		}
		return list;
	}
	public static KetQuaXoSo enterDataFromKeyboard() throws IOException {
		KetQuaXoSo kqxs= new KetQuaXoSo();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Nhập tỉnh: ");
		kqxs.setTinh(br.readLine());
		System.out.print("Nhập giải đặt biệt: ");
		kqxs.setGiaiDacBiet(br.readLine().trim());
		System.out.print("Nhập giải nhất: ");
		kqxs.setGiaiNhat(br.readLine().trim());
		System.out.print("Nhập giải nhì: ");
		kqxs.setGiaiNhi(br.readLine().trim());
		System.out.print("Nhập giải ba: ");
		kqxs.setGiaiBa(br.readLine().trim());
		System.out.print("Nhập giải tư: ");
		kqxs.setGiaiTu(br.readLine().trim());
		System.out.print("Nhập giải năm: ");
		kqxs.setGiaiNam(br.readLine().trim());
		System.out.print("Nhập giải sáu: ");
		kqxs.setGiaiSau(br.readLine().trim());
		System.out.print("Nhập giải bảy: ");
		kqxs.setGiaiBay(br.readLine().trim());
		System.out.println("Nhập dữ liệu thành công.");
		return kqxs;
	}
	
	
	public boolean saveFile(String path) throws IOException {
		File f = new File(path);
		List<KetQuaXoSo> list= null;
		if(f.exists()) {
			list = loadFile(path);
			list.add(this);
		} else {
			if(!f.getParentFile().exists()) {
				f.getParentFile().mkdirs();
			}
			list = new ArrayList<KetQuaXoSo>();
			list.add(this);
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
		for (KetQuaXoSo ketQuaXoSo : list) {
			bw.write(ketQuaXoSo.toString());
			bw.newLine();
			
		}
		bw.close();
		return true;
	}
	public static void main(String[] args) {
		System.out.println("---------------------Data--------------------");
		KetQuaXoSo kqxs = new KetQuaXoSo();
		kqxs.setTinh("TIENGIANG");
		kqxs.setGiaiDacBiet("49435");
		kqxs.setGiaiNhat("37998");
		kqxs.setGiaiNhi("64385 – 97726");
		kqxs.setGiaiBa("44013 – 92535 – 56951 – 50362 – 01231 – 82573");
		kqxs.setGiaiTu("2650 – 8893 – 6053 – 9491");
		kqxs.setGiaiNam("6359 – 1534 – 9335 – 5444 – 4797 – 2963");
		kqxs.setGiaiSau("302 – 944 –177");
		kqxs.setGiaiBay("25 – 83 – 72 – 29");
		System.out.println(kqxs);
		System.out.println("---------------------SaveFile--------------------");
		try {
			System.out.println(kqxs.saveFile("D:/Test/kqxs.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("---------------------LoadFile--------------------");
		try {
			List<KetQuaXoSo> list = KetQuaXoSo.loadFile("D:/Test/kqxs.txt");
			for (KetQuaXoSo k : list) {
				System.out.println(k);
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println("---------------------GetDataFromKeyboard--------------------");
//		try {
//			KetQuaXoSo k2 = KetQuaXoSo.enterDataFromKeyboard();
//			System.out.println("---------------------DataFromKeyboard--------------------");
//			System.out.println(k2);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
