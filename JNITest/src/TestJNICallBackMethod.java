public class TestJNICallBackMethod {
   static {
      System.loadLibrary("TestJNICallBackMethod"); // myjni.dll (Windows) or libmyjni.so (Unixes)
   }
 
   // Declare a native method that calls back the Java methods below
   private native void nativeMethod();
 
   // To be called back by the native code
   private void callback() {
      System.out.println("In Java");
   }
 
   private void callback(String message) {
      System.out.println("In Java with " + message);
   }
 
   private double callbackAverage(int n1, int n2) {
      return ((double)n1 + n2) / 2.0;
   }
 
   // Static method to be called back
   private static String callbackStatic() {
      return "From static Java method";
   }

   // Test Driver 
   public static void main(String args[]) {
      new TestJNICallBackMethod().nativeMethod();
   }
}