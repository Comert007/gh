package com.ww.android.governmentheart.fragment.together;

import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.together.ActivityAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.Arrays;

/**
 * @Author feng
 * @Date 2018/6/16
 * 活动信息
 */
public class ActivityFragment extends BaseFragment<RefreshView,VoidModel> {

    private ActivityAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void init() {
        initListener();
        initRecycler();
    }

    private void initListener(){
        if (v.srl!=null){
            return;
        }

        v.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                v.srl.finishRefresh();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                v.srl.finishLoadMore();
            }
        });
    }

    private void initRecycler(){
        v.initRecycler(RecyclerHelper.defaultManager(getContext()),RecyclerHelper.defaultMoreDecoration(getContext()));
        adapter = new ActivityAdapter(getContext());
        v.crv.setAdapter(adapter);

        adapter.addList(Arrays.asList("1","2","3"));
    }
}
