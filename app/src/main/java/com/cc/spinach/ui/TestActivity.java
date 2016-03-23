package com.cc.spinach.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.cc.spinach.R;
import com.cc.spinach.base.BaseActivity;
import com.cc.spinach.model.NewModel;
import com.cc.spinach.net.CallbackListener;
import com.cc.spinach.net.Http;
import com.cc.spinach.net.NewsJsonUtils;
import com.cc.spinach.net.Urls;
import com.cc.spinach.utils.L;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/23 14:24
 * 邮箱：971859818@qq.com
 */
public class TestActivity extends BaseActivity {
    @Bind(R.id.tv_test)
    TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

        Http.get(Urls.getNewsUrl(Urls.NBA_ID,20),new CallbackListener<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                List<NewModel> newModels = NewsJsonUtils.readJsonNewsBeans(result,Urls.NBA_ID);
                tvTest.setText(result);
                for (NewModel newModel:newModels){
                      L.d(newModel.toString());
                }

            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
