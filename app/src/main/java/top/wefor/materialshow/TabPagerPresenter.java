package top.wefor.materialshow;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;

/**
 * 处理TabLayout与ViewPager
 * <p/>
 * Created on 16/6/30.
 *
 * @author ice, GitHub: https://github.com/XunMengWinter
 */
public class TabPagerPresenter {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    public TabPagerPresenter(TabLayout tabLayout, ViewPager viewPager) {
        mTabLayout = tabLayout;
        mViewPager = viewPager;
    }

    /*快速绑定含数据的二者*/
    public void bindHasData() {
        // adding functionality to tab and viewpager to manage each other when a page is changed or when a tab is selected
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.i("xyz", "pos " + mTabLayout.getSelectedTabPosition());
                mViewPager.setCurrentItem(mTabLayout.getSelectedTabPosition(), true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
