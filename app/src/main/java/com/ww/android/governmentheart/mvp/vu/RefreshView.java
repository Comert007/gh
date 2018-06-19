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

import butterknife.BindView;
import butterknife.ButterKnife;
import ww.com.core.widget.CustomRecyclerView;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class RefreshView implements IView {

    private Activity activity;

    /**
     * recyclerview 的列表展示模式
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


    public void initRecycler(@NonNull RecyclerView.LayoutManager manager, @NonNull DividerItemDecoration
            decoration) {
        if (crv != null) {
            crv.setLayoutManager(manager);
            crv.addItemDecoration(decoration);
        }
    }

    public void initDefaultRecycler(boolean isDecoration) {
        if (manager == null&& crv != null) {
            manager = new LinearLayoutManager(activity);
            crv.setLayoutManager(manager);
        }
        if (isDecoration && crv != null) {
            if (decoration == null) {
                decoration = new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL);
            }
            crv.addItemDecoration(decoration);
        }
    }

    public void initDefaultManager(){
        if (crv != null) {
            if (manager == null){
                manager = new LinearLayoutManager(activity);
            }
            crv.setLayoutManager(manager);
        }
    }

    public void initDefaultDecoration(){
        if (crv != null) {
            if (decoration == null) {
                decoration = new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL);
            }
            crv.addItemDecoration(decoration);
        }
    }

    /**
     * setManager 和 initDefaultManager 不可同时使用
     * @param manager
     */
    public void setManager(RecyclerView.LayoutManager manager) {
        this.manager = manager;
        initDefaultManager();
    }

    /**
     * setDecoration 和 initDefaultDecoration 不可同时使用
     * @param decoration
     */
    public void setDecoration(DividerItemDecoration decoration) {
        this.decoration = decoration;
        initDefaultDecoration();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }
}