package com.ww.android.governmentheart.activity.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ww.android.governmentheart.BuildConfig;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.home.EasyRequestBean;
import com.ww.android.governmentheart.mvp.model.work.WorkModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.network.BaseObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import ww.com.core.Debug;

/**
 * @author feng
 * @Date 2018/7/10.
 */
public class WebViewActivity extends BaseActivity<VoidView, WorkModel> {

    @BindView(R.id.progressBar)
    ProgressBar bar;
    @BindView(R.id.web_view)
    WebView webView;

    private String url;
    private String name;
    private String type;
    private EasyRequestBean mEasyRequestBean;


    public static void start(Context context, EasyRequestBean easyRequestBean) {
        Intent starter = new Intent(context, WebViewActivity.class);
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
        setTitleText(name);
        parseUrl();
        readMsg();
    }

    private void parseUrl(){
        type = mEasyRequestBean.type;
        Debug.d("type:"+type);
        if (isNumeric(type)){
            url = String.format("%s%s?args={\"id\":\"%s\"}", BuildConfig.BASE_URL,"getNewById",mEasyRequestBean.id);
        }else if (TextUtils.equals("NOTICE",type)){
            url = String.format("%s%s?args={\"id\":\"%s\"}", BuildConfig.BASE_URL,"notifyDetail",mEasyRequestBean.id);
        }else if (TextUtils.equals("MAT",type)){
            url = String.format("%s%s?args={\"id\":\"%s\"}", BuildConfig.BASE_URL,"materialDetail",mEasyRequestBean.id);
        }else if (TextUtils.equals("PROPOSAL",type)){
            url = String.format("%s%s?args={\"id\":\"%s\"}", BuildConfig.BASE_URL,"suggestDetail",mEasyRequestBean.id);
        }else if (TextUtils.equals("QUESTION",type)){
            url = String.format("%s%s?args={\"id\":\"%s\"}", BuildConfig.BASE_URL,"getQuestionById",mEasyRequestBean.id);
        }
        Debug.d("url:"+url);
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


    private void readMsg(){
        Map map = new HashMap();
        map.put("id",mEasyRequestBean.id);
        m.readMsg(map, new BaseObserver<String>(this,bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable String s, @Nullable List<String> list, @Nullable PageBean<String> page) {

            }

            @Override
            protected void onResponse(ResponseBean<String> responseBean) {
                super.onResponse(responseBean);
            }

            @Override
            protected boolean isIntercept() {
                return true;
            }
        });
    }

    /**
     * 是否是数字
     * @param str
     * @return
     */
    private boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}
