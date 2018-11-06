package com.ww.android.governmentheart.activity.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.ww.android.governmentheart.BuildConfig;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;

import butterknife.BindView;

public class PortraitActivity extends BaseActivity<VoidView,VoidModel>{
    @BindView(R.id.progressBar)
    ProgressBar bar;
    @BindView(R.id.web_view)
    WebView webView;

    private String url;

    public static void start(Context context) {
        Intent starter = new Intent(context, PortraitActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_portrait;
    }

    @Override
    protected void init() {
        url = String.format("%s%s", BuildConfig.BASE_URL,"biDataMain");
//        url = "file:///android_asset/h5/数据总览.html";
        initSetting();
        initWeb();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initSetting(){
        WebSettings settings = webView.getSettings();
        settings.setDomStorageEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
    }

    private void initWeb() {
        webView.loadUrl(url);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.INVISIBLE);
                } else {
                    if (View.INVISIBLE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });

    }
}
