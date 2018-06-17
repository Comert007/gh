package com.ww.android.governmentheart.fragment.style;

import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.style.FarmAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.Arrays;

/**
 * @Author feng
 * @Date 2018/6/17
 */
public class FarmFragment extends BaseFragment<RefreshView,VoidModel> {

    private FarmAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_farm;
    }

    @Override
    protected void init() {
        initListener();
        initRecycler();
    }

    private void initListener() {
        if (v.srl != null) {
            return;
        }

        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                v.srl.finishRefresh();
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                v.srl.finishLoadMore();
            }
        });
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(getContext()),RecyclerHelper.defaultMoreDecoration(getContext()));
        adapter = new FarmAdapter(getContext());
        v.crv.setAdapter(adapter);
        adapter.addList(Arrays.asList("1","3","3","1","3","3","1","3","3","1","3","3"));


    }
}
