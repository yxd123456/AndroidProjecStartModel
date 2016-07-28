package asus.model.base.thread;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by asus on 2016/7/28.
 */
public class IntentUtil {

    private Intent intent;

    public IntentUtil(Context context, Class aClass){
        intent = new Intent(context, aClass);
    }

    public <T> IntentUtil put(String name, T t){
        if(t instanceof String){
            intent.putExtra(name , (String)t);
        }else if(t instanceof  String[]){
            intent.putExtra(name, (String[]) t);
        }else if(t instanceof  Boolean){
            intent.putExtra(name, (Boolean)t);
        }else if(t instanceof Boolean[]){
            intent.putExtra(name, (Boolean[])t);
        }else if(t instanceof  Double){
            intent.putExtra(name, (Double)t);
        }else if(t instanceof  Double[]){
            intent.putExtra(name, (Double[])t);
        }else if(t instanceof  Integer){
            intent.putExtra(name, (Integer)t);
        }else if(t instanceof  Integer[]){
            intent.putExtra(name, (Integer[])t);
        }else if(t instanceof Bundle){
            intent.putExtra(name, (Bundle)t);
        }else if(t instanceof Serializable){
            intent.putExtra(name, (Serializable)t);
        }else if(t instanceof Parcelable){
            intent.putExtra(name, (Parcelable)t);
        }
        return this;
    }

    public Intent end(){
        return intent;
    }



}



