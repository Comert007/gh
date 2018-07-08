package com.ww.android.governmentheart.adapter.wisdom;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.wisdom.ImagePickBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class ShowImageAdapter extends RvAdapter<ImagePickBean> {

    private Activity activity;

    public ShowImageAdapter(Activity context) {
        super(context);
        this.activity = context;
    }

    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getItemType();
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_actual_image;
    }

    @Override
    protected RvViewHolder<ImagePickBean> getViewHolder(int viewType, View view) {
        return new ShowAdviceViewHolder(view);
    }


    class ShowAdviceViewHolder extends RvViewHolder<ImagePickBean> {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.iv_close)
        ImageView close;

        public ShowAdviceViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, ImagePickBean bean) {
            ImageLoader.getInstance()
                    .displayImage(ImageDownloader.Scheme.FILE.wrap(bean.path) ,iv, BaseApplication
                            .getDisplayImageOptions(R.mipmap.ic_pic_default));
            close.setVisibility(View.INVISIBLE);
        }
    }


}
