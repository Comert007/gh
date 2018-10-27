package com.ww.android.governmentheart.fragment.work;

import android.support.annotation.Nullable;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.adapter.work.MessageAdapter;
import com.ww.android.governmentheart.fragment.BaseFragment;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.work.MessageEntity;
import com.ww.android.governmentheart.mvp.model.work.WorkModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageFragment extends BaseFragment<RefreshView, WorkModel> {
    private MessageAdapter adapter;
    private int page;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void init() {
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
            messageList();
        });

        v.srl.setOnLoadMoreListener(refreshLayout -> messageList());
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(getContext()));
        adapter = new MessageAdapter(getContext());
        v.crv.setAdapter(adapter);
    }

    private void messageList() {
        Map map = new HashMap();
        map.put("pageNo", page);
        m.messageList(map, new BaseObserver<PageListBean<MessageEntity>>(getContext(),
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<MessageEntity>
                                             questionBeanPageListBean, @Nullable
                                             List<PageListBean<MessageEntity>> list, @Nullable
                                             PageBean<PageListBean<MessageEntity>> pageBean) {
                if (questionBeanPageListBean != null && questionBeanPageListBean.getList() != null
                        && questionBeanPageListBean.getList().size() >0) {
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<MessageEntity> commentBeans = questionBeanPageListBean.getList();
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
}
