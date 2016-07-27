package asus.model.base.adapter;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.List;

import asus.model.R;
import asus.model.base.context.AUtil;
import asus.model.base.context.BaseFragment;
import asus.model.base.context.FUtil;

/**
 * Created by asus on 2016/7/26.
 */
public class RecyclerViewUtil extends FUtil {


    private EasyRVAdapter adapter;

    public RecyclerViewUtil(){

    }

    private RecyclerViewUtil util;


    public static RecyclerViewUtil getUtil(){
        return new RecyclerViewUtil();
    }

    /**
     * 使用默认的分割线来产生纵向列表
     * @param context 上下文
     * @param rv View对象
     * @param list 数据集合
     * @param cellLayoutId 列表项布局
     * @param data 接口实现数据捆绑
     * @param <T>
     */
    public  <T> void initRV(Context context, RecyclerView rv, List<T> list, int cellLayoutId, onBindData data){
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        rv.setHasFixedSize(true);
        adapter = new EasyRVAdapter<T>(context, list, cellLayoutId) {
            @Override
            protected void onBindData(EasyRVHolder viewHolder, int position, T item) {
                data.bind(viewHolder, position);
            }
        };
        rv.setAdapter(adapter);
    }

    /**
     * 使用自定义的分割线来产生纵向列表
     */
    public  <T> void initRV(Context context, RecyclerView rv, List<T> list, int cellLayoutId, RecyclerView.ItemDecoration decoration, onBindData data){
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(decoration);
        rv.setHasFixedSize(true);
        adapter = new EasyRVAdapter<T>(context, list, cellLayoutId) {
            @Override
            protected void onBindData(EasyRVHolder viewHolder, int position, T item) {
                data.bind(viewHolder, position);
            }
        };
        rv.setAdapter(adapter);
    }

    /**
     * 使用自定义的分割线来产生自定义的列表
     */
    public  <T, M extends RecyclerView.LayoutManager> void initRV(Context context, RecyclerView rv, List<T> list, int cellLayoutId, RecyclerView.ItemDecoration decoration, M manager, onBindData data){
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(decoration);
        rv.setHasFixedSize(true);
        adapter = new EasyRVAdapter<T>(context, list, cellLayoutId) {
            @Override
            protected void onBindData(EasyRVHolder viewHolder, int position, T item) {
                data.bind(viewHolder, position);
            }
        };
        rv.setAdapter(adapter);
    }

    public EasyRVAdapter getAdapter(){
        return adapter;
    }

    @Override
    public void update() {

    }

    public interface onBindData{
        void bind(EasyRVHolder holder, int pos);
    }

}
