package com.ww.android.governmentheart.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.join.JoinAdapter;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindColor;
import ww.com.core.utils.TimeUtils;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class JoinFragment extends BaseFragment<RefreshView, CommonModel> {
    @BindColor(R.color.color_black)
    int colorBlack;

    private JoinAdapter adapter;
    private int page;
    private NewsTypeBean typeBean;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_join;
    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected void init() {
        initData();
        initListener();
        initRecycler();
        v.srl.autoRefresh();
    }

    public static JoinFragment newInstance(NewsTypeBean newsTypeBean){
        JoinFragment fragment = new JoinFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("type", newsTypeBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    private void initData(){
        typeBean = (NewsTypeBean) getArguments().getSerializable("type");
    }

    private void initListener() {
        if (v.srl == null) {
            return;
        }
        v.setRefreshType(RefreshType.REFRESH);
        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                news();
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
        v.initRecycler(RecyclerHelper.defaultManager(getContext()), RecyclerHelper
                .defaultSingleDecoration(getContext()));

        adapter = new JoinAdapter(getContext());
        v.crv.setAdapter(adapter);
    }

    private void news() {
        Map map = new HashMap();
        map.put("code", typeBean.getCode());
        map.put("pageNo", page);
        if (page == 0) {
            map.put("date", TimeUtils.date2String(new Date()));
        }
        if (v.srl == null) {
            return;
        }
        m.news(map, new BaseObserver<PageListBean<NewsBean>>(getContext(), bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<NewsBean> newsBeanPageListBean,
                                     @Nullable List<PageListBean<NewsBean>> list, @Nullable
                                             PageBean<PageListBean<NewsBean>> pageBean) {

                if (newsBeanPageListBean != null && newsBeanPageListBean.getList() != null) {
                    List<NewsBean> newsBeans = setType(newsBeanPageListBean.getList());
                    PagingBean pagingBean = newsBeanPageListBean.getPage();
                    int totalPage = pagingBean.getTotalPage();
                    if (page == 0) {
                        NewsBean newsBean = new NewsBean(NewsBean.MULTIPLE_HEADER);
                        newsBeans.add(0, newsBean);
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

            private List<NewsBean> setType(List<NewsBean> newsBeans) {
                for (NewsBean newsBean : newsBeans) {
                    newsBean.setItemType(NewsBean.MULTIPLE_BODY);
                }
                return newsBeans;
            }

            @Override
            protected void onFailure() {
                super.onFailure();
                if (page == 0){
                    v.srl.finishRefresh(false);
                }else {
                    v.srl.finishLoadMore(false);
                }
            }
        });
    }

}
