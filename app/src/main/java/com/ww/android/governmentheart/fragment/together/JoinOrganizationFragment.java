package com.ww.android.governmentheart.fragment.together;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;

import butterknife.BindView;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class JoinOrganizationFragment extends BaseFragment<RefreshView, VoidModel> {

    @BindView(R.id.progressBar)
    ProgressBar bar;
    @BindView(R.id.web_view)
    WebView webView;
    private String url;

    public static JoinOrganizationFragment newInstance(String url) {
        JoinOrganizationFragment fragment = new JoinOrganizationFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_join_organization;
    }

    @Override
    protected void init() {
        initData();
        initWeb();
    }

    private void initData() {
        url = getArguments().getString("url");
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
