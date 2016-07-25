package asus.exercise01.base.view;

import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by asus on 2016/7/22.
 */
public class BasePaint extends Paint{

    public BasePaint color(int color){
        this.setColor(color);
        return this;
    }

    public BasePaint style(Style style){
        this.setStyle(style);
        return this;
    }

    public BasePaint flag(int flag){
        this.setFlags(flag);
        return this;
    }

    public BasePaint textSize(float size){
        this.setTextSize(size);
        return this;
    }

    public BasePaint textBounds(String text, int start, int end, Rect bounds){
        this.getTextBounds(text, start, end, bounds);
        return this;
    }

}
