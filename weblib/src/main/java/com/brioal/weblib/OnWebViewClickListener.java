package com.brioal.weblib;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/6/15.
 */
public interface OnWebViewClickListener {
    // 要跳转链接的时候
    void onLoad(String url);

    // 长按的时候
    void onClickPic(String url);
}
