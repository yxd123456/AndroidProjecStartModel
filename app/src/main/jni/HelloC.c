//
// Created by asus on 2016/7/26.
//
#include "asus_model_utils_JniUtil.h"//导入头文件
#include <android/log.h>

//Java_asus_model_utils_JniUtil_getStringFromC_
JNIEXPORT jstring JNICALL Java_asus_model_utils_JniUtil_getStringFromC2
  (JNIEnv *env, jclass jc){
    return (*env)->NewStringUTF(env, "Hello C");//返回字符串
  }