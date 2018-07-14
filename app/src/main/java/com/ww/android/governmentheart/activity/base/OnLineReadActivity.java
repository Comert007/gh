package com.ww.android.governmentheart.activity.base;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;

import butterknife.BindView;

/**
 * @Author feng
 * @Date 2018/7/14
 */
public class OnLineReadActivity extends BaseActivity<VoidView,VoidModel> {

    @BindView(R.id.progressBar)
    ProgressBar bar;
    @BindView(R.id.web_view)
    WebView webView;

    private String url;
    private String title;

    public static void start(Context context,String title,String url) {
        Intent intent = new Intent(context, OnLineReadActivity.class);
        intent.putExtra("url",url);
        intent.putExtra("title",title);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void init() {
        initData();
        initWeb();
    }

    private void  initData(){
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        setTitleText(title);
    }


    private void initWeb() {
        webView.loadUrl(TextUtils.isEmpty(url) ? "http://www.baidu.com/" : "https://view.officeapps.live.com/op/view.aspx?src="+url);
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

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}
