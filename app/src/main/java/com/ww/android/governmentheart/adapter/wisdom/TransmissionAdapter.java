package com.ww.android.governmentheart.adapter.wisdom;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/18
 */
public class TransmissionAdapter extends RvAdapter<TransmissionBean> {

    public TransmissionAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_transmission;
    }

    @Override
    protected RvViewHolder<TransmissionBean> getViewHolder(int viewType, View view) {
        return new TransmissionViewHolder(view);
    }

    class TransmissionViewHolder extends RvViewHolder<TransmissionBean> {

        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;


        public TransmissionViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, TransmissionBean bean) {
            ImageLoader.getInstance().displayImage("", iv, BaseApplication.getDisplayImageOptions
                    (R.mipmap.ic_img_transmission));
            tvTitle.setText(bean.getTitle());
            tvTime.setText(bean.getDate());
        }
    }
}
