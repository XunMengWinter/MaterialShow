package top.wefor.materialshow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 16/7/18.
 *
 * @author ice
 */
public class NestedListActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.imageView) ImageView mImageView;
    @BindView(R.id.nestedScrollView) NestedScrollView mNestedScrollView;
    @BindView(R.id.statusBar_bg_view) View mStatusBarBgView;
    @BindView(R.id.titleBar_bg_view) View mTitleBarBgView;
    @BindView(R.id.topBar_layout) LinearLayout mTopBarLayout;

    private float mTopBarHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_list);
        ButterKnife.bind(this);

        mTopBarLayout.post(new Runnable() {
            @Override
            public void run() {
                mTopBarHeight = mTopBarLayout.getMeasuredHeight();
                mNestedScrollView.scrollTo(0, 0);
            }
        });

        mNestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.i("xyz", "xyz " + scrollX + " " + scrollY + " " + oldScrollX + " " + oldScrollY);
                if (scrollY < mTopBarHeight) {
                    mStatusBarBgView.setAlpha(scrollY / mTopBarHeight);
                    mTitleBarBgView.setAlpha(scrollY / mTopBarHeight);
                }
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        final ArrayList<String> strings = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            String name = "Hello Summer  " + i;
            strings.add(name);
        }

        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                int px32 = 32;
                TextView textView = new TextView(NestedListActivity.this);
                textView.setPadding(px32, px32, px32, px32);

                return new RecyclerView.ViewHolder(textView) {
                    @Override
                    public String toString() {
                        return super.toString();
                    }
                };
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                TextView textView = (TextView) holder.itemView;
                textView.setText(strings.get(position));
            }

            @Override
            public int getItemCount() {
                return strings.size();
            }
        });

    }
}
