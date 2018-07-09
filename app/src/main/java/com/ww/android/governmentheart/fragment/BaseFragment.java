package com.ww.android.governmentheart.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.model.IModel;
import com.ww.android.governmentheart.mvp.presenter.PresenterFragment;
import com.ww.android.governmentheart.mvp.vu.IView;
import com.ww.android.governmentheart.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public abstract class BaseFragment<V extends IView,M extends IModel> extends PresenterFragment<V,M> {

    @Nullable
    @BindView(R.id.tv_title)
    TextView tvTitle;

    protected ImmersionBar mImmersionBar;

    /**
     * 是否对用户可见
     */
    protected boolean mIsVisible;
    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsPrepare;
    /**
     * 是否加载完成
     * 当执行完onViewCreated方法后即为true
     */
    protected boolean mIsImmersion;



    public void showToast(CharSequence text) {
        ToastUtils.showToast(text);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isLazyLoad()) {
            mIsPrepare = true;
            mIsImmersion = true;
            onLazyLoad();
        } else {
            init();
            if (isImmersionBarEnabled())
                initImmersionBar();
        }
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
        }
    }


    /**
     * 是否懒加载
     *
     * @return the boolean
     */
    protected boolean isLazyLoad() {
        return false;
    }

    @Optional
    public void onTitleLeft() {

    }

    @Optional
    public void onTitleRight() {

    }

    @Optional
    public void setTitleText(String titleText){
        if (tvTitle!=null){
            tvTitle.setText(titleText);
        }
    }

    /**
     * 是否在Fragment使用沉浸式
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return false;
    }

    /**
     * 初始化沉浸式
     */
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.keyboardEnable(true).navigationBarWithKitkatEnable(false).init();
    }

    /**
     * 用户可见时执行的操作
     */
    protected void onVisible() {
        onLazyLoad();
    }

    /**
     * 用户不可见执行
     */
    protected void onInvisible() {

    }

    private void onLazyLoad() {
        if (mIsVisible && mIsPrepare) {
            mIsPrepare = false;
            init();
        }
        if (mIsVisible && mIsImmersion && isImmersionBarEnabled()) {
            initImmersionBar();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null)
            mImmersionBar.destroy();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            mIsVisible = true;
            onVisible();
        } else {
            mIsVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null)
            mImmersionBar.init();
    }

}
