#include <jni.h>
#include <stdio.h>
#include "demo_HelloJNIC.h"

JNIEXPORT void JNICALL Java_demo_HelloJNIC_sayHello(JNIEnv *env, jobject thisObj) {
   printf("Hello World from C !!\n");
   return;
}
