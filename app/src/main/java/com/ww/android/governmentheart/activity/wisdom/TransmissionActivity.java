package com.ww.android.governmentheart.activity.wisdom;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.vincent.filepicker.Constant;
import com.vincent.filepicker.activity.NormalFilePickActivity;
import com.vincent.filepicker.filter.entity.NormalFile;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.wisdom.ImagePickAdapter;
import com.ww.android.governmentheart.config.type.CodeType;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.ContactBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.ImagePickBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.RequestFileBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.RequestUserBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.UploadBean;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.wisdom.TransmissionView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.JsonParse;
import com.ww.android.governmentheart.utils.RecyclerHelper;
import com.ww.android.governmentheart.utils.permission.CustomPermissionCallback;
import com.ww.android.governmentheart.utils.permission.PermissionHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;
import me.weyye.hipermission.PermissionItem;
import ww.com.core.Debug;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class TransmissionActivity extends BaseActivity<TransmissionView, WisdomModel> {

    private ImagePickAdapter adapter;
    private ContactBean contactBean;
    private ArrayList<NormalFile> mFiles;

    public static void start(Context context) {
        Intent intent = new Intent(context, TransmissionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_transmossion;
    }

    @Override
    protected void init() {
        initListener();
        initRecycler();
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

    @OnClick({R.id.tv_username, R.id.tv_file_picker, R.id.btn_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_username:
                ChooseContactActivity.start(this);
                break;
            case R.id.tv_file_picker:
                filePicker();
                break;
            case R.id.btn_commit:
                upLoad(createRequestFiles());
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Debug.d("data is null"+(data ==null));
        if (data!=null && requestCode == CodeType.REQUEST_CHOOSE_CONTACT && resultCode == CodeType
                .RESULT_CHOOSE_CONTACT) {
            contactBean = (ContactBean) data.getSerializableExtra("contact");
            v.setUserName(contactBean.getName());
        } else if (data!=null && requestCode == Constant.REQUEST_CODE_PICK_FILE && resultCode == RESULT_OK) {
            mFiles = data.getParcelableArrayListExtra(Constant
                    .RESULT_PICK_FILE);
            v.setFileName(mFiles.get(0).getName()+parseFormat(mFiles.get(0).getPath()));
        } else {
            adapter.onActivityResult(requestCode, requestCode, data);
        }
    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }


    private List<RequestFileBean> createRequestFiles() {
        List<RequestFileBean> requestFiles = new ArrayList<>();
        List<ImagePickBean> imagePickBeans = adapter.getList();
        if (imagePickBeans != null && imagePickBeans.size() > 0) {
            for (ImagePickBean imagePickBean : imagePickBeans) {
                if (imagePickBean.getItemType() == ImagePickBean.MULTIPLE_ACTUAL_IMAGE) {
                    requestFiles.add(new RequestFileBean(imagePickBean.path, JsonParse
                            .MEDIA_IMAGE_TYPE));
                }
            }
        }

        if (mFiles != null && mFiles.size() > 0) {
            for (NormalFile file : mFiles) {
                requestFiles.add(new RequestFileBean(file.getPath(), file.getMimeType()));
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

        if (contactBean == null) {
            showToast("请选择联系人");
            return;
        }

        if (files == null || files.size() == 0) {
            saveTransmission(null);
            return;
        }

        showLoading();

        m.uploadFiles(files, new BaseObserver<PageListBean<UploadBean>>(this, bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<UploadBean> uploadBeanPageListBean,
                                     @Nullable List<PageListBean<UploadBean>> list, @Nullable
                                             PageBean<PageListBean<UploadBean>> page) {

                if (uploadBeanPageListBean != null && uploadBeanPageListBean.getList() != null) {
                    saveTransmission(uploadBeanPageListBean.getList());
                }

            }

            @Override
            protected void onFailure() {
                super.onFailure();
                cancelLoading();
            }
        });
    }


    private void saveTransmission(@Nullable List<UploadBean> files) {
        Map map = new HashMap();

        map.put("title", v.getTitle());
        map.put("cont", v.getContent());
        map.put("users", Arrays.asList(new RequestUserBean(contactBean.getId(), contactBean
                .getName(), contactBean.getUserType())));
        if (files != null) {
            map.put("files", files);
        }
        m.saveMaterial(map, new BaseObserver<String>(this, bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable String s, @Nullable List<String> list, @Nullable
                    PageBean<String> page) {
                cancelLoading();
                CommitSuccessActivity.start(TransmissionActivity.this);
                finish();
            }

            @Override
            protected void onFailure() {
                super.onFailure();
                cancelLoading();
            }
        });
    }

    private void filePicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<PermissionItem> permissions = new ArrayList<>();
            permissions.add(new PermissionItem(Manifest.permission.CAMERA, "相机", R
                    .drawable.permission_ic_camera));
            permissions.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储", R
                    .drawable.permission_ic_storage));
            PermissionHelper.startMultiPermission(this, permissions, new CustomPermissionCallback
                    () {
                @Override
                public void onFinish() {
                    super.onFinish();
                    startFileSelected();
                }
            });
        } else {
            startFileSelected();
        }

    }

    private void startFileSelected() {
        Intent intent = new Intent(this, NormalFilePickActivity.class);
        intent.putExtra(Constant.MAX_NUMBER, 1);
        intent.putExtra(NormalFilePickActivity.SUFFIX, new String[]{"xlsx", "xls", "doc", "docx",
                "ppt", "pptx", "pdf"});
        startActivityForResult(intent, Constant.REQUEST_CODE_PICK_FILE);
    }
}
