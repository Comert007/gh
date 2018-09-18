package com.ww.android.governmentheart.activity.home;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.home.MemberAdapter;
import com.ww.android.governmentheart.config.type.CodeType;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.together.OrganizationTypeBean;
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ww.com.core.Debug;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class MemberActivity extends BaseActivity<RefreshView, MainModel> {

    private MemberAdapter adapter;
    private OrganizationTypeBean mOrganizationTypeBean;

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
        v.setRefreshType(RefreshType.REFRESH);

        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                organizations();
            }
        });
        adapter.setOnTypeListener(new MemberAdapter.OnTypeListener() {
            @Override
            public void onType(OrganizationTypeBean organizationTypeBean) {
//                resultFinish(organizationTypeBean);
                OrgMembersActivity.start(MemberActivity.this,organizationTypeBean.getCode());
            }
        });

    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(this));
        adapter = new MemberAdapter(this);
        v.crv.setAdapter(adapter);
    }

    /**
     * v.srl.finishRefresh();
     * if (suggestBeanPageListBean != null && suggestBeanPageListBean.getList() != null
     * && suggestBeanPageListBean.getList().size() > 0) {
     * v.loadStatus(EmptyLayout.STATUS_HIDE);
     * List<OrganizationBean> suggestBeans = setType(suggestBeanPageListBean.getList
     * ());
     * adapter.addList(suggestBeans);
     * } else {
     * v.loadStatus(EmptyLayout.STATUS_NO_DATA);
     * }
     */


    private void organizations() {
        Map map = new HashMap();
        m.organizationType(new BaseObserver<PageListBean<OrganizationTypeBean>>(this,
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<OrganizationTypeBean>
                                             organizationTypeBeanPageListBean, @Nullable
                                             List<PageListBean<OrganizationTypeBean>> list,
                                     @Nullable PageBean<PageListBean<OrganizationTypeBean>> page) {
                v.srl.finishRefresh();
                if (organizationTypeBeanPageListBean != null && organizationTypeBeanPageListBean
                        .getList() != null
                        && organizationTypeBeanPageListBean.getList().size() > 0) {
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<OrganizationTypeBean> organizationTypeBeans = setType
                            (organizationTypeBeanPageListBean.getList
                            ());
                    adapter.addList(organizationTypeBeans);
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
        v.srl.finishRefresh();
    }


    private List<OrganizationTypeBean> setType(List<OrganizationTypeBean> organizationTypeBeans) {
        List<OrganizationTypeBean> organizationTypeBeanList = new ArrayList<>();
        List<OrganizationTypeBean> headers = new ArrayList<>();
        List<OrganizationTypeBean> bodies = new ArrayList<>();
        for (OrganizationTypeBean organizationTypeBean : organizationTypeBeans) {
            if (organizationTypeBean.getCode().startsWith("11")) {
                organizationTypeBean.setItemType(OrganizationTypeBean.MULTIPLE_HEADER);
                headers.add(organizationTypeBean);
            } else {
                organizationTypeBean.setItemType(OrganizationTypeBean.MULTIPLE_BODY);
                bodies.add(organizationTypeBean);
            }
        }
        Debug.d("headers size:" + headers.size() + "\n bodies size:" + bodies.size());

        OrganizationTypeBean headerTitleName = new OrganizationTypeBean(OrganizationTypeBean
                .MULTIPLE_TITLE);
        headerTitleName.setName("民主党派");
        OrganizationTypeBean header = new OrganizationTypeBean(OrganizationTypeBean
                .MULTIPLE_HEADER);
        header.setOrganizationTypeBeans(headers);
        OrganizationTypeBean bodyTitleName = new OrganizationTypeBean(OrganizationTypeBean
                .MULTIPLE_TITLE);
        bodyTitleName.setName("其他组织");

        organizationTypeBeanList.add(headerTitleName);
        organizationTypeBeanList.add(header);
        organizationTypeBeanList.add(bodyTitleName);
        organizationTypeBeanList.addAll(bodies);
        return organizationTypeBeanList;
    }


    private void resultFinish(OrganizationTypeBean typeBean) {
        Intent intent = getIntent();
        intent.putExtra("type",typeBean);
        setResult(CodeType.RESULT_MEMBER,intent);
        finish();
    }
}
