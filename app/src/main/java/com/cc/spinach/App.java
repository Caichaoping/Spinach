package com.cc.spinach;

import android.app.Application;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/21 16:59
 * 邮箱：971859818@qq.com
 */
public class App extends Application {

    public  static App mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CustomActivityOnCrash.install(this);
    }
}
