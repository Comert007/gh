package com.ww.android.governmentheart.activity.base;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.activity.heart.CommentsActivity;
import com.ww.android.governmentheart.config.ImmersionType;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.widget.dialog.EditTextDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author feng
 * @Date 2018/7/9.
 */
public class WebViewActivity extends BaseActivity<VoidView,VoidModel> {

    @BindView(R.id.progressBar)
    ProgressBar bar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;

    private String url;
    private String name;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_webview;
    }

    public static void launch(Context context,String name,String url) {
        Intent starter = new Intent(context, WebViewActivity.class);
        starter.putExtra("name",name);
        starter.putExtra("url",url);
        context.startActivity(starter);
    }


    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    @OnClick({R.id.ll_container,R.id.ll_comment})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.ll_container:
                EditTextDialog textDialog = new EditTextDialog();
                textDialog.show(getSupportFragmentManager());
                break;
            case R.id.ll_comment:
                CommentsActivity.start(this);
                break;
        }

    }

    @Override
    protected void init() {
        webView.loadUrl(TextUtils.isEmpty(url)?"http://www.baidu.com/":url);
        name = getIntent().getStringExtra("name");
        url = getIntent().getStringExtra("url");
        setTitleText(name);
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

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}
