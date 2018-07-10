package com.ww.android.governmentheart.activity.home;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;

public class CommitSuccessActivity extends BaseActivity<VoidView,VoidModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_pass_commit_success;
    }

    @Override
    protected void init() {

    }



    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }
}
