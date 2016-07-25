package asus.model.utils;

/**
 * Created by asus on 2016/7/25.
 */
public class JniUtil {

    public static native String getStringFromC();

    static {
        System.loadLibrary("jni-test");//之前在build.gradle里面设置的so名字，必须一致}
        //加载SO文件的意思
    }
}
