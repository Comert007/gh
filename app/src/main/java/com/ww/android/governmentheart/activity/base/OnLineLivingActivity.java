package com.ww.android.governmentheart.activity.base;

import android.content.Context;
import android.content.Intent;

import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;

import butterknife.BindView;

/**
 * @author feng
 * @Date 2018/7/16.
 */
public class OnLineLivingActivity extends BaseActivity<VoidView,VoidModel> {

    @BindView(R.id.web_view)
    WebView mWebView;

    private String url;

    public static void start(Context context,String url) {
        Intent intent = new Intent(context, OnLineLivingActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_online_living;
    }

    @Override
    protected void init() {
        url =getIntent().getStringExtra("url");
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                return super.shouldOverrideUrlLoading(webView, s);
            }
        });
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }
}
