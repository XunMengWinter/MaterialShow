# MaterialShow
Material Design 优雅处理复杂视图

### 介绍：
http://www.jianshu.com/p/e64a4e08f57a

### 我的简书：
http://www.jianshu.com/users/e36e60b5af17/latest_articles

### 主要实现
利用Android最新控件实现：
* 保持ViewPager中Fragment的状态。
* 快速绑定已有数据的TabLayout与ViewPager。
* 在Fragment中显示标题栏，并为该标题栏添加菜单。
* Fragment嵌套Fragment。
* 一行代码解决RecyclerView等视图的滚动冲突。
* 为Fragment中的头部视图(含图片)实现沉浸式。
* 最简单的沉浸式版本适配。

### 效果图
![使用Martial Design优雅实现](http://upload-images.jianshu.io/upload_images/2066824-b58f96c2b4dc80ec.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/540/h/960)

### 要点解读
```
// 保存ViewPager中Fragment的状态
mViewPager.setOffscreenPageLimit(mFragments.size());
```
```
// 在Fragment中显示标题栏，并为该标题栏添加菜单。
mToolbar.inflateMenu(R.menu.main); 
mToolbar.setOnMenuItemClickListener(this);
```

```
// 快速绑定已有数据的TabLayout与ViewPager。
mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
mTabLayout.addOnTabSelectedListener(this);
```

```
// Fragment中ViewPager嵌套Fragment，需要注意传入的FragmentMangager()，不然子Fragment可能无法显示。
mViewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()...
```

```
// 一行代码解决RecyclerView等视图的滚动冲突，具体请看[源码](https://github.com/XunMengWinter/MaterialShow/tree/master) 。
app:layout_behavior="@string/appbar_scrolling_view_behavior"
```

```
// 为Fragment中的头部视图(含图片)实现沉浸式。

<style name="AppTheme.NoActionBar">
    <item name="windowActionBar">false</item>
    <item name="windowNoTitle">true</item>
    <item name="android:windowDrawsSystemBarBackgrounds" tools:targetApi="lollipop">true</item>
    <item name="android:statusBarColor" tools:targetApi="lollipop">@android:color/transparent    </item>
</style>
// 为该Fragment所在的Activity设定该主题，会带来一些输入框问题，但是由于AppBar是在Fragment里面，如果在Activity里面的话直接设定为上面的主题就可以实现头部图片沉浸式了。
<style name="AppTheme.NoActionBar.Immerse">
    <item name="android:windowTranslucentStatus" tools:targetApi="kitkat">true</item>
</style>
```

```
// 最简单的沉浸式版本适配，请看源码。

// 为ToolBar设置MarginTop.
android:layout_marginTop="@dimen/height_status_bar"
//在 dimen.xml 文件中添加
<dimen name="height_status_bar">0dp</dimen>
//在 dimen.xml(v19) 文件中添加
<dimen name="height_status_bar">22dp</dimen>

// p.s 这样一来安卓4.4以上toolbar就距离顶部22dp了，而比4.4低的版本是没有沉浸式这一概念的，所以设为0dp就好。

```

