package com.ww.android.governmentheart.mvp.vu;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.ww.android.governmentheart.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author feng
 * @Date 2018/6/17
 */
public class WebKitView implements IView {
    @BindView(R.id.web_view)
    public WebView webView;


    private Activity activity;

    @Override
    public void onAttach(@NonNull Activity preActivity, @NonNull View contentView) {
        this.activity = preActivity;
        ButterKnife.bind(this,contentView);
        initWebKit();
    }

    private void initWebKit(){
        WebSettings webSettings = webView.getSettings();
        //5.0以上开启混合模式加载
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        //允许js代码
        webSettings.setJavaScriptEnabled(true);
        //允许SessionStorage/LocalStorage存储
        webSettings.setDomStorageEnabled(true);
        //禁用放缩
        webSettings.setDisplayZoomControls(false);
        webSettings.setBuiltInZoomControls(false);
        //禁用文字缩放
        webSettings.setTextZoom(100);
        //10M缓存，api 18后，系统自动管理。
        webSettings.setAppCacheMaxSize(10 * 1024 * 1024);
        //允许缓存，设置缓存位置
        webSettings.setAppCacheEnabled(true);
        webSettings.setAppCachePath(activity.getDir("appcache", 0).getPath());
        //允许WebView使用File协议
        webSettings.setAllowFileAccess(true);
        //不保存密码
        webSettings.setSavePassword(false);
        //自动加载图片
        webSettings.setLoadsImagesAutomatically(true);

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
