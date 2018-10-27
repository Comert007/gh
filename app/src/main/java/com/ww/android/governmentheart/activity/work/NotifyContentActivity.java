package com.ww.android.governmentheart.activity.work;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.work.NotifyContentAdapter;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.work.NotifyContentEntity;
import com.ww.android.governmentheart.mvp.bean.work.NotifyEntity;
import com.ww.android.governmentheart.mvp.model.work.WorkModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.mvp.vu.holder.NotifyContentHeaderHolder;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("Registered")
public class NotifyContentActivity extends BaseActivity<RefreshView,WorkModel> {

    private NotifyContentAdapter adapter;
    private int page=0;
    private String id;

    private NotifyContentHeaderHolder mHolder;

    public static void start(Context context,String id) {
        Intent starter = new Intent(context, NotifyContentActivity.class);
        starter.putExtra("id",id);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_nofity_content;
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    @Override
    protected void init() {
        id = getIntent().getStringExtra("id");
        mHolder = new NotifyContentHeaderHolder(this);
        initData();
    }

    private void initData(){

        initListener();
        initRecycler();
        v.srl.autoRefresh();
    }


    private void initListener() {
        if (v.srl == null) {
            return;
        }
        v.setRefreshType(RefreshType.REFRESH);

        v.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                messageList();
            }
        });

    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(this));
        adapter = new NotifyContentAdapter(this);

        v.crv.setAdapter(adapter);
        v.crv.addHeadView(mHolder.getView());
    }

    private void messageList() {
        Map map = new HashMap();
        map.put("id", id);
        m.notifyDetail(map, new BaseObserver<PageListBean<NotifyEntity>>(this,bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<NotifyEntity> notifyEntityPageListBean,
                                     @Nullable List<PageListBean<NotifyEntity>> list,
                                     @Nullable PageBean<PageListBean<NotifyEntity>> pageBean) {
                if (notifyEntityPageListBean != null) {
                    NotifyEntity  notifyEntity = notifyEntityPageListBean.getData();

                    if (notifyEntity!=null){
                        v.srl.finishRefresh();
                        v.loadStatus(EmptyLayout.STATUS_HIDE);
                        mHolder.showInfo(notifyEntity);
                        List<NotifyContentEntity> entities = notifyEntity.getOaNotifyRecordList();
                        adapter.addList(entities);

                    }else {
                        reload(EmptyLayout.STATUS_NO_DATA);
                    }
                }else {
                    reload(EmptyLayout.STATUS_NO_NET);
                }

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
                messageList();
            }
        });
        if (page == 0) {
            v.srl.finishRefresh();
        } else {
            v.srl.finishLoadMore();
        }
    }
}
