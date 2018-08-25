package com.ww.android.governmentheart.fragment.style;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.style.FeaturesAdapter;
import com.ww.android.governmentheart.config.listener.OnActionListener;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsChildTypeBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.model.CommonModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

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
    private String id;
    private NewsTypeBean mTypeBean;
    private NewsChildTypeBean headerBean;

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
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected void init() {
        mTypeBean = (NewsTypeBean) getArguments().getSerializable("type");
        if (mTypeBean != null) {
            id = mTypeBean.getId();
        }
        v.setRefreshType(RefreshType.REFRESH);
        initRecycler();
        initListener();
        categoryChild();
    }

    private void initListener() {
        adapter.setOnActionListener(new OnActionListener() {
            @Override
            public void onAction(View view, int position) {
                NewsChildTypeBean childTypeBean = adapter.getItem(position);
                news(position, childTypeBean);
            }
        });

        v.setRefreshType(RefreshType.REFRESH);
        if (v.srl == null) {
            return;
        }

        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                categoryChild();
            }
        });

    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(getContext()));
        adapter = new FeaturesAdapter(getContext());
        v.crv.setAdapter(adapter);
    }

    //
    private void news(int position, NewsChildTypeBean childTypeBean) {
        Map map = new HashMap();
        map.put("id", childTypeBean.getId());
        m.news(map, new BaseObserver<PageListBean<NewsBean>>(getContext(), bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<NewsBean> newsBeanPageListBean,
                                     @Nullable List<PageListBean<NewsBean>> list, @Nullable
                                             PageBean<PageListBean<NewsBean>> pageBean) {

                if (newsBeanPageListBean != null && newsBeanPageListBean.getList() != null) {
                    List<NewsBean> newsBeans = newsBeanPageListBean.getList();
                    if (newsBeans != null && newsBeans.size() > 0) {
                        childTypeBean.setNewsBeans(newsBeans);
                        adapter.notifyItemChanged(position);
                    }
                }
            }
        });
    }


    /**
     * 获取推荐位
     */
    private void recommend(List<NewsChildTypeBean> typeBeans) {
        Map map = new HashMap();
        map.put("id", id);
        m.recommend(map, new BaseObserver<PageListBean<NewsBean>>(getContext(), bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<NewsBean> newsBeanPageListBean,
                                     @Nullable List<PageListBean<NewsBean>> list, @Nullable
                                             PageBean<PageListBean<NewsBean>> page) {
                if (newsBeanPageListBean != null) {
                    NewsBean headerNewsBean = newsBeanPageListBean.getData();
                    //String id, String name, String parentId, String image,
                    //                             String count, String description
                    headerBean = new NewsChildTypeBean(NewsTypeBean.MULTIPLE_HEADER,
                            headerNewsBean.getId(),
                            headerNewsBean.getTitle(), "", headerNewsBean.getImage(), "0",
                            headerNewsBean.getDescription(), headerNewsBean.getUrl(),
                            headerNewsBean.getViewNum(), headerNewsBean.getCommentCount());
                    typeBeans.add(0,headerBean);
                    adapter.addList(typeBeans);
                }
            }
        });
    }


    private void categoryChild() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("pageNo", 100);
        map.put("date", TimeUtils.date2String(new Date()));

        m.newsCategoryChild(map, new BaseObserver<PageListBean<NewsChildTypeBean>>(getContext(),
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<NewsChildTypeBean>
                                             newsChildTypeBeanPageListBean, @Nullable
                                             List<PageListBean<NewsChildTypeBean>> list, @Nullable
                                             PageBean<PageListBean<NewsChildTypeBean>> pageBean) {

                if (newsChildTypeBeanPageListBean != null && newsChildTypeBeanPageListBean
                        .getList() != null &&
                        newsChildTypeBeanPageListBean.getList().size() > 0) {
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<NewsChildTypeBean> newsBeans = newsChildTypeBeanPageListBean.getList();
                    newsBeans = setType(newsBeans);
                    if (newsBeans != null && newsBeans.size() > 0) {
                        recommend(newsBeans);
                        v.srl.finishRefresh();
                    } else {
                        v.srl.setNoMoreData(true);
                    }
                } else {
                    reload(EmptyLayout.STATUS_NO_DATA);
                }
            }

            @Override
            protected void onFailure() {
                super.onFailure();
                reload(EmptyLayout.STATUS_NO_NET);
            }
        });
    }

    private void reload(int type) {
        if (type == EmptyLayout.STATUS_NO_NET) {
            v.loadStatus(EmptyLayout.STATUS_NO_NET);
        } else {
            v.loadStatus(EmptyLayout.STATUS_NO_DATA);
        }
        v.mEmptyLayout.setRetryListener(new EmptyLayout.OnRetryListener() {
            @Override
            public void onRetry() {
                categoryChild();
            }
        });
        v.srl.finishRefresh();
    }

    private List<NewsChildTypeBean> setType(List<NewsChildTypeBean> typeBeans) {
        for (NewsChildTypeBean newsBean : typeBeans) {
            newsBean.setItemType(NewsBean.MULTIPLE_BODY);
        }
        return typeBeans;
    }
}
