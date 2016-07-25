package asus.exercise01.fragment;

import android.view.View;
import android.widget.TextView;
import asus.exercise01.R;
import asus.exercise01.base.BaseFragment;
import butterknife.BindView;

/**
 * Created by asus on 2016/7/25.
 */
public class MainFragment extends BaseFragment {

    @BindView(R.id.tv_hello_world)
    TextView tvHelloWorld;

    @Override
    protected void onCreateView(View v) {
        tvHelloWorld.setOnClickListener(view -> toast(R.string.HelloWorld));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

}
