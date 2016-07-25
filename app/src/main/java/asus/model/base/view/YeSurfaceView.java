package asus.model.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by asus on 2016/7/19.
 */
public abstract class YeSurfaceView extends SurfaceView
        implements SurfaceHolder.Callback, Runnable{

    private SurfaceHolder holder;
    protected Canvas canvas;
    private boolean isDrawing;


    public YeSurfaceView(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        holder = getHolder();
        holder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        initView_(context);
    }

    protected void initView_(Context context){

    };

    public YeSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isDrawing = false;
    }

    @Override
    public void run() {
        while (isDrawing){
            prepareDraw();
            draw();
            afterDraw();
        }
    }

    protected void afterDraw() {
    }

    protected void prepareDraw() {
    }


    public void draw(){
        try {
            synchronized (holder){
                canvas = holder.lockCanvas();
                draw_(canvas);
            }
        }catch (Exception e){
        }finally {
            if(canvas != null) holder.unlockCanvasAndPost(canvas);
            onCanvasUnlockInFinally(canvas);
        }
    }

    protected void onCanvasUnlockInFinally(Canvas canvas) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                onActionDown(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                onActionMove(x, y);
                break;
            case MotionEvent.ACTION_UP:
                onActionUp(x, y);
                break;
        }
        return true;
    }

    protected void onActionMove(int x, int y) {
    }

    protected void onActionUp(int x, int y) {
    }

    protected void onActionDown(int x, int y) {
    }


    protected abstract void draw_(Canvas canvas);


}
