package rmi.general;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import rmi.lookup.SinhVien;
import rmi.lookup.SinhVienDAO;

public class Server {
	public Server() throws RemoteException {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		List<SinhVien> list;
		SinhVienDAO dao = new SinhVienDAO();
		while (true) {
			String command = sc.nextLine();
			StringTokenizer token = new StringTokenizer(command, "\t");
			String request = token.nextToken();
			switch (request) {
			case "findByName":

				list = dao.findByName(token.nextToken());
				for (SinhVien sv : list) {
					System.out.println(sv);
				}

				break;
			case "findByAge":
				list = dao.findByAge(Integer.parseInt(token.nextToken()));
				for (SinhVien sv : list) {
					System.out.println(sv);
				}
				break;
			case "findByScore":
				list = dao.findMoreThanScore(Double.parseDouble(token
						.nextToken()));
				for (SinhVien sv : list) {
					System.out.println(sv);
				}
				break;
			default:
				System.out.println("Nhap lenh sai vui long nhap lai");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Random r = new Random();
		System.out.println(r.nextInt(24));
	}
	
}
