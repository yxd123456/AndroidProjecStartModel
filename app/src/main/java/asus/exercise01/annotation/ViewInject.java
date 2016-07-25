package asus.exercise01.annotation;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import java.lang.reflect.Field;

import asus.exercise01.annotation.ViewGet;

/**
 * Created by asus on 2016/6/29.
 */
public class ViewInject {

    public static <T extends Activity> void inject(T activity){
        Field[] fields = activity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            //检测该字段是否包含ViewInject的注解
            if(fields[i].isAnnotationPresent(ViewGet.class)){
                ViewGet viewInject = fields[i].getAnnotation(ViewGet.class);
                int id = viewInject.value();//取出注解值
                //if(id>0){
                    //fields[i].setAccessible(true);//设置字段可访问
                try {
                    fields[i].set(activity, activity.findViewById(id));
                    //调用activity的相关方法来给该字段赋值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                //}
            }
        }
    }

    public static <T extends FragmentActivity> void inject(T activity){
        Field[] fields = activity.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            //检测该字段是否包含ViewInject的注解
            if(fields[i].isAnnotationPresent(ViewGet.class)){
                ViewGet viewInject = fields[i].getAnnotation(ViewGet.class);
                int id = viewInject.value();//取出注解值
                //if(id>0){
                //fields[i].setAccessible(true);//设置字段可访问
                try {
                    fields[i].set(activity, activity.findViewById(id));
                    //调用activity的相关方法来给该字段赋值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                //}
            }
        }
    }

    public static <T extends Fragment> void inject(T fragment, android.view.View view){
        Field[] fields = fragment.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            //检测该字段是否包含ViewInject的注解
            if(fields[i].isAnnotationPresent(ViewGet.class)){
                ViewGet viewInject = fields[i].getAnnotation(ViewGet.class);
                int id = viewInject.value();//取出注解值
                //if(id>0){
                //fields[i].setAccessible(true);//设置字段可访问
                try {
                    fields[i].set(fragment, view.findViewById(id));
                    //调用activity的相关方法来给该字段赋值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                //}
            }
        }
    }

}
