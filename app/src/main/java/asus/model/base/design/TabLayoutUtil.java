package asus.model.base.design;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.List;

import asus.model.base.adapter.ModelPagerAdapter;
/*
        List<TestFragment> list = Arrays.asList(
                new TestFragment("Hello1"),
                new TestFragment("Hello12"),
                new TestFragment("Hello13"),
                new TestFragment("Hello14"),
                new TestFragment("Hello15"),
                new TestFragment("Hello16")
        );

        TabLayoutUtil util = new TabLayoutUtil(tl, R.id.vp).setMode(TabLayoutUtil.SCROLLABLE)
                .baseSet(Color.GRAY, Color.parseColor("#ff0000"), Color.BLUE, 10);
        //第一种方式
//        util.addTabs(util.getTab().setText("TAB1"),
//                        util.getTab().setText("TAB1").setIcon(R.mipmap.ic_launcher),
//                        util.getTab().setText("TAB1"),
//                        util.getTab().setText("TAB1"),
//                        util.getTab().setText("TAB1"),
//                        util.getTab().setText("TAB1")).combineViewPager(list, Arrays.asList("故事1","故事1","故事1","故事1","故事1","故事1"));
        //第二种方式
        util.addViewPager(list, Arrays.asList("故事1","故事1","故事1","故事1","故事1","故事1"));
 */
/**
 * Created by asus on 2016/7/26.
 * ViewPager+ActionBar的升级版
 */
public class TabLayoutUtil {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentManager manager;

    public static final int FIXED = TabLayout.MODE_FIXED;
    public static final int SCROLLABLE = TabLayout.MODE_SCROLLABLE;

    public  TabLayoutUtil(TabLayout tabLayout,int vpId){
        this.tabLayout = tabLayout;
        viewPager = (ViewPager) ((ViewGroup)tabLayout.getParent()).findViewById(vpId);
        manager = ((FragmentActivity)tabLayout.getContext()).getSupportFragmentManager();

    }

    public TabLayout.Tab getTab(){
        return tabLayout.newTab();
    }

    public TabLayoutUtil baseSet(int tabTextColor, int tabSelectedTextColor,
                                 int tabIndicatorColor, int tabIndicatorHeight) {
        tabLayout.setTabTextColors(tabTextColor, tabSelectedTextColor);
        tabLayout.setSelectedTabIndicatorColor(tabIndicatorColor);
        tabLayout.setSelectedTabIndicatorHeight(tabIndicatorHeight);
        return this;
    }

    public TabLayoutUtil baseSet(String tabTextColor, String tabSelectedTextColor,
                                 String tabIndicatorColor, String tabIndicatorHeight) {
        tabLayout.setTabTextColors(Color.parseColor(tabTextColor), Color.parseColor(tabSelectedTextColor));
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor(tabIndicatorColor));
        tabLayout.setSelectedTabIndicatorHeight(Color.parseColor(tabIndicatorHeight));
        return this;
    }

    public TabLayoutUtil setMode(int mode){
        tabLayout.setTabMode(mode);
        return this;
    }

    public TabLayoutUtil addTabs(TabLayout.Tab...tabs){
        for (int i = 0; i < tabs.length; i++) {
            if(i==0){
                tabLayout.addTab(tabs[i], true);
            } else {
                tabLayout.addTab(tabs[i], false);
            }
        }
        return this;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public <T extends Fragment> TabLayoutUtil combineViewPager(List<T> fragments, List<String> titles){
        ModelPagerAdapter adapter = new ModelPagerAdapter(manager, fragments, titles);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position, positionOffset, true);
            }

            @Override
            public void onPageSelected(int position) {
                //CommonUtils.getFocus(viewPager);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return this;
    }

    public <T extends Fragment> TabLayoutUtil addViewPager(List<T> fragments, List<String> titles){
        ModelPagerAdapter adapter = new ModelPagerAdapter(manager, fragments, titles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
        return this;
    }

}
