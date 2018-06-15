package com.brioal.brioalvebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.brioal.weblib.BrioalWebView;
import com.brioal.weblib.OnWebViewClickListener;

public class MainActivity extends AppCompatActivity {

    private BrioalWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.main_webView);
        final String content =
                "![ic_launcher.png](http://192.168.1.105:8083/file/1529039452328.png)\n" +
                        "[MarkdownEditors-master.zip](http://192.168.1.105:8083/file/1529039458875.zip)";
        mWebView.showPageFromMD("标题", content);
        mWebView.setLoadDrawable(getResources().getDrawable(R.drawable.bg_grapriacc));
        mWebView.setWebViewClickListener(new OnWebViewClickListener() {
            @Override
            public void onLoad(String url) {
                System.out.println(url);
            }

            @Override
            public void onClickPic(String url) {
                System.out.println(url);
            }
        });
    }
}
