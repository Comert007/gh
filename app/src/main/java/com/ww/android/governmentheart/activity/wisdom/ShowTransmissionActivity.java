package com.ww.android.governmentheart.activity.wisdom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.activity.base.OnLineReadActivity;
import com.ww.android.governmentheart.adapter.wisdom.ShowImageAdapter;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.ImagePickBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionDetailBean;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.wisdom.ShowTransmissionView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class ShowTransmissionActivity extends BaseActivity<ShowTransmissionView, WisdomModel> {

    private ShowImageAdapter adapter;
    private String id;
    private ImagePickBean file;

    public static void start(Context context, String id) {
        Intent intent = new Intent(context, ShowTransmissionActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_show_transmission;
    }

    @Override
    protected void init() {
        id = getIntent().getStringExtra("id");
        initListener();
        initRecycler();
        materialDetail();
    }

    @Override
    public void onTitleLeft() {
        super.onTitleLeft();
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

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }


    @OnClick(R.id.tv_file_picker)
    public void onClick() {
        OnLineReadActivity.start(this, file.name, file.path);
    }

    /**
     * 详情
     */
    private void materialDetail() {
        Map map = new HashMap();
        map.put("id", id);
        m.materialDetail(map, new BaseObserver<PageListBean<TransmissionDetailBean>>(this,
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<TransmissionDetailBean>
                                             transmissionDetailBeanPageListBean, @Nullable
                                             List<PageListBean<TransmissionDetailBean>> list,
                                     @Nullable PageBean<PageListBean<TransmissionDetailBean>>
                                             page) {

                if (transmissionDetailBeanPageListBean != null &&
                        transmissionDetailBeanPageListBean.getData() != null) {
                    TransmissionDetailBean transmissionDetailBean =
                            transmissionDetailBeanPageListBean.getData();
                    v.setTitle(transmissionDetailBean.getTitle());
                    v.setContent(transmissionDetailBean.getSummary());
                    List<ImagePickBean> imagePickBeans = transmissionDetailBean.getFiles();
                    if (imagePickBeans != null && imagePickBeans.size() > 0) {
                        List<ImagePickBean> imgs = new ArrayList<>();
                        List<ImagePickBean> docs = new ArrayList<>();
                        for (ImagePickBean imagePickBean : imagePickBeans) {
                            if (imagePickBean.suffix.endsWith(".png") || imagePickBean.suffix
                                    .endsWith(".bmp") || imagePickBean.suffix.endsWith(".jpeg")
                                    || imagePickBean.suffix.endsWith(".gif")) {
                                imgs.add(imagePickBean);
                            } else {
                                docs.add(imagePickBean);
                            }
                        }
                        adapter.addList(imgs);
                        if (docs.size() > 0) {
                            file = docs.get(0);
                            v.setFileName(file.name);
                        }
                    }

                }
            }
        });
    }
}
