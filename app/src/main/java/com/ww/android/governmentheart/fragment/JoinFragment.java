package com.ww.android.governmentheart.fragment;

import android.support.annotation.NonNull;
import android.widget.Button;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.join.JoinAdapter;
import com.ww.android.governmentheart.mvp.bean.MultipleBean;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.Arrays;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class JoinFragment extends BaseFragment<RefreshView,VoidModel> {
    @BindView(R.id.btn_title_left)
    Button btnTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindColor(R.color.color_black)
    int colorBlack;

    private JoinAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_join;
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

//        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                v.srl.finishLoadMore();
//            }
//        });
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(getContext()),RecyclerHelper.defaultSingleDecoration(getContext()));

        adapter = new JoinAdapter(getContext());
        v.crv.setAdapter(adapter);

        adapter.addList(Arrays.asList(new MultipleBean(MultipleBean.MULTIPLE_HEADER),
                new MultipleBean(MultipleBean.MULTIPLE_BODY),
                new MultipleBean(MultipleBean.MULTIPLE_BODY),
                new MultipleBean(MultipleBean.MULTIPLE_BODY),
                new MultipleBean(MultipleBean.MULTIPLE_BODY),
                new MultipleBean(MultipleBean.MULTIPLE_BODY)));


    }
}
