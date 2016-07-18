package top.wefor.materialshow;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 16/6/30.
 *
 * @author ice
 */
public class HomeFragment extends Fragment implements Toolbar.OnMenuItemClickListener {

    // 记得使用ButterKnife自动生成以下代码
    @BindView(R.id.banner_imageView) ImageView mBannerImageView;
    @BindView(R.id.title_tv) AppCompatTextView mTitleTv;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbar;
    @BindView(R.id.tabLayout) TabLayout mTabLayout;
    @BindView(R.id.app_bar_layout) AppBarLayout mAppBarLayout;
    @BindView(R.id.viewPager) ViewPager mViewPager;

    ArrayList<Fragment> mFragments;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mToolbar.inflateMenu(R.menu.main);  // 添加菜单
        mToolbar.setOnMenuItemClickListener(this);

        if (mFragments == null) {
            mFragments = new ArrayList<>();
            mFragments.add(new FoodListFragment());
            mFragments.add(new FoodListFragment());
        }

        mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        new TabPagerPresenter(mTabLayout, mViewPager).bindHasData();
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_search:
                showToast("search");
                return true;
            case R.id.action_share:
                showToast("share");
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "分享一篇不错的文章：Material Design初露锋芒之复杂视图处理" +
                        " \n" + "http://www.jianshu.com/p/e64a4e08f57a");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Dream Winter");
                intent = Intent.createChooser(intent, "Share to");
                startActivity(intent);
                return true;
            case R.id.action_nested_list:
                startActivity(new Intent(getActivity(),NestedListActivity.class));
                return true;
        }
        return false;
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

}
