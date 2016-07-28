package asus.model.base.adapter;

import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import asus.model.base.thread.RxJava;
import rx.Subscriber;

/**
 * Created by asus on 2016/7/28.
 */
public class AddMoreUtil<T> {

    public static boolean addedFooterView = false;
    public static boolean needShowFoot = false;


    private RecyclerView rv;
    private List<T> list;

    public AddMoreUtil(RecyclerView recyclerView, List<T> list){
        rv = recyclerView;
        this.list = list;
    }

    public void enableAddMore(UpdateListener listener, TimeConsumingListener timeListener){
        LinearLayoutManager lm= (LinearLayoutManager) rv.getLayoutManager();

        rv.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isScrollToBottom = ((RecyclerView.ViewHolder)lm.findViewByPosition(lm.findLastCompletelyVisibleItemPosition()).getTag()).getPosition()+1 == rv.getAdapter().getItemCount()-1;
                if(isScrollToBottom){
                    if(!addedFooterView) {
                        addedFooterView = true;
                        needShowFoot = true;
                        //list.addAll(new ArrayList<T>(1));
                        list.add((T) new Object());
                        rv.getAdapter().notifyDataSetChanged();
                        RxJava.thread(msg -> {
                            list.remove(list.size() - 1);
                            listener.update(msg);
                            rv.getAdapter().notifyDataSetChanged();
                            addedFooterView = false;
                            needShowFoot = false;
                        }, timeListener::time);
                    }
                }
            }
        });
    }

    public interface UpdateListener{
        void update(Message msg);
    }

    public interface TimeConsumingListener{
        void time(Subscriber sub);
    }

}
