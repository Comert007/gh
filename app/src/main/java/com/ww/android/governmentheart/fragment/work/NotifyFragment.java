package com.ww.android.governmentheart.fragment.work;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.work.NotifyAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.work.NotifyEntity;
import com.ww.android.governmentheart.mvp.bean.work.ReceptionEntity;
import com.ww.android.governmentheart.mvp.model.work.WorkModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotifyFragment extends BaseFragment<RefreshView, WorkModel> {


    private NotifyAdapter adapter;
    private int page;

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_notify;
    }

    @Override
    protected void init() {
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
                page = 0;
                notifyList();
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                notifyList();
            }
        });
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(getContext()));
        adapter = new NotifyAdapter(getContext());
        v.crv.setAdapter(adapter);
    }

    private void notifyList() {
        Map map = new HashMap();
        map.put("pageNo", page);
        m.receiveMaterial(map, new BaseObserver<PageListBean<ReceptionEntity>>(getContext(),
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<ReceptionEntity>
                                             questionBeanPageListBean, @Nullable
                                             List<PageListBean<ReceptionEntity>> list, @Nullable
                                             PageBean<PageListBean<ReceptionEntity>> pageBean) {
                if (questionBeanPageListBean != null && questionBeanPageListBean.getList() != null
                        && questionBeanPageListBean.getList().size() >0) {
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<ReceptionEntity> commentBeans = questionBeanPageListBean.getList();
                    PagingBean pagingBean = questionBeanPageListBean.getPage();
                    int totalPage = pagingBean.getTotalPage();
                    if (page == 0) {
                        v.srl.finishRefresh();
                        if (commentBeans != null && commentBeans.size() > 0) {
                            adapter.addList(commentBeans);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    } else {
                        v.srl.finishLoadMore();
                        if (page < totalPage) {
                            adapter.appendList(commentBeans);
                            v.srl.setNoMoreData(false);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    }
                } else {
                    reload(EmptyLayout.STATUS_NO_DATA);
                }
            }

            @Override
            protected void onFailure() {
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
                notifyList();
            }
        });
        if (page == 0) {
            v.srl.finishRefresh();
        } else {
            v.srl.finishLoadMore();
        }
    }

}
