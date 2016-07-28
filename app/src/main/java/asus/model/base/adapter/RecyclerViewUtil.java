package asus.model.base.adapter;

import android.content.Context;
import android.os.Message;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;

import java.util.ArrayList;
import java.util.List;

import asus.model.R;
import asus.model.base.context.AUtil;
import asus.model.base.context.BaseFragment;
import asus.model.base.context.FUtil;
import asus.model.base.thread.RxJava;
import rx.Subscriber;

/**
 * Created by asus on 2016/7/26.
 */
public class RecyclerViewUtil extends FUtil {


    private EasyRVAdapter adapter;
    private Context context;
    private RecyclerView rv;

    public RecyclerViewUtil(Context ctx, RecyclerView recyclerView){
        context = ctx;
        rv = recyclerView;
    }

    public RecyclerViewUtil manager(){
        rv.setLayoutManager(new LinearLayoutManager(context));
        return this;
    }

    public RecyclerViewUtil manager(RecyclerView.LayoutManager manager){
        if(manager == null){
            rv.setLayoutManager(new LinearLayoutManager(context));
        }else {
            rv.setLayoutManager(manager);
        }
        return this;
    }

    public RecyclerViewUtil animator() {
        rv.setItemAnimator(new DefaultItemAnimator());
        return this;
    }

    public RecyclerViewUtil animator(RecyclerView.ItemAnimator animator){
        if(animator==null){
            rv.setItemAnimator(new DefaultItemAnimator());
        }else {
            rv.setItemAnimator(animator);
        }
        return this;
    }

    public RecyclerViewUtil decorate(){
        rv.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        return this;
    }

    public RecyclerViewUtil decorate(RecyclerView.ItemDecoration decoration){
        if(decoration == null){
            rv.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        }else {
            rv.addItemDecoration(decoration);
        }
        return this;
    }

    public RecyclerViewUtil fixed(boolean b){
        rv.setHasFixedSize(b);
        return this;
    }

    /**
     *
     * @param list 数据
     * @param data
     * @param cellView
     * @param itemLayoutId
     * @param <T>
     * @return
     */
    public <T> AddMoreUtil setAdapter(List<T> list, onBindData data, SetMultiCellView cellView, int...itemLayoutId){
        adapter = new EasyRVAdapter<T>(context, list, itemLayoutId) {
            @Override
            protected void onBindData(EasyRVHolder viewHolder, int position, T item) {
                try{
                    data.bind(viewHolder, position);
                }catch (Exception e){

                }
            }
            @Override
            public int getLayoutIndex(int position, T item) {
                return cellView.setMultiCellView(position);
            }
        };
        rv.setAdapter(adapter);
        return new AddMoreUtil(rv, list);
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
    public  <T> void initRV(Context context, RecyclerView rv, List<T> list, onBindData data, SetMultiCellView cellView, int...cellLayoutId
                            ){
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
        rv.setHasFixedSize(true);
        adapter = new EasyRVAdapter<T>(context, list, cellLayoutId) {
            @Override
            protected void onBindData(EasyRVHolder viewHolder, int position, T item) {
                try{
                    data.bind(viewHolder, position);
                }catch (Exception e){

                }
            }

            @Override
            public int getLayoutIndex(int position, T item) {
                return cellView.setMultiCellView(position);
            }
        };
        rv.setAdapter(adapter);
    }

    /**
     * 使用自定义的分割线来产生自定义的列表
     */
    public  <T, M extends RecyclerView.LayoutManager> void initRV(Context context, RecyclerView rv, List<T> list, onBindData data, SetMultiCellView cellView, RecyclerView.ItemDecoration decoration, M manager, int...cellLayoutId){
        rv.setLayoutManager(manager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.addItemDecoration(decoration);
        rv.setHasFixedSize(true);
        adapter = new EasyRVAdapter<T>(context, list, cellLayoutId) {
            @Override
            protected void onBindData(EasyRVHolder viewHolder, int position, T item) {
                try{
                    data.bind(viewHolder, position);
                }catch (Exception e){

                }
            }
            @Override
            public int getLayoutIndex(int position, T item) {
                return cellView.setMultiCellView(position);
            }
        };
        rv.setAdapter(adapter);
    }

    public EasyRVAdapter getAdapter(){
        return adapter;
    }


    public interface onBindData{
        void bind(EasyRVHolder holder, int pos);

    }

    public interface SetMultiCellView{
        int setMultiCellView(int position);
    }

}
