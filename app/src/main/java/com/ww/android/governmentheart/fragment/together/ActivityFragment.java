package com.ww.android.governmentheart.fragment.together;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.together.ActivityAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;
import com.ww.android.governmentheart.mvp.bean.together.ActBean;
import com.ww.android.governmentheart.mvp.model.TogetherModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.utils.ToastUtils;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ww.com.core.utils.TimeUtils;

/**
 * @Author feng
 * @Date 2018/6/16
 * 活动信息
 */
public class ActivityFragment extends BaseFragment<RefreshView, TogetherModel> {

    private ActivityAdapter adapter;
    private int page;
    private String type;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_activity;
    }

    @Override
    protected void init() {
        type = getArguments().getString("type");
        initRecycler();
        initListener();

        v.srl.autoRefresh();
    }


    public static ActivityFragment newInstance(String type) {
        ActivityFragment activityFragment = new ActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        activityFragment.setArguments(bundle);
        return activityFragment;
    }

    private void initListener() {
        if (v.srl == null) {
            return;
        }

        v.srl.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                act();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                act();
            }
        });

        adapter.setOnActionListener((view, position) -> {
            joinAct(position);
        });
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(getContext()), RecyclerHelper
                .defaultMoreDecoration(getContext()));
        adapter = new ActivityAdapter(getContext());
        v.crv.setAdapter(adapter);
    }


    //t 1 即将开始 2 正在进行 3 结束
    private void act() {
        Map map = new HashMap();
        map.put("pageNo", page);
        map.put("date", TimeUtils.date2Milliseconds(new Date()));
        map.put("t", type);
        m.act(map, new BaseObserver<PageListBean<ActBean>>(getContext(), bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<ActBean> actBeanPageListBean,
                                     @Nullable List<PageListBean<ActBean>> list, @Nullable
                                             PageBean<PageListBean<ActBean>> pageBean) {
                if (actBeanPageListBean != null && actBeanPageListBean.getList() != null &&
                        actBeanPageListBean.getList().size()>0) {
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<ActBean> actBeans = actBeanPageListBean.getList();
                    for (ActBean actBean : actBeans) {
                        actBean.setType(type);
                    }
                    PagingBean pagingBean = actBeanPageListBean.getPage();
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

    private void joinAct(int position) {
        Map map = new HashMap();
        UserBean userBean = (UserBean) BaseApplication.getInstance().getUserInfo();
        if (userBean != null && userBean.getUser() != null) {
            map.put("userId", userBean.getUser().getId());
            map.put("actId", adapter.getItem(position).getId());
            m.joinAct(map, new BaseObserver<String>(getContext(), bindToLifecycle()) {
                @Override
                protected void onSuccess(@Nullable String s, @Nullable List<String> list,
                                         @Nullable PageBean<String> page) {
                    ToastUtils.showToast(TextUtils.isEmpty(getResponseBean().getMsg()) ? "报名成功" :
                            getResponseBean().getMsg());
                }
            });
        }

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
                act();
            }
        });
        if (page == 0) {
            v.srl.finishRefresh();
        } else {
            v.srl.finishLoadMore();
        }
    }
}
