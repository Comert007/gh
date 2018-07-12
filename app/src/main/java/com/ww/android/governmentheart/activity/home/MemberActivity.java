package com.ww.android.governmentheart.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.home.MemberAdapter;
import com.ww.android.governmentheart.config.type.CodeType;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.home.OrganizationBean;
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class MemberActivity extends BaseActivity<RefreshView, MainModel> {

    private MemberAdapter adapter;
    private int page;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_member;
    }

    public static void start(Activity context) {
        Intent intent = new Intent(context, MemberActivity.class);
        context.startActivityForResult(intent, CodeType.REQUEST_MEMBER);
    }

    @Override
    protected void init() {
        initRecycler();
        initListener();
        v.srl.autoRefresh();
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }


    private void initListener() {
        if (v.srl == null) {
            return;
        }

        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                organizations();
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                organizations();
            }
        });
    }

    private void initRecycler() {
        //RecyclerHelper.gridManager(this, 2)
        v.initRecycler(RecyclerHelper.defaultManager(this));
        adapter = new MemberAdapter(this);
        v.crv.setAdapter(adapter);
    }


    private void organizations() {
        Map map = new HashMap();
        m.organizations(map, new BaseObserver<PageListBean<OrganizationBean>>(this, bindToLifecycle
                ()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<OrganizationBean>
                                             suggestBeanPageListBean,
                                     @Nullable List<PageListBean<OrganizationBean>> list, @Nullable
                                             PageBean<PageListBean<OrganizationBean>> pageBean) {

                if (suggestBeanPageListBean != null && suggestBeanPageListBean.getList() != null
                        && suggestBeanPageListBean.getList().size() > 0) {
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<OrganizationBean> suggestBeans = setType(suggestBeanPageListBean.getList());
                    PagingBean pagingBean = suggestBeanPageListBean.getPage();
                    int totalPage = pagingBean.getTotalPage();
                    if (page == 0) {
                        v.srl.finishRefresh();
                        if (suggestBeans != null && suggestBeans.size() > 0) {
                            adapter.addList(suggestBeans);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    } else {
                        v.srl.finishLoadMore();
                        if (page < totalPage) {
                            adapter.appendList(suggestBeans);
                            v.srl.setNoMoreData(false);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    }
                } else {
                    v.loadStatus(EmptyLayout.STATUS_NO_DATA);
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
                organizations();
            }
        });
        if (page == 0) {
            v.srl.finishRefresh();
        } else {
            v.srl.finishLoadMore();
        }
    }


    private List<OrganizationBean> setType(List<OrganizationBean> organizationBeans) {
        for (OrganizationBean organizationBean : organizationBeans) {
            organizationBean.setItemType(OrganizationBean.MULTIPLE_BODY);
        }
        return organizationBeans;
    }
}
