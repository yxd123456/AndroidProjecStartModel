package asus.model.base.view;

import android.content.Context;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by asus on 2016/7/19.
 */
public class ScrollerHelper {

    private static Scroller scroller;

    public static void initScroll(Context context){
        scroller = new Scroller(context);
    }

    public static void doThisInComputeScroll(View v){
        // 判断Scroller是否执行完毕
        if (scroller.computeScrollOffset()) {
            ((View) v.getParent()).scrollTo(
                    scroller.getCurrX(),
                    scroller.getCurrY());
            // 通过重绘来不断调用computeScroll
            v.invalidate();
        }
    }

    public static Scroller getScroller(){
        return scroller;
    }



}
