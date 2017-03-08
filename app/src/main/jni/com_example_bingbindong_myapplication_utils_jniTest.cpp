/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include <stdio.h>
#include "testJni.h"
/* Header for class com_example_bingbindong_myapplication_utils_jniTest */

#ifndef _Included_com_example_bingbindong_myapplication_utils_jniTest
#define _Included_com_example_bingbindong_myapplication_utils_jniTest
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_example_bingbindong_myapplication_utils_jniTest
 * Method:    changeFloatFromCpp
 * Signature: ([FI)[F
 */
JNIEXPORT jfloatArray JNICALL Java_com_example_bingbindong_myapplication_utils_jniTest_changeFloatFromCpp
  (JNIEnv *env, jobject obj, jfloatArray javaFloat, jint javaInt){

        float *javaFloatArray = env->GetFloatArrayElements(javaFloat,NULL);
        changeFloatByCpp(javaFloatArray,javaInt);
        jfloatArray ArrayFromCpp = env->NewFloatArray(javaInt);
        env->SetFloatArrayRegion(ArrayFromCpp,0,javaInt,javaFloatArray);
        return ArrayFromCpp;

  }

#ifdef __cplusplus
}
#endif
#endif
