package com.ww.android.governmentheart.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.login.PassBean;
import com.ww.android.governmentheart.mvp.model.home.LoginModel;
import com.ww.android.governmentheart.mvp.vu.VoidView;
import com.ww.android.governmentheart.network.BaseObserver;

import java.util.List;

public class LoginActivity extends BaseActivity<VoidView, LoginModel> {

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
        m.initPass("18200131081", new BaseObserver<PassBean>(this,
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PassBean passBean, @Nullable List<PassBean> list,
                                     @Nullable PageBean<PassBean> page) {

            }
        });
    }
}
