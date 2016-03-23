package com.cc.spinach.net;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/22 15:12
 * 邮箱：971859818@qq.com
 */
public class Urls  {

    //http://c.m.163.com/nc/article/headline/T1348647909107/0-5.html  头条
    //http://c.m.163.com/nc/article/list/T1348654060988/0-5.html 汽车

    public static final int PAZE_SIZE = 20;

    public static final String HOST = "http://c.m.163.com/";
    public static final String END_URL = "-" + PAZE_SIZE + ".html";
    public static final String END_DETAIL_URL = "/full.html";
    // 头条
    public static final String TOP_URL = HOST + "nc/article/headline/";
    public static final String TOP_ID = "T1348647909107";
    // 新闻详情
    public static final String NEW_DETAIL = HOST + "nc/article/";

    public static final String COMMON_URL = HOST + "nc/article/list/";

    // 汽车
    public static final String CAR_ID = "T1348654060988";
    // 笑话
    public static final String JOKE_ID = "T1350383429665";
    // nba
    public static final String NBA_ID = "T1348649145984";

    // 图片
    public static final String IMAGES_URL = "http://api.laifudao.com/open/tupian.json";

    // 天气预报url
    public static final String WEATHER = "http://wthrcdn.etouch.cn/weather_mini?city=";

    //百度定位
    public static final String INTERFACE_LOCATION = "http://api.map.baidu.com/geocoder";

    // 获取新闻列表的绝对Url
    public static String getNewsUrl(String id,int FromPage){
        StringBuffer sb = new StringBuffer(COMMON_URL);
        sb.append(id).append("/").append(FromPage).append("-20.html");
        return sb.toString();
    }

    // 获取新闻详情绝对url
    public static String getNewsDetailUrl(String docId){
        StringBuffer sb = new StringBuffer(Urls.NEW_DETAIL);
        sb.append(docId).append(Urls.END_DETAIL_URL);
        return sb.toString();
    }
}
