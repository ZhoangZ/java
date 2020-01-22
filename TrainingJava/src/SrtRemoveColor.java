import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class SrtRemoveColor {
	
	public static void removeColor(File folder) throws IOException {
		for(File f:folder.listFiles()) {
			//System.out.println(f.getName());
			if(f.isDirectory()) {
				removeColor(f);
			} else {
				if(f.getName().matches("^.*\\.srt$")) {
					if(System.console()!=null)
					System.console().printf(f.toString());else
					System.out.println(f.toString());
					removeColorFromFile(f);
				}
			}
		}
	}
	private static void removeColorFromFile(File f) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));
		StringBuffer buffer= new StringBuffer();
		String line = null;
		while((line= br.readLine())!=null) {
			buffer.append(line.replaceAll("color=\"#[0-9a-fA-F]{6}\"", "")+System.getProperty("line.separator"));
			
		}
		br.close();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f), "UTF-8"));
		
		bw.write(buffer.toString());
		bw.close();
	}
	public static void main(String[] args) {
		String pwdPath=System.getProperty("user.dir");
		if(System.console()!=null)
		System.console().printf("Current folder: \""+pwdPath+"\"");else
			System.out.println("Current folder: \""+pwdPath+"\"");
		File pwd = new File(pwdPath);
		try {
			removeColor(pwd);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
