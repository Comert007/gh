package com.ww.android.governmentheart.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.home.HomeAdapter;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;
import com.ww.android.governmentheart.mvp.model.CommonModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ww.com.core.utils.TimeUtils;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class HomeFragment extends BaseFragment<RefreshView, CommonModel> {

    private int code =0;
    private int page;
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

    private void news(){
        Map map = new HashMap();
        map.put("code",code);
        map.put("pageNo",page);
        if (page == 0){
            map.put("date", TimeUtils.date2String(new Date()));
        }
        if (v.srl == null){
            return;
        }
        m.news(map, new BaseObserver<List<NewsBean>>(getContext(),bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable List<NewsBean> newsBeans, @Nullable
                    List<List<NewsBean>> list, @Nullable PageBean<List<NewsBean>> pageBean) {
                if (page == 0){
                    v.srl.finishRefresh();
                    if (newsBeans!=null && newsBeans.size()>0){
                        adapter.addList(newsBeans);
                        page++;
                    }else {
                        v.srl.setNoMoreData(false);
                    }
                }else {
                    v.srl.finishLoadMore();
                    if (newsBeans==null || newsBeans.size() == 0){
                        v.srl.setNoMoreData(false);
                    }else {
                        adapter.appendList(newsBeans);
                        v.srl.setNoMoreData(true);
                        page++;
                    }
                }
            }
        });
    }
}
