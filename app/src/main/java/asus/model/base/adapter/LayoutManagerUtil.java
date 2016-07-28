package asus.model.base.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by asus on 2016/7/26.
 */
public class LayoutManagerUtil {

    private static LinearLayoutManager manager1;
    private static GridLayoutManager manager2;

    private static LayoutManagerUtil util;

    private LayoutManagerUtil(){

    }

    public static LayoutManagerUtil get1(Context ctx){
        if(util == null) util = new LayoutManagerUtil();
        if(manager1 == null) manager1 = new LinearLayoutManager(ctx);
        return util;
    }

    public static LayoutManagerUtil get2(Context ctx, int spanCount){
        if(util == null) util = new LayoutManagerUtil();
        if(manager2 == null) manager2 = new GridLayoutManager(ctx, spanCount);
        return util;
    }

    public LayoutManagerUtil ori1(int ori){
        manager1.setOrientation(ori);
        return util;
    }

    public LayoutManagerUtil ori2(int ori){
        manager2.setOrientation(ori);
        return util;
    }

    public LayoutManagerUtil revert1(boolean revert){
        manager1.setReverseLayout(revert);
        return util;
    }

    public LayoutManagerUtil revert2(boolean revert){
        manager2.setReverseLayout(revert);
        return util;
    }

    public LinearLayoutManager end1(){
        return manager1;
    }

    public GridLayoutManager end2(){
        return manager2;
    }


}
