package asus.exercise01.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * Created by asus on 2016/7/19.
 */
public class DrawboardView extends YeSurfaceView {

    private Path path;
    private Paint paint;
    private long start;
    private long end;

    public DrawboardView(Context context) {
        super(context);
    }

    public DrawboardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void initView_(Context context) {
        path = new Path();
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    @Override
    protected void prepareDraw() {
        start = System.currentTimeMillis();
    }

    @Override
    protected void afterDraw() {
        end = System.currentTimeMillis();
        if(end - start <100){
            try {
                Thread.sleep(100-(end-start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void draw_(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path, paint);
    }

    @Override
    protected void onActionDown(int x, int y) {
        path.moveTo(x, y);
    }

    @Override
    protected void onActionMove(int x, int y) {
        path.lineTo(x, y);
    }


}
