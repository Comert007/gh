package com.ww.android.governmentheart.activity;

import android.os.Handler;
import android.text.TextUtils;

import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.home.LoginActivity;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class LauncherActivity extends BaseActivity<VoidView, VoidModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_launcher;
    }

    @Override
    protected void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String token = BaseApplication.getInstance().getToken();
                if (TextUtils.isEmpty(token)) {
                    LoginActivity.launch(LauncherActivity.this);
                }else {
                    MainActivity.start(LauncherActivity.this);
                }
            }
        }, 2000);
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                .color_blue).init();
    }

    @Override
    protected boolean isDefaultImmersionBar() {
        return false;
    }
}
