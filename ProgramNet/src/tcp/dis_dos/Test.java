package tcp.dis_dos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		File f = new File("d:\\test\\1.mp3");
		FileInputStream fis = new FileInputStream(f);
		FileOutputStream fos = new FileOutputStream("d:\\test\\2.mp3");
		long len = f.length();
		long byteReaded = 0;
		int byteMustRead = (len - byteReaded > 1024) ? 1024 : (int) (len-byteReaded);
		byte[] buffer = new byte[byteMustRead];
		int i = 0;
		while(byteMustRead != 0){
			i = fis.read(buffer);
			fos.write(buffer, 0, i);
			byteReaded += i;
			byteMustRead = (len-byteReaded > 1024) ? 1024 : (int) (len - byteReaded);
			buffer = new byte[byteMustRead];
		}
		fis.close();
		fos.flush();
		fos.close();
	}
}
