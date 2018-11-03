package com.ww.android.governmentheart.activity.wisdom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.wisdom.DataTransmissionAdapter;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.PagingBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.ImagePickBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionDetailBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionUserBean;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.mvp.vu.holder.DataTransmissionHeaderHolder;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.widget.EmptyLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataTransmissionActivity extends BaseActivity<RefreshView,WisdomModel> {

    private DataTransmissionAdapter adapter;
    private int page=0;
    private String id;

    private DataTransmissionHeaderHolder mHolder;


    public static void start(Context context, String id) {
        Intent starter = new Intent(context, DataTransmissionActivity.class);
        starter.putExtra("id",id);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_data_transmission;
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    @Override
    protected void init() {
        id = getIntent().getStringExtra("id");
        mHolder = new DataTransmissionHeaderHolder(this);
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
                materialDetail();
                matUsers();
            }
        });

    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(this));
        adapter = new DataTransmissionAdapter(this);

        v.crv.setAdapter(adapter);
        v.crv.addHeadView(mHolder.getView());
    }


    private void matUsers(){
        Map map = new HashMap();
        map.put("id", id);
        m.matUsers(map, new BaseObserver<PageListBean<TransmissionUserBean>>(this,bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<TransmissionUserBean>
                                             transmissionUserBeanPageListBean,
                                     @Nullable List<PageListBean<TransmissionUserBean>> list,
                                     @Nullable PageBean<PageListBean<TransmissionUserBean>> pageBean) {
                if (transmissionUserBeanPageListBean!=null &&transmissionUserBeanPageListBean.getList()!=null){
                    v.loadStatus(EmptyLayout.STATUS_HIDE);
                    List<TransmissionUserBean> commentBeans = transmissionUserBeanPageListBean.getList();
                    PagingBean pagingBean = transmissionUserBeanPageListBean.getPage();
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
        });
    }

    private void materialDetail() {
        Map map = new HashMap();
        map.put("id", id);
        m.materialDetail(map, new BaseObserver<PageListBean<TransmissionDetailBean>>(this,bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<TransmissionDetailBean> notifyEntityPageListBean,
                                     @Nullable List<PageListBean<TransmissionDetailBean>> list,
                                     @Nullable PageBean<PageListBean<TransmissionDetailBean>> pageBean) {
                if (notifyEntityPageListBean != null) {
                    TransmissionDetailBean  notifyEntity = notifyEntityPageListBean.getData();

                    if (notifyEntity!=null){
                        v.srl.finishRefresh();
                        v.loadStatus(EmptyLayout.STATUS_HIDE);

                        List<ImagePickBean> imgs = new ArrayList<>();
                        List<ImagePickBean> docs = new ArrayList<>();
                        mHolder.showInfo(notifyEntity);
                        List<ImagePickBean> imagePickBeans = notifyEntity.getFiles();
                        if (imagePickBeans != null && imagePickBeans.size() > 0) {

                            for (ImagePickBean imagePickBean : imagePickBeans) {
                                if (imagePickBean.suffix.endsWith(".png") || imagePickBean.suffix
                                        .endsWith(".bmp") || imagePickBean.suffix.endsWith(".jpeg")
                                        || imagePickBean.suffix.endsWith(".gif")) {
                                    imgs.add(imagePickBean);
                                } else if (imagePickBean.suffix.endsWith(".doc") ||
                                        imagePickBean.suffix.endsWith(".docx")){
                                    docs.add(imagePickBean);
                                }else {
//                                    ToastUtils.showToast("文件不是word或者图片");
                                }
                            }
                        }

                        mHolder.showFile(docs,imgs);

                    }
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
                matUsers();
            }
        });
        if (page == 0) {
            v.srl.finishRefresh();
        } else {
            v.srl.finishLoadMore();
        }
    }
}
