package asus.model.base.thread;

import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by asus on 2016/7/28.
 */
public class BundleUtil {

    private Bundle bundle;

    public BundleUtil(){
        bundle = new Bundle();
    }

    public <T> BundleUtil put(String name, T t){
        if(t instanceof String){
            bundle.putString(name , (String)t);
        }else if(t instanceof  String[]){
            bundle.putStringArray(name, (String[]) t);
        }else if(t instanceof  Boolean){
            bundle.putBoolean(name, (Boolean)t);
        }else if(t instanceof Boolean[]){
            bundle.putBooleanArray(name, (boolean[])t);
        }else if(t instanceof  Double){
            bundle.putDouble(name, (Double)t);
        }else if(t instanceof  Double[]){
            bundle.putDoubleArray(name, (double[])t);
        }else if(t instanceof  Integer){
            bundle.putInt(name, (Integer)t);
        }else if(t instanceof  Integer[]){
            bundle.putIntArray(name, (int[])t);
        }else if(t instanceof Serializable){
            bundle.putSerializable(name, (Serializable)t);
        }else if(t instanceof Parcelable){
            bundle.putParcelable(name, (Parcelable)t);
        }
        return this;
    }

    public Bundle end(){
        return bundle;
    }

}
