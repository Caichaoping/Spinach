package com.cc.spinach.ui;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.cc.spinach.R;
import com.cc.spinach.base.BaseActivity;
import com.cc.spinach.model.NewDetailModel;
import com.cc.spinach.model.NewModel;
import com.cc.spinach.net.CallbackListener;
import com.cc.spinach.net.Http;
import com.cc.spinach.net.NewsJsonUtils;
import com.cc.spinach.net.PicassoUtils;
import com.cc.spinach.net.Urls;
import com.cc.spinach.utils.T;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/21 17:48
 * 邮箱：971859818@qq.com
 */
public class NewDetailsActivity extends BaseActivity {


    @Bind(R.id.ivImage)
    ImageView ivImage;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @Bind(R.id.progress)
    ProgressBar progress;
    @Bind(R.id.new_content)
    HtmlTextView newContent;

    private NewModel newModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdetails);
        ButterKnife.bind(this);
        initView();
        newModel = (NewModel) getIntent().getSerializableExtra("new");
        collapsingToolbar.setTitle(newModel.getTitle());
        PicassoUtils.display(NewDetailsActivity.this, ivImage, newModel.getImgsrc());
        progress.setVisibility(View.VISIBLE);
        getNewDetail(newModel.getDocid());
    }

    // 获取新闻详情
    private void getNewDetail(final String id) {

        Http.get(Urls.getNewsDetailUrl(id),new CallbackListener<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                NewDetailModel newDetailModel = NewsJsonUtils.readJsonNewsDetailBeans(result, id);
                // 显示内容
                progress.setVisibility(View.GONE);
                newContent.setHtmlFromString(newDetailModel.getBody(),new HtmlTextView.LocalImageGetter());
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                T.showLong("获取数据失败");
                progress.setVisibility(View.GONE);
            }
        });

    }

    // 界面初始化
    private void initView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}
