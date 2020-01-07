#include "demo_HelloJNICpp.h"
#include <jni.h>
#include  <iostream>
 
using namespace std;
 
JNIEXPORT void JNICALL Java_demo_HelloJNICpp_sayHello
  (JNIEnv *, jobject)  {
    cout << "Hello World from C++!" << endl;
    return;
}
