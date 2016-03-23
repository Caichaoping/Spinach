package com.cc.spinach.utils;


/**
 * @title  判断按钮是否快速点击的工具类，如果是返回true
 * @author bocai
 * @date 2016-3-10
 */
public class FastOnClickUtils {
    private static long lastClickTime;

    public static boolean isFastClick800() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 800) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    public static boolean isFastClick100() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 100) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
