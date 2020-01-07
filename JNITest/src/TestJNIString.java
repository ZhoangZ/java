import java.util.Arrays;

public class TestJNIString {
    static {
       System.loadLibrary("TestJNIString"); // myjni.dll (Windows) or libmyjni.so (Unixes)
    }
    // Native method that receives a Java String and return a Java String
    private native String sayHello(String msg);
  
    public static void main(String args[]) {
       String result = new TestJNIString().sayHello("Hello from Java");
       System.out.println("In Java, the returned string is: " + result);
       System.out.println(Arrays.toString(result.getBytes()));
    }
 }