package asus.exercise01.base.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/**
 * Created by asus on 2016/7/19.
 */
public class ModelScroller extends View {

    private int lastX;
    private int lastY;

    public ModelScroller(Context context) {
        super(context);
        ininView(context);
    }

    public ModelScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
        ininView(context);
    }

    public ModelScroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ininView(context);
    }

    private void ininView(Context context) {
        setBackgroundColor(Color.BLUE);
        ScrollerHelper.initScroll(context);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        ScrollerHelper.doThisInComputeScroll(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
            case MotionEvent.ACTION_UP:
                // 手指离开时，执行滑动过程
                View viewGroup = ((View) getParent());
                ScrollerHelper.getScroller().startScroll(
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY());
                invalidate();
                break;
        }
        return true;
    }
}
