package com.ww.android.governmentheart.fragment.wisdom;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.wisdom.TransmissionAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionBean;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ww.com.core.utils.TimeUtils;

/**
 * @Author feng
 * @Date 2018/6/18
 * 资料发送
 */
public class TransmissionFragment extends BaseFragment<RefreshView, WisdomModel> {

    private TransmissionAdapter adapter;
    private int page;

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_transmission;
    }

    @Override
    protected void init() {
        initRecycler();
        initListener();
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
                material();
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                material();
            }
        });
    }

    private void initRecycler() {
        v.initDefaultDecoration();
        adapter = new TransmissionAdapter(getContext());
        v.crv.setAdapter(adapter);
    }

    private void material() {
        UserBean userBean = (UserBean) BaseApplication.getInstance().getUserInfo();
        if (userBean == null || userBean.getUser() == null) {
            return;
        }
        Map map = new HashMap();
        map.put("userId", userBean.getUser().getId());
        map.put("pageNo", page);
        map.put("date", TimeUtils.date2String(new Date()));
        if (v.srl == null) {
            return;
        }

        m.material(map, new BaseObserver<PageListBean<TransmissionBean>>(getContext(),
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<TransmissionBean>
                                             transmissionBeanPageListBean, @Nullable
                                             List<PageListBean<TransmissionBean>> list, @Nullable
                                             PageBean<PageListBean<TransmissionBean>> pageBean) {

                if (transmissionBeanPageListBean != null && transmissionBeanPageListBean.getList() != null) {
                    List<TransmissionBean> transmissionBeans = transmissionBeanPageListBean.getList();
                    PagingBean pagingBean = transmissionBeanPageListBean.getPage();
                    int totalPage = pagingBean.getTotalPage();
                    if (page == 0) {
                        v.srl.finishRefresh();
                        if (transmissionBeans != null && transmissionBeans.size() > 0) {
                            adapter.addList(transmissionBeans);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    } else {
                        v.srl.finishLoadMore();
                        if (page < totalPage) {
                            adapter.appendList(transmissionBeans);
                            v.srl.setNoMoreData(false);
                            page++;
                        } else {
                            v.srl.setNoMoreData(true);
                        }
                    }
                }else {
                    if (page == 0) {
                        v.srl.finishRefresh();
                    }else {
                        v.srl.finishLoadMore();
                    }
                }
            }
        });
    }
}
