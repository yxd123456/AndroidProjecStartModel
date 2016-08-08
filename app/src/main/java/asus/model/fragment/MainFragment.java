package asus.model.fragment;

import android.view.View;
import android.widget.TextView;

import asus.model.R;
import asus.model.base.context.BaseFragment;
import asus.model.utils.JniUtil;
import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by asus on 2016/7/25.
 */
public class MainFragment extends BaseFragment {


    @InjectView(R.id.tv1)
    TextView tv1;
    @InjectView(R.id.tv2)
    TextView tv2;
    @InjectView(R.id.tv3)
    TextView tv3;

    @Override
    protected void onCreateView(View v) {
        tv2.setText(JniUtil.getStringFromC());
        tv3.setText(JniUtil.getStringFromC2());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

}
