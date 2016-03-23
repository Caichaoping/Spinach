package com.cc.spinach.net;

import android.content.Context;
import android.widget.ImageView;

import com.cc.spinach.R;
import com.squareup.picasso.Picasso;

/**
 * 注释：
 * 作者：菠菜 on 2016/3/23 09:13
 * 邮箱：971859818@qq.com
 */
public class PicassoUtils  {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument view error");
        }
        Picasso.with(context)
                .load(url)
                .placeholder(placeholder)
                .error(error)
                .into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if(imageView == null) {
            throw new IllegalArgumentException("argument view error");
        }
        Picasso.with(context)
                .load(url)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_loadfail)
                .into(imageView);
    }
}
