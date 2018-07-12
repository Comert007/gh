package com.ww.android.governmentheart.activity.wisdom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.wisdom.ImagePickAdapter;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.ImagePickBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.RequestFileBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.UploadBean;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.wisdom.AdviceView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.JsonParse;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class AdviceActivity extends BaseActivity<AdviceView, WisdomModel> {

    private ImagePickAdapter adapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, AdviceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_advice;
    }

    @Override
    protected void init() {
        initListener();
        initRecycler();
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
        adapter = new ImagePickAdapter(this);
        v.crv.setAdapter(adapter);
        adapter.addList(Arrays.asList(new ImagePickBean(ImagePickBean.MULTIPLE_DEFAULT_IMAGE)));
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    @OnClick({R.id.btn_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                upLoad(createRequestFiles());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.onActivityResult(requestCode, requestCode, data);
    }


    private List<RequestFileBean> createRequestFiles() {
        List<RequestFileBean> requestFiles = new ArrayList<>();
        List<ImagePickBean> imagePickBeans = adapter.getList();
        if (imagePickBeans != null && imagePickBeans.size() > 0) {
            for (ImagePickBean imagePickBean : imagePickBeans) {
                if (imagePickBean.getItemType() == ImagePickBean.MULTIPLE_ACTUAL_IMAGE) {
                    requestFiles.add(new RequestFileBean(imagePickBean.path, JsonParse.MEDIA_IMAGE_TYPE));
                }
            }
        }
        return requestFiles;
    }


    private void upLoad(List<RequestFileBean> files) {
        if (TextUtils.isEmpty(v.getTitle())) {
            showToast("请输入标题");
            return;
        }

        if (TextUtils.isEmpty(v.getContent())) {
            showToast("请输入内容");
            return;
        }

        if (files == null || files.size() == 0) {
            saveSuggest(null);
            return;
        }

        m.uploadFiles(files, new BaseObserver<PageListBean<UploadBean>>(this, bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<UploadBean> uploadBeanPageListBean,
                                     @Nullable List<PageListBean<UploadBean>> list, @Nullable
                                             PageBean<PageListBean<UploadBean>> page) {

                if (uploadBeanPageListBean != null && uploadBeanPageListBean.getList() != null) {
                    saveSuggest(uploadBeanPageListBean.getList());
                }

            }
        });
    }

    private void saveSuggest(@Nullable List<UploadBean> imgs) {
        Map map = new HashMap();

        map.put("title", v.getContent());
        map.put("cont", v.getContent());
        if (imgs != null) {
            map.put("imgs", imgs);
        }
        m.saveSuggest(map, new BaseObserver<String>(this, bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable String s, @Nullable List<String> list, @Nullable
                    PageBean<String> page) {
                CommitSuccessActivity.start(AdviceActivity.this);
                finish();
            }
        });
    }
}
