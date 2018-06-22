package com.ww.android.governmentheart.mvp.vu;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.utils.RefreshType;

import butterknife.BindView;
import butterknife.ButterKnife;
import ww.com.core.Debug;
import ww.com.core.widget.CustomRecyclerView;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class RefreshView implements IView {

    private Activity activity;

    /**
     * RecyclerView 的列表展示模式
     */
    private RecyclerView.LayoutManager manager;
    /**
     * 分割线
     */
    private DividerItemDecoration decoration;

    @Nullable
    @BindView(R.id.srl)
    public SmartRefreshLayout srl;

    @Nullable
    @BindView(R.id.crv)
    public CustomRecyclerView crv;


    @Override
    public void onAttach(@NonNull Activity preActivity, @NonNull View contentView) {
        this.activity = preActivity;
        ButterKnife.bind(this, contentView);
    }

    /**
     * 设置刷新模式
     * @param refreshType RefreshType类型
     */
    public void setRefreshType(int refreshType) {
        if (srl == null) {
            Debug.e("the srl is null");
            return;
        }
        if (refreshType == RefreshType.ENABLE) {
            srl.setEnableRefresh(true);
            srl.setEnableLoadMore(true);
        } else if (refreshType == RefreshType.REFRESH) {
            srl.setEnableRefresh(true);
            srl.setEnableLoadMore(false);
        } else if (refreshType == RefreshType.LOAD_MORE) {
            srl.setEnableRefresh(false);
            srl.setEnableLoadMore(true);
        } else {
            srl.setEnablePureScrollMode(true);
        }
    }

    public void initRecycler(@NonNull RecyclerView.LayoutManager manager, @NonNull
            DividerItemDecoration
            decoration) {
        if (crv != null) {
            crv.setLayoutManager(manager);
            crv.addItemDecoration(decoration);
        }
    }

    public void initDefaultRecycler(boolean isDecoration) {
        initDefaultManager();
        if (isDecoration){
            initDefaultDecoration();
        }
    }

    public void initDefaultManager() {
        if (crv != null) {
            if (manager == null) {
                manager = new LinearLayoutManager(activity);
            }
            crv.setLayoutManager(manager);
        }
    }

    public void initDefaultDecoration() {
        if (crv != null) {
            if (decoration == null) {
                decoration = new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL);
            }
            crv.addItemDecoration(decoration);
        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}
