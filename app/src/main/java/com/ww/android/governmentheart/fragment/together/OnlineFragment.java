package com.ww.android.governmentheart.fragment.together;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.together.OnlineAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.together.OnlineBean;
import com.ww.android.governmentheart.mvp.model.TogetherModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author feng
 * @Date 2018/6/16
 * 在线直播
 */
public class OnlineFragment extends BaseFragment<RefreshView, TogetherModel> {

    private OnlineAdapter adapter;
    private int page;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void init() {
        initListener();
        initRecycler();
        online();
    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    private void initListener() {
        if (v.srl == null) {
            return;
        }

        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                online();
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                online();
            }
        });
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(getContext()), RecyclerHelper
                .defaultMoreDecoration(getContext()));
        adapter = new OnlineAdapter(getContext());
        v.crv.setAdapter(adapter);
    }


    private void online() {
        Map map = new HashMap();
        map.put("pageNo",page);
        if (v.srl == null) {
            return;
        }
        m.online(map ,new BaseObserver<PageListBean<OnlineBean>>(getContext(),bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<OnlineBean> onlineBeanPageListBean, @Nullable
                    List<PageListBean<OnlineBean>> list, @Nullable PageBean<PageListBean<OnlineBean>>
                                             pageBean) {

                if (onlineBeanPageListBean != null && onlineBeanPageListBean.getList() != null
                        && onlineBeanPageListBean.getList().size()>0) {
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<OnlineBean> actBeans = onlineBeanPageListBean.getList();
                    PagingBean pagingBean = onlineBeanPageListBean.getPage();
                    int totalPage = pagingBean.getTotalPage();
                    if (page == 0) {
                        v.srl.finishRefresh();
                        if (actBeans != null && actBeans.size() > 0) {
                            adapter.addList(actBeans);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    } else {
                        v.srl.finishLoadMore();
                        if (page < totalPage) {
                            adapter.appendList(actBeans);
                            v.srl.setNoMoreData(false);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    }
                }else {
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


    private void reload(int type){
        if (type == EmptyLayout.STATUS_NO_NET){
            v.loadStatus(EmptyLayout.STATUS_NO_NET);
        }else {
            v.loadStatus(EmptyLayout.STATUS_NO_DATA);
        }
        v.mEmptyLayout.setRetryListener(new EmptyLayout.OnRetryListener() {
            @Override
            public void onRetry() {
                online();
            }
        });
        if (page == 0) {
            v.srl.finishRefresh();
        } else {
            v.srl.finishLoadMore();
        }
    }
}
