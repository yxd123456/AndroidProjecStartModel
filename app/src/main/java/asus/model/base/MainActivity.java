package asus.model.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.List;

import asus.model.R;
import asus.model.annotation.ViewGet;
import asus.model.annotation.ViewInject;
import asus.model.fragment.MainFragment;

/**
 * 托管所有Fragment的Activity，程序的入口
 */
public class MainActivity extends CommonUtils {

    public static final String TAG = MainActivity.class.getSimpleName();
    public static int BACKFLAG = 1;

    public MainFragment mf = new MainFragment();
    public FragmentUtils fragmentUtils;

    //收藏所有Fragment的集合
    public List<Fragment> fragments = Arrays.asList((Fragment) mf);
    @ViewGet(value = R.id.rg_bottom)
    public RadioGroup rgBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewInject.inject(this);
        fragmentUtils = new FragmentUtils(this, fragments, R.id.fragment_container);
    }

    //切换Fragment的RadioButton
    public void rb1(android.view.View v) {
        fragmentUtils.switchFragment(mf);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack();//将最上层Fragment弹出回退栈
        switch (BACKFLAG) {//根据回退标志来决定返回到哪个Fragment
            case 1:
                fragmentUtils.switchFragment(mf);
                break;
        }
        invertVisible(rgBottom);//重新显示底部切换栏
    }

}
