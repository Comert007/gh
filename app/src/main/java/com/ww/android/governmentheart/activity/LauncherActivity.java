package com.ww.android.governmentheart.activity;

import android.os.Handler;
import android.text.TextUtils;
import android.view.WindowManager;

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
    protected void beforeOnCreate() {
        super.beforeOnCreate();
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    protected void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String token = BaseApplication.getInstance().getToken();
                if (TextUtils.isEmpty(token)) {
                    LoginActivity.launch(LauncherActivity.this);
                    finish();
                }else {
                    MainActivity.start(LauncherActivity.this);
                    finish();
                }
            }
        }, 2000);
    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                .color_red).init();
    }

    @Override
    protected boolean isDefaultImmersionBar() {
        return false;
    }
}
