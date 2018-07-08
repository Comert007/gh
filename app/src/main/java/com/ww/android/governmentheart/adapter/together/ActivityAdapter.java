package com.ww.android.governmentheart.adapter.together;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.together.ActBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class ActivityAdapter extends RvAdapter<ActBean> {

    public ActivityAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_activity;
    }

    @Override
    protected RvViewHolder<ActBean> getViewHolder(int viewType, View view) {
        return new PublishViewHolder(view);
    }

    class PublishViewHolder extends RvViewHolder<ActBean> {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_time_delay)
        TextView tvTimeDelay;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_origin_name)
        TextView tvOriginName;

        public PublishViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, ActBean bean) {
            ImageLoader.getInstance().displayImage(bean.getUrl(), iv, BaseApplication
                    .getDisplayImageOptions(R.mipmap.ic_pic_default));

            tvTitle.setText(bean.getTitle());
//            tvTimeDelay.setText("还有"+bean.getDate()+"天开始");
            tvTime.setText("时间："+bean.getDate());
//            tvOriginName.setText(bean.get);
        }
    }
}
