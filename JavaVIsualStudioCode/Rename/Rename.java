import java.io.File;
public class Rename{
    public static void main(String [] args){
        System.out.println(System.getProperty("user.dir"));
        String[] list =new File(System.getProperty("user.dir")).list();
        for (String fName : list) {
            System.out.println(new File(fName));
            
        }
    }
}