package asus.model.fragment;

import android.view.View;
import android.widget.TextView;

import asus.model.R;
import asus.model.base.BaseFragment;
import butterknife.ButterKnife;
import butterknife.InjectView;

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
