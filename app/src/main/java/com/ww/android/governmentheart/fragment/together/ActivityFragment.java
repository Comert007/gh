package com.ww.android.governmentheart.fragment.together;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.together.ActivityAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.together.ActBean;
import com.ww.android.governmentheart.mvp.model.TogetherModel;
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
               page =0;
               act();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                act();
            }
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

                if (actBeanPageListBean != null && actBeanPageListBean.getList() != null) {
                    List<ActBean> actBeans = actBeanPageListBean.getList();
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
                }
            }
        });
    }
}
