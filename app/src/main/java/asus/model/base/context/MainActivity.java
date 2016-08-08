package asus.model.base.context;

import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.view.Window;
import android.widget.RadioGroup;

import java.util.Arrays;
import java.util.List;

import asus.model.R;
import asus.model.base.annotation.ViewGet;
import asus.model.base.annotation.ViewInject;
import asus.model.base.thread.RxJava;
import asus.model.base.utils.FragmentUtils;
import asus.model.fragment.MainFragment;
import rx.Subscriber;

/**
 * 托管所有Fragment的Activity，程序的入口
 */
public class MainActivity extends AUtil {

    public static final String TAG = MainActivity.class.getSimpleName();
    enum BACKFLAG{
        NEED_TO, MAIN_FRAGEMNT, SECOND_FRAGMENT;
    }
    public MainFragment mf = new MainFragment();
    public FragmentUtils fragmentUtils;
    public DrawerLayout drawerLayout;

    //收藏所有Fragment的集合
    public List<Fragment> fragments = Arrays.asList(mf);
    @ViewGet(value = R.id.rg_bottom)
    public RadioGroup rgBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.design_navigation_view);
        ViewInject.inject(this);
        fragmentUtils = new FragmentUtils(this, fragments, R.id.fragment_container);
    }

    //切换Fragment的RadioButton
    public void rb1(android.view.View v) {

        //fragmentUtils.switchFragment(mf);
        startActivity(intent(WebActivity.class).put("msg","Hello").end());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStack();//将最上层Fragment弹出回退栈
        switch (BACKFLAG.NEED_TO) {//根据回退标志来决定返回到哪个Fragment
            case MAIN_FRAGEMNT:
                fragmentUtils.switchFragment(mf);
                break;
            case SECOND_FRAGMENT:

                break;
        }
        invertVisible(rgBottom);//重新显示底部切换栏
    }

    @Override
    public void update() {
    }
}
