package asus.model.base.thread;

import android.os.Message;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by asus on 2016/7/21.
 */
public class RxJava {

    public static <T extends Object> Message getMsg(T t){
        Message message = Message.obtain();
        message.obj = t;
        return message;
    }

    public static <T extends Object> Message getMsg(T t, int what){
        Message message = Message.obtain();
        message.obj = t;
        message.what = what;
        return message;
    }

    public static Message getEmptyMsg(int what){
        Message message = Message.obtain();
        message.what = what;
        return message;
    }

    public static void sleep(long time){
        try {
            java.lang.Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void thread(final Thread thread){
        Observable.create(new Observable.OnSubscribe<Message>() {
            @Override
            public void call(Subscriber<? super Message> subscriber) {
                thread.inNewThread(subscriber);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(
                AndroidSchedulers.mainThread()
        ).subscribe(new Action1<Message>() {

            @Override
            public void call(Message msg) {
                thread.inMainThread(msg);
            }
        });
    }

    public static void thread(final MainThread mainThread, final NewThread newThread){
        Observable.create(new Observable.OnSubscribe<Message>() {
            @Override
            public void call(Subscriber<? super Message> subscriber) {
                newThread.inNewThread(subscriber);
            }
        }).subscribeOn(Schedulers.newThread()).observeOn(
                AndroidSchedulers.mainThread()
        ).subscribe(new Action1<Message>() {

            @Override
            public void call(Message msg) {
                mainThread.inMainThread(msg);
            }
        });
    }

    public  interface MainThread{
        void inMainThread(Message msg);
    }

    public interface NewThread{
        void inNewThread(Subscriber sub);
    }

    public interface Thread{
        void inMainThread(Message msg) ;
        void inNewThread(Subscriber sub);
    }

}
