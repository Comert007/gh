package com.ww.android.governmentheart.mvp.vu;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ww.android.governmentheart.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class RefreshView implements IView {

    @Nullable
    @BindView(R.id.srl)
    public SmartRefreshLayout srl;

    @Nullable
    @BindView(R.id.rv)
    public RecyclerView rv;


    @Override
    public void onAttach(@NonNull Activity preActivity, @NonNull View contentView) {
        ButterKnife.bind(this,contentView);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
