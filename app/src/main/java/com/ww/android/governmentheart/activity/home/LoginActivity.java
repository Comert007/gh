package com.ww.android.governmentheart.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.login.PassBean;
import com.ww.android.governmentheart.mvp.model.home.LoginModel;
import com.ww.android.governmentheart.mvp.vu.VoidView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<VoidView, LoginModel> {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pass)
    EditText etPass;

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

    @OnClick({R.id.tv_gain_pass})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_gain_pass:
                gainPass();
                break;
        }
    }

    private void gainPass(){
        String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(phone) || phone.length()<11){
            ToastUtils.showToast("请输入正确的手机号");
            return;
        }
        m.initPass(phone, new BaseObserver<PassBean>(this,
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PassBean passBean, @Nullable List<PassBean> list,
                                     @Nullable PageBean<PassBean> page) {


            }
        });
    }
}
