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
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.bean.home.EasyRequestBean;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;

import butterknife.BindView;

/**
 * @author feng
 * @Date 2018/7/10.
 */
public class WebViewActivity extends BaseActivity<VoidView, VoidModel> {

    @BindView(R.id.progressBar)
    ProgressBar bar;
    @BindView(R.id.web_view)
    WebView webView;

    private String url;
    private String name;
    private EasyRequestBean mEasyRequestBean;


    public static void start(Context context, EasyRequestBean easyRequestBean) {
        Intent starter = new Intent(context, ContentDetailActivity.class);
        starter.putExtra("easyRequestBean", easyRequestBean);
        context.startActivity(starter);
    }


    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
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

    private void initData() {
        mEasyRequestBean = (EasyRequestBean) getIntent().getSerializableExtra("easyRequestBean");
        name = mEasyRequestBean.name;
        url = mEasyRequestBean.url;
        setTitleText(name);
    }

    private void initWeb() {
        webView.loadUrl(TextUtils.isEmpty(url) ? "http://www.baidu.com/" : url);
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
