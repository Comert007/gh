package com.ww.android.governmentheart.fragment;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.model.IModel;
import com.ww.android.governmentheart.mvp.presenter.PresenterFragment;
import com.ww.android.governmentheart.mvp.vu.IView;
import com.ww.android.governmentheart.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public abstract class BaseFragment<V extends IView,M extends IModel> extends PresenterFragment<V,M> {

    @Nullable
    @BindView(R.id.tv_title)
    TextView tvTitle;

    public void showToast(CharSequence text) {
        ToastUtils.showToast(text);
    }

    @Optional
    @OnClick({R.id.btn_title_left, R.id.btn_title_right})
    public void onTitleClick(View v) {
        switch (v.getId()) {
            case R.id.btn_title_left:
                onTitleLeft();
                break;
            case R.id.btn_title_right:
                onTitleRight();
                break;
        }
    }

    @Optional
    public void onTitleLeft() {

    }

    @Optional
    public void onTitleRight() {

    }

    @Optional
    public void setTitleText(String titleText){
        if (tvTitle!=null){
            tvTitle.setText(titleText);
        }
    }
}
