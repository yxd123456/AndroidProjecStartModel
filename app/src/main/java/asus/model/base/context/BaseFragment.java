package asus.model.base.context;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;

import java.util.List;

import asus.model.R;
import asus.model.base.adapter.LayoutManagerUtil;
import asus.model.base.adapter.RecyclerViewUtil;
import asus.model.base.adapter.RefreshUtil;
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
        View v = inflater.inflate(getLayoutId(), container, false);
        view = v;
        ButterKnife.inject(this, v);
        activity = (MainActivity) getActivity();
        onCreateView(v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * 此方法仅限于需要刷新的列表是二级布局时使用，总之view应该是列表的直接父布局
     */
    public RefreshUtil getRefreshUtil(RefreshUtil.getViewParams params){
        return new RefreshUtil(getActivity(), view, params);
    }

    public LayoutManagerUtil getLL(){
        return LayoutManagerUtil.get1(getActivity());
    }

    public LayoutManagerUtil getGL(int spanCount){
        return LayoutManagerUtil.get2(getActivity(), spanCount);
    }

    protected  <T> EasyRVAdapter rvBindData(RecyclerView rv, List<T> list, int cell, RecyclerViewUtil.onBindData data){
        RecyclerViewUtil recyclerViewUtil = new RecyclerViewUtil();
        recyclerViewUtil.initRV(getActivity(), rv, list, R.layout.recyclerview_cell, data::bind);
        return recyclerViewUtil.getAdapter();
    }

    protected  <T, M extends RecyclerView.LayoutManager> EasyRVAdapter rvBindData(RecyclerView rv, List<T> list, int cellLayoutId, RecyclerView.ItemDecoration decoration, M manager, RecyclerViewUtil.onBindData data){
        RecyclerViewUtil recyclerViewUtil = new RecyclerViewUtil();
        recyclerViewUtil.initRV(getActivity(), rv, list, cellLayoutId, decoration, manager, data);
        return recyclerViewUtil.getAdapter();
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
