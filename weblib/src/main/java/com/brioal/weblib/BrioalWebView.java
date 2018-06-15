package com.brioal.weblib;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * email:brioal@foxmail.com
 * github:https://github.com/Brioal
 * Created by brioa on 2018/2/25.
 */
public class BrioalWebView extends RelativeLayout {
    private Context mContext;
    private WebView mWebView;
    private Drawable mLoadDrawable;//加载时候的Drawable
    private FlexLoadingView mLoadingView;
    private RelativeLayout mLoadingLayout;

    public BrioalWebView(Context context) {
        this(context, null);
    }

    public BrioalWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public void setLoadDrawable(Drawable loadDrawable) {
        mLoadDrawable = loadDrawable;
        mLoadingView.setBackSrc(loadDrawable);
    }

    /**
     * WebView初始化
     */
    private void init() {
        mWebView = new WebView(mContext);
        mLoadingView = new FlexLoadingView(mContext);
        mLoadingLayout = new RelativeLayout(mContext);

        mLoadingView.setBackSrc(mContext.getResources().getDrawable(R.drawable.bg_loading)).setLinesCount(5);

        LayoutParams webViewParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        addView(mWebView, webViewParams);

        LayoutParams loadingParams = new LayoutParams(SizeUtil.DpToPx(mContext, 100), SizeUtil.DpToPx(mContext, 100));
        loadingParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        mLoadingLayout.setBackgroundResource(R.drawable.bg_loading);
        mLoadingLayout.addView(mLoadingView, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        addView(mLoadingLayout, loadingParams);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                showLoading();
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                hideLoading();
                System.out.println("6:" + (System.currentTimeMillis() - mCurrentTime));
                mCurrentTime = System.currentTimeMillis();
                isLoadDone = true;
                super.onPageFinished(view, url);

            }
        });
        setBackgroundColor(Color.WHITE);
    }

    private boolean isLoadDone = false;

    /**
     * 显示正在加载
     */
    private void showLoading() {
        //默认隐藏ViewView+显示加载动画
        if (!isLoadDone) {
            mWebView.setVisibility(GONE);
        }
        mLoadingLayout.setVisibility(VISIBLE);
    }

    private void hideLoading() {
        mWebView.setVisibility(VISIBLE);
        mLoadingLayout.setVisibility(GONE);
    }

    /**
     * 加载网页列表
     *
     * @param url
     */
    public void loadUrl(String url) {
        showLoading();
        mWebView.loadUrl(url);
    }

    /**
     * 从Context 和 MD文件名称来显示MD
     *
     * @param context
     * @param mdName
     */
    public void showPageFromMD(Context context, String mdName) {
        //读取MD的内容
        String htmlContent = AssetReadUtil.getStringFromAssert(context, mdName);
        showPageFromMD("", htmlContent);
    }

    private long mCurrentTime = 0;

    /**
     * 传入MD的字符串,然后显示
     *
     * @param content
     */
    public void showPageFromMD(String title, String content) {
        mCurrentTime = System.currentTimeMillis();
        showLoading();
        System.out.println("1:" + (System.currentTimeMillis() - mCurrentTime));
        mCurrentTime = System.currentTimeMillis();
        //读取网页的内容
        String htmlContent = AssetReadUtil.getStringFromAssert(mContext, "index.html");
        System.out.println("2:" + (System.currentTimeMillis() - mCurrentTime));
        mCurrentTime = System.currentTimeMillis();
        //是否显示标题
        if (StringUtil.isAvailable(title)) {
            //显示标题
            String titleHtml = "<head>\n  <title>" + title + "\n</title></head>";
            //替换标题
            htmlContent = htmlContent.replace("$title$", titleHtml);
        } else {
            htmlContent = htmlContent.replace("$title$", "");
        }
        System.out.println("3:" + (System.currentTimeMillis() - mCurrentTime));
        mCurrentTime = System.currentTimeMillis();
        //是否显示i内容
        if (StringUtil.isAvailable(content)) {
            //替换内容
            htmlContent = htmlContent.replace("$content$", content);//替换内容
        } else {
            htmlContent = htmlContent.replace("$content$", "");//替换内容
        }
        System.out.println("4:" + (System.currentTimeMillis() - mCurrentTime));
        mCurrentTime = System.currentTimeMillis();
        //显示内容
        mWebView.loadDataWithBaseURL("file:///android_asset/", htmlContent, "text/html", "utf-8", null);
    }

    /**
     * 传入Context 和asset内网页的名称
     *
     * @param context
     * @param name
     */
    public void showPageAll(Context context, String name) {
        //读取网页内容
        String content = AssetReadUtil.getStringFromAssert(context, name);
        //加载内容
        mWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    }

    /**
     * 传入网页内容,然后显示
     *
     * @param content
     */
    public void showPageByAll(String content) {
        //加载内容
        mWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    }

    /**
     * 直接显示body的内容到Html
     *
     * @param title
     * @param body
     */
    public void showPageByBodyElement(String title, String body) {
        showLoading();
        //读取网页内容
        String content = AssetReadUtil.getStringFromAssert(mContext, "fitPhonePage.html");
        //显示标题
        if (StringUtil.isAvailable(title)) {
            //替换title
            content = content.replace("$title$", title);//替换大标题
        } else {
            content = content.replace("$title$", "");//替换大标题
        }
        //替换内容
        if (StringUtil.isAvailable(body)) {
            content = content.replace("$content$", body);//替换内容
        } else {
            content = content.replace("$content$", "");//替换内容
        }
        //webView设置
        //加载内容
        mWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    }


}
