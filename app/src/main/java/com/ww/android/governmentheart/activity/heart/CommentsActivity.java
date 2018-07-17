package com.ww.android.governmentheart.activity.heart;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.home.CommentsAdapter;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.home.CommentBean;
import com.ww.android.governmentheart.mvp.bean.home.EasyRequestBean;
import com.ww.android.governmentheart.mvp.model.CommonModel;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;
import com.ww.android.governmentheart.widget.dialog.EditDialog;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import ww.com.core.utils.TimeUtils;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class CommentsActivity extends BaseActivity<RefreshView, CommonModel> {
    @BindView(R.id.ll_container)
    LinearLayout llContainer;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private CommentsAdapter adapter;
    private int page;
    private EasyRequestBean mEasyRequestBean;
    // 需动态修改参数 列表获取id
    private String id;
    // type：1新闻类，2 参政议政，3 知识交流，4 活动，5 直播
    private String type;
    private EditDialog mEditDialog;

    public static void start(Context context, EasyRequestBean easyRequestBean) {
        Intent intent = new Intent(context, CommentsActivity.class);
        intent.putExtra("easyRequestBean", easyRequestBean);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_comments;
    }

    @Override
    protected void init() {
        if (btnTitleRight != null) {
            btnTitleRight.setTextSize(12);
        }
        initData();
        initListener();
        initRecycler();
        v.srl.autoRefresh();
    }

    @Override
    public void onTitleLeft() {
        super.onTitleLeft();
    }

    private void initData() {
        mEasyRequestBean = (EasyRequestBean) getIntent().getSerializableExtra("easyRequestBean");
        id = mEasyRequestBean.id;
        type = mEasyRequestBean.type;
    }

    private void initListener() {
        if (v.srl == null) {
            return;
        }

        v.srl.setOnRefreshListener(refreshLayout -> {
            page = 0;
            comments();
        });

        v.srl.setOnLoadMoreListener(refreshLayout -> comments());
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(this), RecyclerHelper
                .defaultSingleDecoration(this));
        adapter = new CommentsAdapter(this);
        v.crv.setAdapter(adapter);
    }

    @OnClick({R.id.ll_container})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_container:
                llContainer.clearFocus();
                mEditDialog = new EditDialog(this, 7)
                        .setParms(tvContent, "在这里输入您的观点（最少5个字）");
                mEditDialog.setNums(false, 200);
                mEditDialog.setEdiClickInterface(new EditDialog.EditDialogClickInterface() {
                    @Override
                    public void doConfirm(String str) {
                        saveComment(str);
                    }
                });
                mEditDialog.show();
                break;
        }

    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    private void comments() {
        Map map = new HashMap();
        map.put("id", id);
        map.put("pageNo", page);
        map.put("t", type);
        if (page == 0) {
            map.put("date", TimeUtils.date2String(new Date()));
        }

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
                    reload(EmptyLayout.STATUS_NO_NET);
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


    /**
     * 保存评论
     * @param content
     */
    private void saveComment(String content) {
        Map map = new HashMap();
        map.put("id", mEasyRequestBean.id);
        map.put("cont", content);
        map.put("t", mEasyRequestBean.type);
        //需确认这个评论 open id .
//        map.put("openid", content);
        m.saveComment(map, new BaseObserver<String>(this, bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable String s, @Nullable List<String> list, @Nullable
                    PageBean<String> pageBean) {
                ResponseBean<String> responseBean = getResponseBean();
                showToast(TextUtils.isEmpty(responseBean.getMsg())?"评论成功":responseBean.getMsg());
                v.srl.autoRefresh();
                comments();
            }
        });
    }
}
