package com.cc.spinach.utils;
import android.content.Context;

import java.io.IOException;
import java.io.InputStream;


public class GsonUtils {

    public static String getJson(Context context,String filename) {
        InputStream mInputStream = null;
        String resultString = "";
        try {
            mInputStream = context.getAssets().open(filename);
            byte[] buffer = new byte[mInputStream.available()];
            mInputStream.read(buffer);
            resultString = new String(buffer, "GB2312");
        } catch (IOException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        } finally {
            try {
                mInputStream.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block  
                e.printStackTrace();
            }
        }
        return resultString.toString();
    }
}