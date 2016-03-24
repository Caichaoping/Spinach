package com.cc.spinach.ui;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.cc.spinach.R;
import com.cc.spinach.base.BaseActivity;
import com.cc.spinach.fragment.SettingFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 注释：设置界面
 * 作者：菠菜 on 2016/3/24 10:28
 * 邮箱：971859818@qq.com
 */
public class SettingActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_content);
        ButterKnife.bind(this);
        toolbar.setTitle("设置");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setToolBack(toolbar);
        getFragmentManager().beginTransaction().add(R.id.fragment_content, new SettingFragment()).commitAllowingStateLoss();

    }
}
