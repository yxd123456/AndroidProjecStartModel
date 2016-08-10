package asus.model.base.context;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;

import java.util.List;

import asus.model.R;
import asus.model.base.adapter.AddMoreUtil;
import asus.model.base.adapter.LayoutManagerUtil;
import asus.model.base.adapter.RecyclerViewUtil;
import asus.model.base.adapter.RefreshUtil;
import asus.model.base.thread.BundleUtil;
import butterknife.ButterKnife;

/**
 * Created by asus on 2016/7/20.
 */
public abstract class BaseFragment extends FUtil {

    private static final String NULL = "NULL";
    protected static MainActivity activity;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        this.view = view;
        ButterKnife.inject(this, view);
        activity = (MainActivity) getActivity();
        onCreateView(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * 此方法仅限于需要刷新的列表是二级布局时使用，总之view应该是列表的直接父布局
     * 参数1：设置列表的宽度
     * 参数2：设置列表的高度
     * 参数3：设置列表在根布局中的索引
     */
    public RefreshUtil refresh(int...params){
        return new RefreshUtil(getActivity(), view, () -> params);
    }

    public void setRefresh(boolean refresh){
        RefreshUtil.setRefreshing(refresh);
    }

    public LayoutManagerUtil getLL(){
        return LayoutManagerUtil.get1(getActivity());
    }

    public LayoutManagerUtil getGL(int spanCount){
        return LayoutManagerUtil.get2(getActivity(), spanCount);
    }

    protected  <T> AddMoreUtil adapter(RecyclerView rv, List<T> list, RecyclerViewUtil.onBindData data,
                                                        RecyclerViewUtil.SetMultiCellView cellView, int...cell){
        RecyclerViewUtil recyclerViewUtil = new RecyclerViewUtil(activity, rv);
        recyclerViewUtil.initRV(getActivity(), rv, list, data, cellView, cell);
        return new AddMoreUtil(rv, list);
    }

    public RecyclerViewUtil getViewUtil(RecyclerView rv){
        return new RecyclerViewUtil(activity, rv);
    }



    public static <T> void log1(T t){
        if(t instanceof String){
            Log.d("TAG1", (String) t);
        }else {
            Log.d("TAG1", t+"");
        }

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

    public <T extends FragmentActivity> void startActivity(Context context, Class<T> aClass){
        context.startActivity(new Intent(context, aClass));
    }
}
