package com.ww.android.governmentheart.fragment.heart;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.heart.PolicyAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
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
 * @Date 2018/7/8
 */
public class PolicyCoreFragment extends BaseFragment<RefreshView,CommonModel> {

    private String code; // code:1 方针，2 统战知识，3 权威解读， 4 政策库 5 崇州特色 6 农副产品 7 电商介绍 8 人物访谈 9加入我（加入组织）
    private int page;
    private PolicyAdapter adapter;


    public static PolicyCoreFragment newInstance(String code){
        PolicyCoreFragment fragment = new PolicyCoreFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code",code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_policy_core;
    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected void init() {
        initData();
        initListener();
        if (v.crv == null) {
            return;
        }
        initRecycler();
        v.srl.autoRefresh();
    }

    private void initData(){
        code = getArguments().getString("code","0");
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

        adapter = new PolicyAdapter(getContext());
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
        m.news(map, new BaseObserver<PageListBean<NewsBean>>(getContext(), bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<NewsBean> newsBeanPageListBean,
                                     @Nullable List<PageListBean<NewsBean>> list, @Nullable
                                             PageBean<PageListBean<NewsBean>> pageBean) {

                if (newsBeanPageListBean != null && newsBeanPageListBean.getList() != null) {
                    List<NewsBean> newsBeans = newsBeanPageListBean.getList();
                    PagingBean pagingBean = newsBeanPageListBean.getPage();
                    int totalPage = pagingBean.getTotalPage();
                    if (page == 0) {
                        v.srl.finishRefresh();
                        if (newsBeans != null && newsBeans.size() > 0) {
                            adapter.addList(newsBeans);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    } else {
                        v.srl.finishLoadMore();
                        if (page < totalPage) {
                            adapter.appendList(newsBeans);
                            v.srl.setNoMoreData(false);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    }
                }else {
                    if (page == 0) {
                        v.srl.finishRefresh();
                    }else {
                        v.srl.finishLoadMore();
                    }
                }
            }

            @Override
            protected void onFailure() {
                if (page == 0) {
                    v.srl.finishRefresh();
                }else {
                    v.srl.finishLoadMore();
                }
            }
        });
    }
}
