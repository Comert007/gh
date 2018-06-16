package com.ww.android.governmentheart.fragment;

import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
public class HeartFragment extends BaseFragment<RefreshView, VoidModel> {


    private HomeAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void init() {
        if (v.srl == null) {
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

        if (v.crv == null) {
            Debug.d("the rv is null~");
            return;
        }
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        v.crv.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.shape_divider_more));
        v.crv.addItemDecoration(decoration);

        adapter = new HomeAdapter(getContext());
        v.crv.setAdapter(adapter);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_heart_header, null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        v.crv.addHeadView(view);


        adapter.addList(Arrays.asList("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
                "1", "1", "1", "1", "1", "1", "1"));
    }
}
