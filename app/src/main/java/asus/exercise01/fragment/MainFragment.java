package asus.exercise01.fragment;

import android.os.Message;
import android.view.View;
import android.widget.TextView;

import asus.exercise01.R;
import asus.exercise01.base.BaseFragment;
import asus.exercise01.base.thread.RxJava;
import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Subscriber;

/**
 * Created by asus on 2016/7/25.
 */
public class MainFragment extends BaseFragment {

    @InjectView(R.id.tv_hello_world)
    public TextView tvHelloWorld;

    @Override
    protected void onCreateView(View v) {
        ButterKnife.inject(this, v);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

}
