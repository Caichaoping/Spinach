package com.cc.spinach.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cc.spinach.R;

/**
 * 作者：菠菜 2016/3/21 21:45
 * 邮箱：971859818.com
 */
public class NewsListFragment extends Fragment {

    private int mType = NewsFragment.NEWS_TYPE_TOP;

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
        switch (mType) {
            case NewsFragment.NEWS_TYPE_TOP:
                view.setBackgroundColor(getResources().getColor(R.color.material_pink_100));
                break;
            case NewsFragment.NEWS_TYPE_NBA:
                view.setBackgroundColor(getResources().getColor(R.color.material_purple_100));
                break;
            case NewsFragment.NEWS_TYPE_CARS:
                view.setBackgroundColor(getResources().getColor(R.color.material_blue_100));
                break;
            case NewsFragment.NEWS_TYPE_JOKES:
                view.setBackgroundColor(getResources().getColor(R.color.material_green_A100));
                break;

        }
        return view;
    }
}
