package com.ww.android.governmentheart.fragment.style;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.WebKitView;

/**
 * @Author feng
 * @Date 2018/6/17
 */
public class IntroduceFragment extends BaseFragment<WebKitView,VoidModel> {

    private String url="https://mbd.baidu.com/newspage/data/landingsuper?context=%7B%22nid%22%3A%22news_9318814606734472450%22%7D&n_type=0&p_from=4";

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
    protected void init() {
        v.webView.loadUrl(url);
        v.webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                v.webView.loadUrl(url);
                return true;
            }
        });
    }
}
