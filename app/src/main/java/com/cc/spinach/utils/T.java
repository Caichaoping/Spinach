package com.cc.spinach.utils;

import android.content.Context;
import android.widget.Toast;

import com.cc.spinach.AppContext;

/**
 * 注释：吐司工具类
 * 作者：菠菜 on 2016/3/22 14:47
 * 邮箱：971859818@qq.com
 */
public class T {

    private T() {

    }

    private static void show(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    private static void show(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static void showShort(int resId) {
        Toast.makeText(AppContext.mContext, resId, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(String message) {
        Toast.makeText(AppContext.mContext, message, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(int resId) {
        Toast.makeText(AppContext.mContext, resId, Toast.LENGTH_LONG).show();
    }

    public static void showLong(String message) {
        Toast.makeText(AppContext.mContext, message, Toast.LENGTH_LONG).show();
    }

}
