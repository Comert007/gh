package com.ww.android.governmentheart.mvp.presenter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.ww.android.governmentheart.mvp.WWApplication;
import com.ww.android.governmentheart.mvp.manager.IUserInfo;
import com.ww.android.governmentheart.mvp.manager.IUserInfoManager;
import com.ww.android.governmentheart.mvp.model.IModel;
import com.ww.android.governmentheart.mvp.vu.IView;

import butterknife.ButterKnife;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public abstract class PresenterActivity<V extends IView, M extends IModel> extends
        RxAppCompatActivity implements IPresenter, IUserInfoManager {

    WWApplication wwApp;

    protected V v;
    protected M m;

    public PresenterActivity() {
        PresenterHelper.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wwApp = (WWApplication) getApplication();
        wwApp.addRunActivity(this);

        try {
            setContentView(getLayoutResId());
        }catch (Exception e){
            e.printStackTrace();
        }

        View contentView = findViewById(android.R.id.content);
        v.onAttach(this, contentView);
        m.onAttach(this);

        ButterKnife.bind(this);


    }

    /**
     * 获取 layout 资源id
     *
     * @return
     */
    protected abstract int getLayoutResId();

    /**
     */
    protected abstract void init();

    @Override
    protected void onResume() {
        super.onResume();
        v.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        v.onDestroy();
        wwApp.removeRunActivity(this);
    }

    @Override
    public Activity getPresenterActivity() {
        return this;
    }

    @Override
    public M getModelModule() {
        return m;
    }

    @Override
    public void setModelModule(IModel modelModule) {
        this.m = (M) modelModule;
    }

    @Override
    public V getViewModule() {
        return v;
    }

    @Override
    public void setViewModule(IView viewModule) {
        this.v = (V) viewModule;
    }

    @Override
    public void clearUserInfo() {
        wwApp.clearUserInfo();
    }

    @Override
    public void saveUserInfo(IUserInfo userBean) {
        wwApp.saveUserInfo(userBean);
    }

    @Override
    public IUserInfo getUserInfo() {
        return wwApp.getUserInfo();
    }

    @Override
    public boolean isLogin() {
        return wwApp.isLogin();
    }

    @Override
    public String getToken() {
        return wwApp.getToken();
    }

    @Override
    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftKeyBoard() {
        View v = getCurrentFocus();
        if (v == null){
            return;
        }
        try {
            InputMethodManager imm = (InputMethodManager) this
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
