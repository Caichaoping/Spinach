package com.cc.spinach.utils;

import android.util.Log;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/22 14:47
 * 邮箱：971859818@qq.com
 */
public class L  {
    private static final String tag = "CCP";
    public final static String MATCH = "%s->%s->%d";
    public final static String CONNECTOR = ":<--->:";
    private static boolean isDebug = true;

    public static String buildHeader() {
        StackTraceElement stack = Thread.currentThread().getStackTrace()[4];
        return String.format(MATCH, stack.getClassName(), stack.getMethodName(),
                stack.getLineNumber()) + CONNECTOR;
    }

    public static void i(Object msg) {
        if (!isDebug) {
            return;
        }
        Log.i(tag, buildHeader() + String.valueOf(msg));
    }

    public static void d(Object msg) {
        if (!isDebug) {
            return;
        }
        Log.d(tag, buildHeader() + String.valueOf(msg));
    }
}
