//
// Created by asus on 2016/7/25.
//
#include "asus_model_utils_JniUtil.h"//导入头文件

JNIEXPORT jstring JNICALL Java_asus_model_utils_JniUtil_getStringFromC
  (JNIEnv *env, jclass){
       return env->NewStringUTF("Hello C++");//返回字符串
  }