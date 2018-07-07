package com.ww.android.governmentheart.fragment.heart;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.heart.HeartAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
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

public class HeartCoreFragment extends BaseFragment<RefreshView,CommonModel> {

    private int code; // code:1 方针，2 统战知识，3 权威解读， 4 政策库 5 崇州特色 6 农副产品 7 电商介绍 8 人物访谈 9加入我（加入组织）
    private int page;
    private HeartAdapter adapter;

    public static HeartCoreFragment newInstance(int code){
        HeartCoreFragment heartCoreFragment = new HeartCoreFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("code",code);
        heartCoreFragment.setArguments(bundle);
        return heartCoreFragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_heart_core;
    }

    @Override
    protected void init() {
        initData();
        initListener();
        if (v.crv == null) {
            return;
        }
        initRecycler();
    }

    private void initData(){
        code = getArguments().getInt("code",0);
    }

    private void initListener(){
        if (v.srl == null) {
            return;
        }

        v.srl.setOnRefreshListener(refreshLayout -> {
            page =0;
            news();
        });

        v.srl.setOnLoadMoreListener(refreshLayout -> news());
    }

    private void initRecycler(){
        v.initRecycler(RecyclerHelper.defaultManager(getContext()),RecyclerHelper.defaultMoreDecoration(getContext()));

        adapter = new HeartAdapter(getContext());
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
                    adapter.addList(newsBeans);
                    v.srl.finishRefresh();
                    page++;
                }else {
                    if (newsBeans==null || newsBeans.size() == 0){
                        v.srl.setNoMoreData(false);
                    }else {
                        adapter.appendList(newsBeans);
                        v.srl.setNoMoreData(true);
                        v.srl.finishLoadMore();
                        page++;
                    }
                }
            }
        });
    }
}
