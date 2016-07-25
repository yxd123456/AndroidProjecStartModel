package asus.model.base.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import asus.model.R;
import io.sweers.barber.Barber;
import io.sweers.barber.Kind;
import io.sweers.barber.StyledAttr;

/**
 * Created by asus on 2016/6/29.
 */
public class TitleView extends View {

    private final Rect mBound;
    private Paint mPaint;
    @StyledAttr(value = R.styleable.CustomTitleView_titleText, kind = Kind.NON_RES_STRING)
    public String title;
    @StyledAttr(value = R.styleable.CustomTitleView_titleTextColor1, kind = Kind.COLOR)
    public int color;
    @StyledAttr(value = R.styleable.CustomTitleView_titleTextSize, kind = Kind.DIMEN_PIXEL_SIZE)
    public int size;

    public TitleView(Context context) {
        this(context, null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Barber.style(this, attrs, R.styleable.CustomTitleView);
        mPaint = new Paint();
        mPaint.setTextSize(size);
        mBound = new Rect();
        mPaint.getTextBounds(title, 0, title.length(), mBound);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);

        mPaint.setColor(color);
        canvas.drawText(title, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }
}
