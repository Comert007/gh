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
import com.ww.android.governmentheart.adapter.wisdom.QuestionAdapter;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.QuestionBean;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySuggestActivity extends BaseActivity<RefreshView,WisdomModel>{


    private QuestionAdapter adapter;
    private int page;


    public static void start(Context context) {
        Intent starter = new Intent(context, MySuggestActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_my_suggest;
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
                .defaultMoreDecoration(this));
        adapter = new QuestionAdapter(this);
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
                questions();
            }
        });

        v.srl.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                questions();
            }
        });

    }

    private void questions() {
        Map map = new HashMap();

        UserBean user = (UserBean) BaseApplication.getInstance().getUserInfo();
        map.put("userId", user.getUser().getId());
        map.put("pageNo", page);
        m.questions(map, new BaseObserver<PageListBean<QuestionBean>>(this,
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<QuestionBean>
                                             questionBeanPageListBean, @Nullable
                                             List<PageListBean<QuestionBean>> list, @Nullable
                                             PageBean<PageListBean<QuestionBean>> pageBean) {
                if (questionBeanPageListBean != null && questionBeanPageListBean.getList() != null
                        && questionBeanPageListBean.getList().size() >0) {
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<QuestionBean> commentBeans = questionBeanPageListBean.getList();
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
                questions();
            }
        });
        if (page == 0) {
            v.srl.finishRefresh();
        } else {
            v.srl.finishLoadMore();
        }
    }

//    private void suggest() {
//        Map map = new HashMap();
//        UserBean user = (UserBean) BaseApplication.getInstance().getUserInfo();
//        map.put("userId", user.getUser().getId());
//        map.put("pageNo", page);
//        if (v.srl == null) {
//            return;
//        }
//        m.suggest(map, new BaseObserver<PageListBean<SuggestBean>>(this, bindToLifecycle
//                ()) {
//            @Override
//            protected void onSuccess(@Nullable PageListBean<SuggestBean> suggestBeanPageListBean,
//                                     @Nullable List<PageListBean<SuggestBean>> list, @Nullable
//                                             PageBean<PageListBean<SuggestBean>> pageBean) {
//
//                if (suggestBeanPageListBean != null && suggestBeanPageListBean.getList() != null
//                        && suggestBeanPageListBean.getList().size() > 0) {
//                    v.loadStatus(EmptyLayout.STATUS_HIDE);
//                    List<SuggestBean> suggestBeans = suggestBeanPageListBean.getList();
//                    PagingBean pagingBean = suggestBeanPageListBean.getPage();
//                    int totalPage = pagingBean.getTotalPage();
//                    if (page == 0) {
//                        v.srl.finishRefresh();
//                        if (suggestBeans != null && suggestBeans.size() > 0) {
//                            adapter.addList(suggestBeans);
//                            page++;
//                        } else {
//                            v.srl.setNoMoreData(true);
//                        }
//                    } else {
//                        v.srl.finishLoadMore();
//                        if (page < totalPage) {
//                            adapter.appendList(suggestBeans);
//                            v.srl.setNoMoreData(false);
//                            page++;
//                        } else {
//                            v.srl.setNoMoreData(true);
//                        }
//                    }
//                } else {
//                    reload(EmptyLayout.STATUS_NO_DATA);
//                }
//            }
//
//            @Override
//            protected void onFailure() {
//                super.onFailure();
//                reload(EmptyLayout.STATUS_NO_NET);
//            }
//        });
//    }

//    private void reload(int type) {
//        if (type == EmptyLayout.STATUS_NO_NET) {
//            v.loadStatus(EmptyLayout.STATUS_NO_NET);
//        } else {
//            v.loadStatus(EmptyLayout.STATUS_NO_DATA);
//        }
//        v.mEmptyLayout.setRetryListener(new EmptyLayout.OnRetryListener() {
//            @Override
//            public void onRetry() {
//                suggest();
//            }
//        });
//        if (page == 0) {
//            v.srl.finishRefresh();
//        } else {
//            v.srl.finishLoadMore();
//        }
//    }

}
