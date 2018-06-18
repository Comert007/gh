package com.ww.android.governmentheart.fragment.wisdom;

import android.support.annotation.NonNull;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.wisdom.TransmissionAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;

import java.util.Arrays;

/**
 * @Author feng
 * @Date 2018/6/18
 * 资料发送
 */
public class TransmissionFragment extends BaseFragment<RefreshView,VoidModel>{

    private TransmissionAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_transmission;
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
        v.initDefaultDecoration();
        adapter = new TransmissionAdapter(getContext());
        v.crv.setAdapter(adapter);

        adapter.addList(Arrays.asList("1","2","3","1","2","3","1","2","3"));


    }
}
