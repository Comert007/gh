package com.ww.android.governmentheart.activity.wisdom;

import android.content.Context;
import android.content.Intent;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;

import butterknife.OnClick;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class CommitSuccessActivity extends BaseActivity<VoidView, VoidModel> {

    public static void start(Context context) {
        Intent intent = new Intent(context, CommitSuccessActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_commit_success;
    }

    @Override
    protected void init() {

    }


    @OnClick(R.id.btn_back)
    public void onClick(){
        finish();
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }
}
