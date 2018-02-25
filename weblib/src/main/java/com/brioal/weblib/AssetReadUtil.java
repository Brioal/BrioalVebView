package com.brioal.weblib;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2017/12/28.
 */

public class AssetReadUtil {
    /**
     * 读取文件的内容返回
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getStringFromAssert(Context context, String fileName) {
        String content = "";
        try {
            // 把数据从文件读入内存
            InputStream is = context.getResources().getAssets().open(fileName);
            ByteArrayOutputStream bs = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int i = is.read(buffer, 0, buffer.length);
            while (i > 0) {
                bs.write(buffer, 0, i);
                i = is.read(buffer, 0, buffer.length);
            }

            content = new String(bs.toByteArray(), Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return content;
    }
}
