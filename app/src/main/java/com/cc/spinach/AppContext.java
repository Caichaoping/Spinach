package com.cc.spinach;

import android.app.Application;

import com.cc.spinach.utils.L;

import java.util.Properties;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/24 09:54
 * 邮箱：971859818@qq.com
 */
public class AppContext extends Application {

    public static AppContext mContext;

    private boolean isShowMap;
    private boolean isReceiveMsg;

    public static AppContext getInstance() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        CustomActivityOnCrash.install(this);
        initAppConfig();
    }

    // 初始化系统配置文件
    private void initAppConfig() {
        isReceiveMsg = getIsReceiveMsg();
        isShowMap = getIsShowMap();
    }

    // 设置图片显示
    public void setLoadMap(boolean b) {
        L.d("设置图片显示:"+b);
        setProperty(AppConfig.CONF_LOAD_MAP, String.valueOf(b));
        isReceiveMsg = b;
    }

    // 从应用上下文获取是否显示图片
    public boolean getIsShowMapFromCache() {
        return isShowMap;
    }

    // 从配置文件获取是否显示图片
    public boolean getIsShowMap(){
        String perf_map = getProperty(AppConfig.CONF_LOAD_MAP);
        L.d("获取是否显示图片"+perf_map);
        if (perf_map == null || perf_map.isEmpty()){
            L.d("报空返回true");
            return true;
        }else {
            L.d("返回"+Boolean.parseBoolean(perf_map));
            return Boolean.parseBoolean(perf_map);
        }

    }

    // 设置消息接收
    public void setReceiveMsg(boolean b) {
        setProperty(AppConfig.CONF_RECEIVE_MSG, String.valueOf(b));
        isReceiveMsg = b;
        L.d("设置消息接收"+isReceiveMsg);
    }

    // 从应用上下文获取是否接收消息
    public boolean getIsReceiveMsgFromCache() {
        return isReceiveMsg;
    }

    // 从配置文件获取是否接收消息
    public boolean getIsReceiveMsg() {
        String perf_message = getProperty(AppConfig.CONF_RECEIVE_MSG);
        L.d("获取是否接收消息"+perf_message);
        if (perf_message == null || perf_message.isEmpty()){
            L.d("报空返回true");
            return true;
        }else {
            L.d("返回"+Boolean.parseBoolean(perf_message));
            return Boolean.parseBoolean(perf_message);
        }

    }

    // 以下为配置文件的相关方法
    public boolean containsProperty(String key) {
        Properties props = getProperties();
        return props.containsKey(key);
    }

    public void setProperties(Properties ps) {
        AppConfig.getAppConfig(this).set(ps);
    }

    public Properties getProperties() {
        return AppConfig.getAppConfig(this).get();
    }

    public void setProperty(String key, String value) {
        AppConfig.getAppConfig(this).set(key, value);
    }

    public String getProperty(String key) {
        return AppConfig.getAppConfig(this).get(key);
    }

    public void removeProperty(String... key) {
        AppConfig.getAppConfig(this).remove(key);
    }


}
