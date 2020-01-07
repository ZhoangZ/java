package demo;
public class HelloJNICMixCpp {
    static {
       System.loadLibrary("demo_HelloJNICMixCpp"); // hello.dll (Windows) or libhello.so (Unixes)
    }
  
    // Native method declaration
    private native void sayHello();
  
    // Test Driver
    public static void main(String[] args) {
       new HelloJNICMixCpp().sayHello();  // Invoke native method
    }
 }