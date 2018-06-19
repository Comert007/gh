package com.ww.android.governmentheart.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.model.IModel;
import com.ww.android.governmentheart.mvp.presenter.PresenterActivity;
import com.ww.android.governmentheart.mvp.vu.IView;
import com.ww.android.governmentheart.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public abstract class BaseActivity<V extends IView, M extends IModel> extends
        PresenterActivity<V, M> {
    protected BaseApplication baseApp;

    @Nullable
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @Nullable
    @BindView(R.id.btn_title_right)
    public Button btnTitleRight;
    @Nullable
    @BindView(R.id.btn_title_left)
    public Button btnTitleLeft;

    private ImmersionBar mImmersionBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseApp = BaseApplication.getInstance();
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.fitsSystemWindows(true)  //使用该属性,必须指定状态栏颜色
                .statusBarColor(R.color.colorPrimary).init();
    }

    public void showToast(CharSequence text) {
        ToastUtils.showToast(text);
    }


    @Optional
    @OnClick({R.id.btn_title_left, R.id.btn_title_right})
    public void onTitleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_title_left:
                onTitleLeft();
                break;
            case R.id.btn_title_right:
                onTitleRight();
                break;
            default:
                break;
        }
    }

    public void onTitleLeft() {
    }

    public void onTitleRight() {

    }

    @Optional
    public void setTitleText(String titleText) {
        if (tvTitle != null) {
            tvTitle.setText(titleText);
        }
    }

    public void setTitleLeftText(String titleLeftText) {
        if (btnTitleLeft != null) {
            btnTitleLeft.setText(titleLeftText);
        }
    }

    public void setTitleRightText(String titleRightText) {
        if (btnTitleRight != null) {
            btnTitleRight.setText(titleRightText);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
        //必须调用该方法，防止内存泄漏，不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
    }
}