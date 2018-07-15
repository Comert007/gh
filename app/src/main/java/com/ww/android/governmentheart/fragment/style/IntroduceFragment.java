package com.ww.android.governmentheart.fragment.style;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.config.Constant;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.WebKitView;

/**
 * @Author feng
 * @Date 2018/6/17
 */
public class IntroduceFragment extends BaseFragment<WebKitView,VoidModel> {


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_introduce;
    }

    public static IntroduceFragment newInstance(String code) {
        IntroduceFragment fragment = new IntroduceFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected void init() {
        v.webView.loadUrl(Constant.INTRODUCE_URL);
        v.webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                v.webView.loadUrl(url);
                return true;
            }
        });
    }
}
