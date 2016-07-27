package asus.model.base.observer;

import android.support.v4.app.Fragment;

/**
 * Created by asus on 2016/7/27.
 */
public class FragmentSubject extends Fragment {

    protected Observer ob = Observer.getObserver();

    protected void update(){

    }

    public MessageUtil msg(){
        return new MessageUtil();
    }

}
