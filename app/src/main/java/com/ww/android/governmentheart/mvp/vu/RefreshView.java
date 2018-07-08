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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ww.com.core.Debug;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.widget.CustomRecyclerView;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class RefreshView implements IView {

    public Activity activity;

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
     *
     * @param refreshType RefreshType类型
     */
    public void setRefreshType(int refreshType) {
        if (srl == null) {
            Debug.e("the srl is null");
            return;
        }

        switch (refreshType) {
            case RefreshType.ENABLE:
                srl.setEnableRefresh(true);
                srl.setEnableLoadMore(true);
                break;
            case RefreshType.REFRESH:
                srl.setEnableRefresh(true);
                srl.setEnableLoadMore(false);
                break;
            case RefreshType.LOAD_MORE:
                srl.setEnableRefresh(false);
                srl.setEnableLoadMore(true);
                break;
            case RefreshType.NOT_ENABLE:
                srl.setEnableRefresh(false);
                srl.setEnableLoadMore(false);
                break;
        }
    }

    public void initRecycler(@NonNull RecyclerView.LayoutManager manager) {
        if (crv != null) {
            crv.setLayoutManager(manager);
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
        if (isDecoration) {
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

    public int createSuccess(int page, List list, RvAdapter adapter) {
        if (page == 0) {
            srl.finishRefresh();
            if (list != null && list.size() > 0) {
                adapter.addList(list);
                page++;
            } else {
                srl.setNoMoreData(false);
            }
        } else {
            srl.finishLoadMore();
            if (list == null || list.size() == 0) {
                srl.setNoMoreData(false);
            } else {
                adapter.appendList(list);
                srl.setNoMoreData(true);
                page++;
            }
        }
        return page;
    }

    public void onRefresh(int page, List list, RvAdapter adapter) {
    }

    public void onLoadMore() {

    }
}
