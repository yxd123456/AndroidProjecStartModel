package asus.model.base.context;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;

import asus.model.R;
import asus.model.base.design.SnackBarUtil;
import asus.model.base.design.TabLayoutUtil;
import asus.model.base.design.TextInputUtil;
import asus.model.base.observer.ActivitySubject;

/**
 * Created by asus on 2016/7/21.
 */
public abstract class AUtil extends ActivitySubject{

    public static <T extends View> void invertVisible(T t){
        if(t.getVisibility()==View.GONE){
            t.setVisibility(View.VISIBLE);
        }else if(t.getVisibility() == View.VISIBLE){
            t.setVisibility(View.GONE);
        }
    }

    //fragments.addAll(Arrays.asList(mf, sf, tf, df));
    public static <T extends Object> void addAll(List list, T...t){
        list.addAll(Arrays.asList(t));
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

}
