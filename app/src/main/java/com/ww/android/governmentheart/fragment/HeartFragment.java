package com.ww.android.governmentheart.fragment;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.HomeAdapter;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;

import java.util.Arrays;

import ww.com.core.Debug;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class HeartFragment extends BaseFragment<RefreshView,VoidModel> {


    private HomeAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        if (v.srl==null){
            Debug.d("the srl is null~");
            return;
        }

        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                adapter.addItem("1");
                v.srl.finishRefresh(2000);
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                adapter.addItem("1");
                v.srl.finishLoadMore(2000);
            }
        });

        if (v.rv==null){
            Debug.d("the rv is null~");
            return;
        }
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        v.rv.setLayoutManager(manager);

        adapter= new HomeAdapter(getContext());
        v.rv.setAdapter(adapter);

        adapter.addList(Arrays.asList("1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1","1"));
    }
}
