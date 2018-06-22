package com.ww.android.governmentheart.fragment;

import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.home.HomeAdapter;
import com.ww.android.governmentheart.mvp.bean.MultipleBean;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.Arrays;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class HeartFragment extends BaseFragment<RefreshView, VoidModel> {


    private HomeAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_heart;
    }

    @Override
    protected void init() {
        initListener();
        if (v.crv == null) {
            return;
        }
        initRecycler();

        adapter.addList(Arrays.asList(new MultipleBean(MultipleBean.MULTIPLE_HEADER),
                new MultipleBean(MultipleBean.MULTIPLE_BODY),
        new MultipleBean(MultipleBean.MULTIPLE_BODY)));
    }


    private void initListener(){
        if (v.srl == null) {
            return;
        }

        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                v.srl.finishRefresh(2000);
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                v.srl.finishLoadMore(2000);
            }
        });
    }

    private void initRecycler(){
        v.initRecycler(RecyclerHelper.defaultManager(getContext()),RecyclerHelper.defaultMoreDecoration(getContext()));

        adapter = new HomeAdapter(getContext());
        v.crv.setAdapter(adapter);
    }
}
