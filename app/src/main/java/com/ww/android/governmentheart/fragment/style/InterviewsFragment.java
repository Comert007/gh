package com.ww.android.governmentheart.fragment.style;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.style.InterviewsAdapter;
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
 * @Date 2018/6/17
 * 人物访谈
 */
public class InterviewsFragment extends BaseFragment<RefreshView,CommonModel> {

    private InterviewsAdapter adapter;
    private int page;
    private String code;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_interviews;
    }


    public static InterviewsFragment newInstance(String code) {
        InterviewsFragment fragment = new InterviewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("code", code);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected void init() {
        code = getArguments().getString("code","0");
        initListener();
        initRecycler();
        v.srl.autoRefresh();
    }

    private void initListener() {
        if (v.srl == null) {
            return;
        }

        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page =0;
                news();
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                news();
            }
        });
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(getContext()),RecyclerHelper.defaultMoreDecoration(getContext()));
        adapter = new InterviewsAdapter(getContext());
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
                }
            }
        });
    }
}
