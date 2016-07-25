package asus.exercise01.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import asus.exercise01.annotation.ViewInject;
import butterknife.ButterKnife;

/**
 * Created by asus on 2016/7/20.
 */
public abstract class BaseFragment extends Fragment {

    private static final String NULL = "NULL";
    protected static MainActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, v);
        activity = (MainActivity) getActivity();
        onCreateView(v);
        return v;
    }

    public static void log1(String str){
        Log.d("TAG1", str);
    }

    public static void log2(String str){
        Log.d("TAG2", str);
    }

    public static void log3(String str){
        Log.d("TAG3", str);
    }

    public static void toast(String str){
        Toast.makeText(activity, str, Toast.LENGTH_SHORT).show();
    }

    public static void toast(int strId){
        Toast.makeText(activity, strId, Toast.LENGTH_SHORT).show();
    }

    protected abstract void onCreateView(View v);

    protected abstract int getLayoutId();

    public static <T> void testNull(T t){
        Log.d(NULL, "空指针测试："+(t == null));
    }
}
