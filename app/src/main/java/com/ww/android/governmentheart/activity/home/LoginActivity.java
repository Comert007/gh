package com.ww.android.governmentheart.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.activity.MainActivity;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.login.PassBean;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;
import com.ww.android.governmentheart.mvp.model.home.LoginModel;
import com.ww.android.governmentheart.mvp.vu.home.LoginView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.ToastUtils;

import java.util.List;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginView, LoginModel> {


    public static void launch(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.tv_gain_pass,R.id.btn_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_gain_pass:
                gainPass();
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void gainPass(){
        String phone = v.getPhone();
        if (TextUtils.isEmpty(phone)|| phone.length()<11){
            ToastUtils.showToast("请输入正确的手机号");
            return;
        }
        if (v.checkParams(phone)){
            m.initPass(phone, new BaseObserver<PassBean>(this,
                    bindToLifecycle()) {
                @Override
                protected void onSuccess(@Nullable PassBean passBean, @Nullable List<PassBean> list,
                                         @Nullable PageBean<PassBean> page) {


                }
            });
        }
    }

    private void login(){
        String phone = v.getPhone();
        String pass = v.getPass();
        if (TextUtils.isEmpty(phone)|| phone.length()<11){
            ToastUtils.showToast("请输入正确的手机号");
            return;
        }

        if (TextUtils.isEmpty(pass)){
            ToastUtils.showToast("请输入密码");
            return;
        }

        if (v.checkParams(phone,pass)){
            m.login(phone, pass, new BaseObserver<UserBean>(this,bindToLifecycle()) {
                @Override
                protected void onSuccess(@Nullable UserBean userBean, @Nullable List<UserBean> list, @Nullable PageBean<UserBean> page) {
                  if (userBean!= null && !TextUtils.isEmpty(userBean.getToken())){

                      BaseApplication.getInstance().saveUserInfo(userBean);
                      MainActivity.start(LoginActivity.this);
                  }
                }
            });
        }

    }

}
