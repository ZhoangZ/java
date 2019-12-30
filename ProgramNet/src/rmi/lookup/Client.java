package rmi.lookup;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {
    public Client() throws RemoteException, NotBoundException {
        Registry rs = LocateRegistry.getRegistry(12345);
        ISinhVien sv = (ISinhVien) rs.lookup("sv");
        System.out.println("ban hay nhap lenh");
        @SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
        List<SinhVien> list;

        while (true) {
            String command = sc.nextLine();
            StringTokenizer token = new StringTokenizer(command, "[\t ]");
            String request = token.nextToken();
            switch (request) {
                case "findByName":

                    list = sv.findByName(token.nextToken());
                    print(list);

                    break;
                case "findByAge":
                    list = sv.findByAge(Integer.parseInt(token.nextToken()));
                    print(list);
                    break;
                case "findByScore":
                    list = sv.findMoreThanScore(Double.parseDouble(token.nextToken()));
                    print(list);
                    break;
                default:
                    System.out.println("Nhap lenh sai vui long nhap lai");
                    break;
            }
        }
    }
    
    private void print(List<SinhVien> list) {
        for (SinhVien sv1 : list) {
            System.out.println((SinhVien)sv1);
        }
    }
}