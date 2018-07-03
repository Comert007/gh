package com.ww.android.governmentheart.activity.home;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.VoidView;

public class LoginActivity extends BaseActivity<VoidView,VoidModel>{

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {

    }
}
