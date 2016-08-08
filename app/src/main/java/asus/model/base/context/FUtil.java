package asus.model.base.context;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import asus.model.R;
import asus.model.base.design.SnackBarUtil;
import asus.model.base.design.TabLayoutUtil;
import asus.model.base.observer.FragmentSubject;
import asus.model.base.thread.BundleUtil;
import asus.model.base.thread.RxJava;

/**
 * Created by asus on 2016/7/21.
 */
public abstract class FUtil extends FragmentSubject implements EnumInterface{

    public static final int VIEWGROUP_MATCH = ViewGroup.LayoutParams.MATCH_PARENT;
    public static final int VIEWGROUP_WRAP = ViewGroup.LayoutParams.WRAP_CONTENT;

    public int[] ints(int...arr){
        return arr;
    }

    public String[] strs(String...arr){
        return arr;
    }

    public List<String> strList(String...arr){
        return Arrays.asList(arr);
    }

    public List<String> strList(int num, String str){
        List<String> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            list.add(str+(i+1));
        }
        return list;
    }

    public <T> List<T> list(T...ts){
        return Arrays.asList(ts);
    }

    public <T> List<T> quickList(int num, SetT s){
        List<T> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            list.add((T) s.set(i+1));
        }
        return list;
    }

    public interface SetT<T>{
        T set(int i);
    }

    public static <T extends View> void invertVisible(T t){
        if(t.getVisibility()==View.GONE){
            t.setVisibility(View.VISIBLE);
        }else if(t.getVisibility() == View.VISIBLE){
            t.setVisibility(View.GONE);
        }
    }

    public String txt(TextView tv){
        return tv.getText().toString();
    }

    public View inflate(int id){
        View v = LayoutInflater.from(getActivity()).inflate(id, null);
        return v;
    }

    public void txt(TextView tv, String string){
        tv.setText(string);
    }

    public void txt(TextView tv, int string){
        tv.setText(string);
    }

    public void click(View v, View.OnClickListener listener){
        v.setOnClickListener(listener);
    }

    //fragments.addAll(Arrays.asList(mf, sf, tf, df));
    public static <T extends Object> void addAll(List list, T...t){
        list.addAll(Arrays.asList(t));
    }

    public void sleep(long s){
        RxJava.sleep(s);
    }

    public static void getFocus(View view){
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    public static void snack(View target, String toast, int len, String ok, SnackBarUtil.OnClickListener listener){
        new SnackBarUtil(target, toast, SnackBarUtil.LONG)
                .show().action(ok, listener);
    }

    public static void snackEasy(View target, String toast){
        new SnackBarUtil(target, toast, SnackBarUtil.LONG)
                .show().action("确定", (view, snackbar) -> snackbar.dismiss());
    }

    public static TabLayoutUtil tabLayout(TabLayout tl, int vpId){
        TabLayoutUtil util = new TabLayoutUtil(tl, vpId);
        return util;
    }

    public BundleUtil bundle(){
        return new BundleUtil();
    }


}
