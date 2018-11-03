package com.ww.android.governmentheart.activity.work;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.work.ThemeContentAdapter;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.config.type.RequestType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.work.ThemeDetailBean;
import com.ww.android.governmentheart.mvp.bean.work.ThemeReplyEntity;
import com.ww.android.governmentheart.mvp.model.work.WorkModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.mvp.vu.holder.ThemeContentHeaderHolder;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThemeContentActivity extends BaseActivity<RefreshView,WorkModel>{


    private ThemeContentHeaderHolder mHolder;

    private ThemeContentAdapter adapter;
    private int page;
    private String id;

    public static void start(Context context,String id) {
        Intent starter = new Intent(context, ThemeContentActivity.class);
        starter.putExtra("id",id);
        context.startActivity(starter);
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_theme_content;
    }

    @Override
    protected void init() {
        mHolder = new ThemeContentHeaderHolder(this);
        id = getIntent().getStringExtra("id");
        initListener();
        initRecycler();
        v.srl.autoRefresh();
    }

    private void initListener() {
        if (v.srl == null) {
            return;
        }

        v.srl.setOnRefreshListener(refreshLayout -> {
            page = 0;
            onThemeDetail();
            messageList();
        });

        v.srl.setOnLoadMoreListener(refreshLayout -> messageList());
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(this));
        adapter = new ThemeContentAdapter(this);
        v.crv.setAdapter(adapter);
        v.crv.addHeadView(mHolder.getView());
    }

    private void onThemeDetail(){
        Map map = new HashMap();
        map.put("id",id);
        m.topicDetail(map, new BaseObserver<PageListBean<ThemeDetailBean>>(this,bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<ThemeDetailBean> themeDetailBeanPageListBean,
                                     @Nullable List<PageListBean<ThemeDetailBean>> list,
                                     @Nullable PageBean<PageListBean<ThemeDetailBean>> pageBean) {
                if (themeDetailBeanPageListBean!=null){
                   ThemeDetailBean bean = themeDetailBeanPageListBean.getData();
                   mHolder.showInfo(bean);
                }
            }
        });
    }

    private void messageList() {
        Map map = new HashMap();
        map.put("pageNo", page);
        map.put("id",id);
        m.topicReplayList(map, new BaseObserver<PageListBean<ThemeReplyEntity>>(this,
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<ThemeReplyEntity>
                                             questionBeanPageListBean, @Nullable
                                             List<PageListBean<ThemeReplyEntity>> list, @Nullable
                                             PageBean<PageListBean<ThemeReplyEntity>> pageBean) {
                if (questionBeanPageListBean != null && questionBeanPageListBean.getList() != null) {
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<ThemeReplyEntity> commentBeans = questionBeanPageListBean.getList();
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
        v.mEmptyLayout.setRetryListener(() -> messageList());
        if (page == 0) {
            v.srl.finishRefresh();
        } else {
            v.srl.finishLoadMore();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == RequestType.REQUEST_REPLY_FORUM){
            v.srl.autoRefresh();
        }
    }
}
