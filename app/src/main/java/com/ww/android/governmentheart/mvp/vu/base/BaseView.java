package com.ww.android.governmentheart.mvp.vu.base;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import com.ww.android.governmentheart.mvp.vu.IView;

import butterknife.ButterKnife;

public abstract class BaseView implements IView {

    private Activity mActivity;
    private View contentView;

    @Override
    public void onAttach(@NonNull Activity preActivity, @NonNull View contentView) {
        this.mActivity = mActivity;
        this.contentView = contentView;
        ButterKnife.bind(this,contentView);
        onAttach();
    }

    public abstract void onAttach();

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
