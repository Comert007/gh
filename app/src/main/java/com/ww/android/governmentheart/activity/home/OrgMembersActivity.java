package com.ww.android.governmentheart.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.home.OrgMembersAdapter;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.home.OrganizationBean;
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.model.home.UserMemberBean;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrgMembersActivity extends BaseActivity<RefreshView, MainModel> {

    private OrgMembersAdapter adapter;
    private int page;
    private String id;
    private String code;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_org_members;
    }

    public static void start(Context context,String code) {
        Intent starter = new Intent(context, OrgMembersActivity.class);
        starter.putExtra("code",code);
        context.startActivity(starter);
    }

    @Override
    protected void init() {
        code = getIntent().getStringExtra("code");
        initRecycler();
        initListener();
        organizations();
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(this));
        adapter = new OrgMembersAdapter(this);
        v.crv.setAdapter(adapter);
    }


    private void initListener() {
        if (v.srl == null) {
            return;
        }

        v.setRefreshType(RefreshType.ENABLE);

        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                userPage();
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                userPage();
            }
        });

    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }


    private void organizations() {
        Map map = new HashMap();
        if (!TextUtils.isEmpty(code)) {
            map.put("code", code);
        }
        m.organizations(map, new BaseObserver<PageListBean<OrganizationBean>>(this, bindToLifecycle
                ()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<OrganizationBean>
                                             organizationBeanPageListBean,
                                     @Nullable List<PageListBean<OrganizationBean>> list, @Nullable
                                             PageBean<PageListBean<OrganizationBean>> pageBean) {
                if (organizationBeanPageListBean != null && organizationBeanPageListBean.getList
                        () != null
                        && organizationBeanPageListBean.getList().size() > 0) {
                    List<OrganizationBean> organizationBeans = organizationBeanPageListBean
                            .getList();
                    id = organizationBeans.get(0).getId();
                    userPage();
                }
            }

        });
    }


    private void userPage() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("pageNo", page);
        m.userpage(map, new BaseObserver<PageListBean<UserMemberBean>>(this, bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<UserMemberBean>
                                             userMemberbeanPageListBean, @Nullable
                                             List<PageListBean<UserMemberBean>> list, @Nullable
                                             PageBean<PageListBean<UserMemberBean>> pageBean) {

                if (userMemberbeanPageListBean != null && userMemberbeanPageListBean.getList() != null
                        && userMemberbeanPageListBean.getList().size() >0) {

                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<UserMemberBean> userMemberBeans = userMemberbeanPageListBean.getList();
                    PagingBean pagingBean = userMemberbeanPageListBean.getPage();
                    int totalPage = pagingBean.getTotalPage();
                    if (page == 0) {
                        v.srl.finishRefresh();
                        if (userMemberBeans != null && userMemberBeans.size() > 0) {
                            adapter.addList(userMemberBeans);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    } else {
                        v.srl.finishLoadMore();
                        if (page < totalPage) {
                            adapter.appendList(userMemberBeans);
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
                userPage();
            }
        });
        if (page == 0) {
            v.srl.finishRefresh();
        } else {
            v.srl.finishLoadMore();
        }
    }

}
