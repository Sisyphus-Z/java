#include <jni.h>
	#include "com_HelloWorld.h"
	#include <stdio.h>
	JNIEXPORT void JNICALL Java_com_HelloWorld_hello(JNIEnv *env,jobject obj, jstring name){
		
		const char *str; 
		str = (*env)->GetStringUTFChars(env, name, NULL); 
		if (str == NULL) { 
			return; 
		} 
		
		printf("Hello World! %s \n", str );
		return;
	}