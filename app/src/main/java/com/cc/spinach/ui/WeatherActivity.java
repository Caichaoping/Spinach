package com.cc.spinach.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.cc.spinach.R;
import com.cc.spinach.base.BaseActivity;
import com.cc.spinach.net.CallbackListener;
import com.cc.spinach.net.Http;
import com.cc.spinach.net.Urls;
import com.cc.spinach.utils.L;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/22 14:31
 * 邮箱：971859818@qq.com
 */
public class WeatherActivity extends BaseActivity {

    @Bind(R.id.tv_weather)
    TextView tvWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        initView();
        getData();
    }

    private void initView() {

    }

    private void getData() {
        String url = "";
        try {
            url = Urls.WEATHER + URLEncoder.encode("深圳","utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Http.get(url,new CallbackListener<String>(){
            @Override
            public void onSuccess(String result) {
                super.onSuccess(result);
                tvWeather.setText(result);
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                L.d("获取天气信息失败");
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
