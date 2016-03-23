package com.cc.spinach.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.spinach.R;
import com.cc.spinach.adapter.NewsAdapter;
import com.cc.spinach.model.NewModel;
import com.cc.spinach.net.CallbackListener;
import com.cc.spinach.net.Http;
import com.cc.spinach.net.NewsJsonUtils;
import com.cc.spinach.net.Urls;
import com.cc.spinach.ui.NewDetailsActivity;
import com.cc.spinach.utils.L;
import com.cc.spinach.utils.T;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：菠菜 2016/3/21 21:45
 * 邮箱：971859818.com
 */
public class NewsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recycle_view)
    RecyclerView recycleView;
    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout swipeRefreshWidget;

    private int mType = NewsFragment.NEWS_TYPE_TOP;
    private LinearLayoutManager mLayoutManager;
    private int FromPage = 0;
    private List<NewModel> mData;
    private NewsAdapter mAdapter;


    public static NewsListFragment newInstance(int Type) {
        Bundle bundle = new Bundle();
        NewsListFragment fragment = new NewsListFragment();
        bundle.putInt("type", Type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("type");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, null);
        ButterKnife.bind(this, view);
        initView(view);
        return view;

    }

    // 界面初始化
    private void initView(View view) {
        mData = new ArrayList<>();
        swipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary,
                R.color.material_pink_100, R.color.material_orange_300,
                R.color.colorAccent);
        swipeRefreshWidget.setOnRefreshListener(this);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recycleView.setLayoutManager(mLayoutManager);
        recycleView.setHasFixedSize(true);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setOnScrollListener(mOnScrollListener);
        mAdapter = new NewsAdapter(getActivity());
        mAdapter.setOnItemClickListener(itemClickListener);
        recycleView.setAdapter(mAdapter);
        swipeRefreshWidget.setRefreshing(true);
        onRefresh();
    }

    // 列表监听
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
        }

        // 加载更多
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                FromPage += Urls.PAZE_SIZE;
                getData(getNewsId(mType), FromPage);
                L.d("加载更多");
            }
        }
    };

    private NewsAdapter.OnItemClickListener itemClickListener = new NewsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            NewModel newModel = mAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), NewDetailsActivity.class);
            intent.putExtra("new",newModel);
            startActivity(intent);
        }
    };

    //获取数据
    private void getData(final String id, final int FromPage) {
//        mAdapter.setShowFooter(true);
        L.d("请求数据的Url为："+Urls.getNewsUrl(id, FromPage));
        Http.get(Urls.getNewsUrl(id, FromPage), new CallbackListener<String>() {
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                List<NewModel> newModels = new ArrayList<NewModel>();
                newModels = NewsJsonUtils.readJsonNewsBeans(result, id);
                mData.addAll(newModels);
                mAdapter.setShowFooter(true);
                if (newModels.size() == 0) {
                    T.showLong("没有更多数据了");
                    mAdapter.setShowFooter(false);
                }
                if (FromPage == 0) {
                    L.d("下拉刷新成功");
                } else {
                    // 没有更多数据了
                    L.d("加载更多成功");
                }
                mAdapter.setDate(mData);
                swipeRefreshWidget.setRefreshing(false);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                L.d("获取数据失败");
                swipeRefreshWidget.setRefreshing(false);
                mAdapter.setShowFooter(false);
                mAdapter.notifyDataSetChanged();
            }
        });

    }

    // 根据Fragment的类型获取新闻Id
    private String getNewsId(int i) {
        switch (i) {
            case NewsFragment.NEWS_TYPE_TOP:
                return Urls.TOP_ID;
            case NewsFragment.NEWS_TYPE_NBA:
                return Urls.NBA_ID;
            case NewsFragment.NEWS_TYPE_CARS:
                return Urls.CAR_ID;
            case NewsFragment.NEWS_TYPE_JOKES:
                return Urls.JOKE_ID;
            default:
                return Urls.TOP_ID;

        }
    }

    //下拉刷新
    @Override
    public void onRefresh() {
        FromPage = 0;
        if (null != mData) mData.clear();
        getData(getNewsId(mType), FromPage);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
