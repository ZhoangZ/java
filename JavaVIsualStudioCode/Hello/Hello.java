import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Hello {
    public static void main(String[] args) throws Exception {
        
       // String message=br.readLine();
        byte[] buffer = new byte[1024];
        int bytesRead = System.in.read(buffer);
        byte[] data = new byte[bytesRead];
        System.arraycopy(buffer, 0, data, 0, bytesRead);
        BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(data), "UTF-8"));

        for (int i = 0; i < data.length; i++)
            System.out.printf("%X\n", data[i]);
            System.out.println(Arrays.toString(data));
            System.out.println(new String(data, "UTF-8"));
        System.out.println(br.read());
        
    }

}