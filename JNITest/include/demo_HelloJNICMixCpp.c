#include <jni.h>
#include "demo_HelloJNICMixCpp.h"
#include "demo_HelloJNICppImpl.h"
 
JNIEXPORT void JNICALL Java_demo_HelloJNICMixCpp_sayHello
  (JNIEnv *, jobject) {
    sayHello();  // invoke C++ function
    return;
}
