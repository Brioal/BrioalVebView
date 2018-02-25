package com.brioal.brioalvebview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.brioal.weblib.BrioalWebView;

public class MainActivity extends AppCompatActivity {

    private BrioalWebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = findViewById(R.id.main_webView);
//        mWebView.loadUrl("file:///android_asset/strapdown/index.html");
        mWebView.showPageFromMD(this,"test.md");
        mWebView.setLoadDrawable(getResources().getDrawable(R.drawable.bg_grapriacc));
//        mWebView.showPageByBodyElement("标题","<div style=\"background:#fafaff; min-height:50px; padding:0 5px; margin:0\">\n" +
//                "  <div style=\"float:left;\">\n" +
//                "    <img style=\"width:40px; margin:0; padding-top:5px; margin-right:10px;\" src=\"https://g.twimg.com/Twitter_logo_blue.png\"/>\n" +
//                "  </div>\n" +
//                "  <div style=\"padding-top:15px; padding-bottom:10px;\">\n" +
//                "    Follow the author <a href=\"http://twitter.com/r2r\">@r2r</a> on Twitter\n" +
//                "  </div>\n" +
//                "</div>");
    }
}
