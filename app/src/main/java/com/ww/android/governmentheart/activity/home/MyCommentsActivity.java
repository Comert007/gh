package com.ww.android.governmentheart.activity.home;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.home.MyCommentsAdapter;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.home.CommentBean;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;
import com.ww.android.governmentheart.mvp.model.CommonModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCommentsActivity extends BaseActivity<RefreshView,CommonModel>{


    private MyCommentsAdapter adapter;
    private int page;


    public static void start(Context context) {
        Intent starter = new Intent(context, MyCommentsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_comments;
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


    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(this), RecyclerHelper
                .defaultSingleDecoration(this));
        adapter = new MyCommentsAdapter(this);
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
                comments();
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                comments();
            }
        });

    }

    private void comments() {
        Map map = new HashMap();
        UserBean user = (UserBean) BaseApplication.getInstance().getUserInfo();
        map.put("userId", user.getUser().getId());
        map.put("pageNo", page);

        if (v.srl == null) {
            return;
        }
        m.comments(map, new BaseObserver<PageListBean<CommentBean>>(this, bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<CommentBean> commentBeanPageListBean,
                                     @Nullable List<PageListBean<CommentBean>> list, @Nullable
                                             PageBean<PageListBean<CommentBean>> pageBean) {

                if (commentBeanPageListBean != null && commentBeanPageListBean.getList() != null
                        && commentBeanPageListBean.getList().size() >0) {
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<CommentBean> commentBeans = commentBeanPageListBean.getList();
                    PagingBean pagingBean = commentBeanPageListBean.getPage();
                    btnTitleRight.setText("共" + pagingBean.getTotalNum() + "条");
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
                comments();
            }
        });
        if (page == 0) {
            v.srl.finishRefresh();
        } else {
            v.srl.finishLoadMore();
        }
    }

}
