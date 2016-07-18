package top.wefor.materialshow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created on 16/6/30.
 *
 * @author ice
 */
public class FoodListFragment extends Fragment {
    public static int sTimes = 0;

    @BindView(R.id.refreshLayout) SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lay_recycler_view, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (sTimes % 2 == 0) {
            mRefreshLayout.setEnabled(false);
        }
        sTimes++;
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);

        final ArrayList<String> strings = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            String name = "I Know You  " + i;
            strings.add(name);
        }

        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                int px32 = 32;
                TextView textView = new TextView(getActivity());
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
