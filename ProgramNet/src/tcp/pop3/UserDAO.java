package tcp.pop3;
import java.util.ArrayList;

public class UserDAO {
	static ArrayList<User> list = new ArrayList<>();
	static {
		list.add(new User("abc", "123"));
        list.add(new User("teo", "123"));
        list.add(new User("ti", "123"));
        list.add(new User("suu", "123"));
        list.add(new User("dan", "123"));
	}
	
	public static boolean check(String user){
		for(User u : list){
			if(u.getName().equals(user))
				return true;
		}
		return false;
	}
	
	public static boolean check(String user, String pass){
		for(User u : list){
			if((u.getName().equals(user)) && (u.getPass().equals(pass)))
				return true;
		}
		return false;
	}
}
