package com.ww.android.governmentheart.activity.work;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.wisdom.ShowImageAdapter;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.ImagePickBean;
import com.ww.android.governmentheart.mvp.bean.work.ReceptionEntity;
import com.ww.android.governmentheart.mvp.model.work.WorkModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.work.ReceptionView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceptionActivity extends BaseActivity<ReceptionView,WorkModel>{

    private String id;
    private ShowImageAdapter adapter;
    private ImagePickBean file;


    public static void start(Context context, String id) {
        Intent starter = new Intent(context, ReceptionActivity.class);
        starter.putExtra("id",id);
        context.startActivity(starter);
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_reception;
    }

    @Override
    protected void init() {
        id = getIntent().getStringExtra("id");
        initListener();
        initRecycler();
        materialDetail();
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    private void initListener() {
        if (v.srl == null) {
            return;
        }
        v.setRefreshType(RefreshType.NOT_ENABLE);
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.gridManager(this, 4));
        adapter = new ShowImageAdapter(this);
        v.crv.setAdapter(adapter);
    }


    private void materialDetail() {
        Map map = new HashMap();
        map.put("id", id);
        m.receiveMaterial(map, new BaseObserver<PageListBean<ReceptionEntity>>(this,
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<ReceptionEntity>
                                             transmissionDetailBeanPageListBean, @Nullable
                                             List<PageListBean<ReceptionEntity>> list,
                                     @Nullable PageBean<PageListBean<ReceptionEntity>>
                                             page) {

                if (transmissionDetailBeanPageListBean != null &&
                        transmissionDetailBeanPageListBean.getData() != null) {
                    ReceptionEntity transmissionDetailBean =
                            transmissionDetailBeanPageListBean.getData();
                    v.setTitle(transmissionDetailBean.getTitle());
                    v.setContent(transmissionDetailBean.getSummary());
//                    if (transmissionDetailBean.get()!=null && transmissionDetailBean.getUsers().size()>0){
//                        v.setUserName(transmissionDetailBean.getUsers().get(0).getName());
//                    }
//                    List<ImagePickBean> imagePickBeans = transmissionDetailBean.getFiles();
//                    if (imagePickBeans != null && imagePickBeans.size() > 0) {
//                        List<ImagePickBean> imgs = new ArrayList<>();
//                        List<ImagePickBean> docs = new ArrayList<>();
//                        for (ImagePickBean imagePickBean : imagePickBeans) {
//                            if (imagePickBean.suffix.endsWith(".png") || imagePickBean.suffix
//                                    .endsWith(".bmp") || imagePickBean.suffix.endsWith(".jpeg")
//                                    || imagePickBean.suffix.endsWith(".gif")) {
//                                imgs.add(imagePickBean);
//                            } else {
//                                docs.add(imagePickBean);
//                            }
//                        }
//                        adapter.addList(imgs);
//                        if (docs.size() > 0) {
//                            file = docs.get(0);
//                            v.setFileName(file.name);
//                        }
//                    }
//
                }
            }
        });
    }


}
