package com.ww.android.governmentheart.activity.base;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;

/**
 * @author feng
 * @Date 2018/7/30.
 */
public class UserActivity extends BaseActivity<VoidView,VoidModel>{

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_user;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.BLUE;
    }
}
