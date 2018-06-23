package com.ww.android.governmentheart.adapter.wisdom;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.wisdom.ImagePickBean;
import com.ww.android.governmentheart.utils.permission.CustomPermissionCallback;
import com.ww.android.governmentheart.utils.permission.OwnerImageLoader;
import com.ww.android.governmentheart.utils.permission.PermissionHelper;

import java.util.ArrayList;

import butterknife.BindView;
import me.weyye.hipermission.PermissionItem;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class ImagePickAdapter extends RvAdapter<ImagePickBean> {

    private Activity activity;
    private final int IMAGE_PICKER = 0x1;

    public ImagePickAdapter(Activity context) {
        super(context);
        this.activity = context;
        initImagePicker();
    }

    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getItemType();
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        if (ImagePickBean.MULTIPLE_DEFAULT_IMAGE == viewType) {
            return R.layout.adapter_default_image;
        } else {
            return R.layout.adapter_actual_image;
        }
    }

    @Override
    protected RvViewHolder<ImagePickBean> getViewHolder(int viewType, View view) {
        if (ImagePickBean.MULTIPLE_DEFAULT_IMAGE == viewType) {
            return new DefaultViewHolder(view);
        } else {
            return new AdviceViewHolder(view);
        }
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new OwnerImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(true);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(5);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setFocusWidth(800);
        imagePicker.setFocusHeight(800);
        imagePicker.setOutPutX(1000);
        imagePicker.setOutPutY(1000);
    }

    class DefaultViewHolder extends RvViewHolder<ImagePickBean> {
        @BindView(R.id.linear_default)
        LinearLayout linearDefault;

        public DefaultViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, ImagePickBean bean) {
            linearDefault.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        PermissionHelper.startSinglePermission(getContext(), new PermissionItem
                                        (Manifest.permission.CAMERA, "相机", R.drawable
                                                .permission_ic_camera),
                                new CustomPermissionCallback() {
                                    @Override
                                    public void onFinish() {
                                        super.onFinish();
                                        startCameraOrPhoto();
                                    }
                                });
                    } else {
                        startCameraOrPhoto();
                    }
                }
            });
        }
    }


    class AdviceViewHolder extends RvViewHolder<ImagePickBean> {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.iv_close)
        ImageView close;

        public AdviceViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, ImagePickBean bean) {
            ImageLoader.getInstance()
                    .displayImage(ImageDownloader.Scheme.FILE.wrap(bean.path) ,iv, BaseApplication
                            .getDisplayImageOptions(R.mipmap.ic_pic_default));
            close.setOnClickListener(v -> {
                int total = getList().size();
                if (total == 5 && (getList().get(total-1)).getItemType()!=ImagePickBean.MULTIPLE_DEFAULT_IMAGE) {
                    getList().add(new ImagePickBean(ImagePickBean.MULTIPLE_DEFAULT_IMAGE));
                }
                getList().remove(position);
                notifyDataSetChanged();
            });
        }
    }

    private void startCameraOrPhoto() {
        Intent intent = new Intent(getContext(), ImageGridActivity.class);
        activity.startActivityForResult(intent, IMAGE_PICKER);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == IMAGE_PICKER && data != null) {
            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra
                    (ImagePicker.EXTRA_RESULT_ITEMS);
            int leftSize = 6 - getList().size();
            for (int i = 0; i < (leftSize < images.size() ? leftSize : images.size()); i++) {
                ImagePickBean image = new ImagePickBean(ImagePickBean.MULTIPLE_ACTUAL_IMAGE);
                image.path = images.get(i).path;
                getList().add(getList().size() - 1, image);
            }
            if (getList().size() == 6) {
                getList().remove(5);
            }
            notifyDataSetChanged();
        }
    }

}
