package asus.model.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import io.sweers.barber.Barber;

/**
 * Created by asus on 2016/7/22.
 */
public class BaseView extends View {

    private BasePaint paint;
    protected Context context;

    public BaseView(Context context) {

        this(context, null, 0);
    }

    public BaseView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Barber.style(this, attrs, getAttrs(), defStyleAttr);
        this.context = context;
        init();
    }

    protected void init() {
    }

    protected int[] getAttrs() {
        return new int[0];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new BasePaint();
        onDraw(canvas, paint);
    }

    protected void onDraw(Canvas canvas, BasePaint paint) {
    }

    int width = 0;
    int height = 0;
    int modeWidth;
    int modeHeight;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        if(modeWidth == MeasureSpec.EXACTLY){
            width = sizeWidth;
        }
        if(modeHeight == MeasureSpec.EXACTLY){
            height = sizeHeight;
        }
        if(modeWidth == MeasureSpec.AT_MOST){
            width = getAtMostWidth();
        }
        if(modeHeight == MeasureSpec.AT_MOST ){
            height = getAtMostHeight();
        }
        setMeasuredDimension(width, height);

    }

    protected int getAtMostHeight() {
        return 0;
    }

    protected int getAtMostWidth() {
        return 0;
    }


}
