package com.ww.android.governmentheart.fragment.style;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.style.FeaturesAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.model.CommonModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ww.com.core.utils.TimeUtils;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class FeaturesFragment extends BaseFragment<RefreshView, CommonModel> {

    private FeaturesAdapter adapter;
    private String code; // code:1 方针，2 统战知识，3 权威解读， 4 政策库 5 崇州特色 6 农副产品 7 电商介绍 8 人物访谈 9加入我（加入组织）
    private int page;
    private NewsTypeBean mTypeBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_features;
    }

    public static FeaturesFragment newInstance(NewsTypeBean newsTypeBean) {
        FeaturesFragment fragment = new FeaturesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", newsTypeBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void init() {
        mTypeBean = (NewsTypeBean) getArguments().getSerializable("type");
        if (mTypeBean != null) {
            code = mTypeBean.getCode();
        }
        v.setRefreshType(RefreshType.REFRESH);
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

            }
        });
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(getContext()), RecyclerHelper
                .defaultMoreDecoration(getContext()));

        adapter = new FeaturesAdapter(getContext());
        v.crv.setAdapter(adapter);
    }


//
    private void news() {
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
                            List<NewsTypeBean> newsTypeBeans = new ArrayList<>();
                            NewsTypeBean header = new NewsTypeBean(NewsTypeBean.MULTIPLE_HEADER);
                            NewsTypeBean body = new NewsTypeBean(NewsTypeBean.MULTIPLE_BODY);
                            newsBeans = setType(newsBeans);
                            body.setNews(newsBeans);
                            newsTypeBeans.add(header);
                            newsTypeBeans.add(body);
                            adapter.addList(newsTypeBeans);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    }
//                    else {
//                        v.srl.finishLoadMore();
//                        if (page < totalPage) {
//                            adapter.appendList(newsBeans);
//                            v.srl.setNoMoreData(false);
//                            page++;
//                        } else {
//                            v.srl.setNoMoreData(true);
//                        }
//                    }
                }
            }
        });
    }


    private List<NewsBean> setType(List<NewsBean> newsBeans) {
        for (NewsBean newsBean : newsBeans) {
            newsBean.setItemType(NewsBean.MULTIPLE_BODY);
        }
        return newsBeans;
    }
}
