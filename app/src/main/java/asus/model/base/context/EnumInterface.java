package asus.model.base.context;

import android.content.Context;

/**
 * Created by asus on 2016/7/29.
 */
public interface EnumInterface {

    enum MF implements EnumInterface{
        TAG1(1), TAG2("value"), AGEKEY("yi");
        public String s;
        public int i;

        private MF(){

        }

        private MF(int intVal){
            i = intVal;
        }

        private MF(String strVal){
            s = strVal;
        }

    }

}
