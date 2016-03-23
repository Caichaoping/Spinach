package com.cc.spinach.net;

import com.cc.spinach.model.NewDetailModel;
import com.cc.spinach.model.NewModel;
import com.cc.spinach.utils.L;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/23 14:19
 * 邮箱：971859818@qq.com
 */
public class NewsJsonUtils {

    private final static String TAG = "NewsJsonUtils";

    /**
     * 将获取到的json转换为新闻列表对象
     */
    public static List<NewModel> readJsonNewsBeans(String res, String value) {
        List<NewModel> beans = new ArrayList<NewModel>();
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get(value);
            if(jsonElement == null) {
                return null;
            }
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (int i = 1; i < jsonArray.size(); i++) {
                JsonObject jo = jsonArray.get(i).getAsJsonObject();
                if (jo.has("skipType") && "special".equals(jo.get("skipType").getAsString())) {
                    continue;
                }
                if (jo.has("TAGS") && !jo.has("TAG")) {
                    continue;
                }

                if (!jo.has("imgextra")) {
                    NewModel news = JsonUtils.deserialize(jo, NewModel.class);
                    beans.add(news);
                }
            }
        } catch (Exception e) {
            L.e("readJsonNewsBeans error----->"+e);
        }
        return beans;
    }

    public static NewDetailModel readJsonNewsDetailBeans(String res, String docId) {
        NewDetailModel newsDetailBean = null;
        try {
            JsonParser parser = new JsonParser();
            JsonObject jsonObj = parser.parse(res).getAsJsonObject();
            JsonElement jsonElement = jsonObj.get(docId);
            if(jsonElement == null) {
                return null;
            }
            newsDetailBean = JsonUtils.deserialize(jsonElement.getAsJsonObject(), NewDetailModel.class);
        } catch (Exception e) {
            L.e("readJsonNewsBeans error----->"+e);
        }
        return newsDetailBean;
    }
}
