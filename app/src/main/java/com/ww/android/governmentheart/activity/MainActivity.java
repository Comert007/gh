package com.ww.android.governmentheart.activity;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.VoidView;

public class MainActivity extends BaseActivity<VoidView,VoidModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {

    }
}
