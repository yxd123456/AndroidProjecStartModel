package asus.exercise01.base.view;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by asus on 2016/7/19.
 */
public class YePaint {

    public static Paint getSolidPaint(int color){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        return paint;
    }

    public static Paint getStrokePaint(int color, int strokeWidth){
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        paint.setAntiAlias(true);
        return paint;
    }



}
