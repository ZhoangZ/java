
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestMD5 {

    private String convertHashToString(String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(text.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    private String covertCheckSum(String path) throws NoSuchAlgorithmException {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            InputStream fis = new FileInputStream(path);
            byte[] buffer = new byte[1024];
            int nread;
            while ((nread = fis.read(buffer)) != -1) {
                md.update(buffer, 0, nread);
            }
            StringBuilder result = new StringBuilder();
            for (byte b : md.digest()) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (Exception ex) {
            ex.toString();
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        TestMD5 md5 = new TestMD5();
        System.out.println(md5.convertHashToString("aaa"));
        System.out.println(md5.covertCheckSum("D:/red.png"));
    }

}