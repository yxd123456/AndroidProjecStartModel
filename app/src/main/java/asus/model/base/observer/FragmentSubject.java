package asus.model.base.observer;

import android.os.Message;
import android.support.v4.app.Fragment;
import android.widget.Button;

/**
 * Created by asus on 2016/7/27.
 */
public class FragmentSubject extends Fragment {

    protected Observer ob = Observer.getObserver();

    protected void update(){
        //链式编程
        Message message = msg().obj("hello").what(0x123).end();

        //传统编程
        Message message1 = Message.obtain();
        message1.obj = "Hello";
        message.what = 0x123;


    }

    public MessageUtil msg(){
        return new MessageUtil();
    }

    public <T> Message msg(T t){
        return new MessageUtil().obj(t).end();
    }

    public Message msgWhat(int what){
        return new MessageUtil().what(what).end();
    }

    public <T> Message msg(int what, T t){
        return new MessageUtil().what(what).obj(t).end();
    }



}
