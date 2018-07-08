package com.ww.android.governmentheart.adapter.style;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;
import ww.com.core.widget.RoundImageView;

/**
 * @Author feng
 * @Date 2018/6/17
 */
public class InterviewsAdapter extends RvAdapter<NewsBean> {

    public InterviewsAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_interviews;
    }

    @Override
    protected RvViewHolder<NewsBean> getViewHolder(int viewType, View view) {
        return new InterviewsViewHolder(view);
    }

    class InterviewsViewHolder extends RvViewHolder<NewsBean> {
        @BindView(R.id.riv)
        RoundImageView riv;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_address)
        TextView tvAddress;

        public InterviewsViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {
            ImageLoader.getInstance().displayImage(bean.getImage(), riv, BaseApplication
                    .getDisplayImageOptions(R.mipmap.ic_pic_default));
            tvTitle.setText(bean.getTitle());
            tvDes.setText(bean.getDescription());
            tvTime.setText("时间：");
            tvAddress.setText("地点：");
        }
    }
}
