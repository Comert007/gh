package com.ww.android.governmentheart.mvp.vu.home;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.vu.base.BaseView;

import butterknife.BindView;

public class LoginView extends BaseView {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pass)
    EditText etPass;
    @BindView(R.id.tv_forget_pass)
    TextView tvForgetPass;

    @Override
    public void onAttach() {

    }

    public String getPhone() {
        return etPhone.getText().toString();
    }

    public String getPass() {
        return etPass.getText().toString();
    }

    public boolean checkParams(String... strings){
        boolean isSuccess = true;
        for (String string : strings) {
            if (TextUtils.isEmpty(string)){
                isSuccess = false;
            }
        }
        return isSuccess;
    }
}
