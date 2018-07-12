package com.ww.android.governmentheart.activity.base;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.activity.heart.CommentsActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.home.EasyRequestBean;
import com.ww.android.governmentheart.mvp.model.CommonModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.widget.dialog.EditDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author feng
 * @Date 2018/7/9.
 */
public class ContentDetailActivity extends BaseActivity<VoidView, CommonModel> {

    @BindView(R.id.progressBar)
    ProgressBar bar;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;

    private EditDialog mEditDialog;
    private EasyRequestBean mEasyRequestBean;
    private String url;
    private String name;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_content_detail;
    }

    public static void start(Context context, EasyRequestBean easyRequestBean) {
        Intent starter = new Intent(context, ContentDetailActivity.class);
        starter.putExtra("easyRequestBean", easyRequestBean);
        context.startActivity(starter);
    }


    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    @OnClick({R.id.ll_container, R.id.ll_comment})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_container:
                llContainer.clearFocus();
                mEditDialog = new EditDialog(this, 7)
                        .setParms(tvContent, "在这里输入您的观点（最少5个字）");
                mEditDialog.setNums(false, 200);
                mEditDialog.setEdiClickInterface(new EditDialog.EditDialogClickInterface() {
                    @Override
                    public void doConfirm(String str) {
                        saveComment(str);
                    }
                });
                mEditDialog.show();
                break;
            case R.id.ll_comment:
                CommentsActivity.start(this, mEasyRequestBean);
                break;
        }

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
        tvCommentNum.setText(String.format("%d", mEasyRequestBean.num));
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

    /**
     * 保存评论
     * @param content
     */
    private void saveComment(String content) {
        Map map = new HashMap();
        map.put("id", mEasyRequestBean.id);
        map.put("cont", content);
        map.put("t", mEasyRequestBean.type);
        //需确认这个评论 open id .
//        map.put("openid", content);
        m.saveComment(map, new BaseObserver<String>(this, bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable String s, @Nullable List<String> list, @Nullable
                    PageBean<String> page) {
                ResponseBean<String> responseBean = getResponseBean();
                showToast(TextUtils.isEmpty(responseBean.getMsg())?"评论成功":responseBean.getMsg());
                tvCommentNum.setText((mEasyRequestBean.num+1)+"");
            }
        });
    }
}
