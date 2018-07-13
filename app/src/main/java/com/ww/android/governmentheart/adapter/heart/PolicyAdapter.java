package com.ww.android.governmentheart.adapter.heart;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class PolicyAdapter extends RvAdapter<NewsBean> {

    public PolicyAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_policy_core;
    }

    @Override
    protected RvViewHolder<NewsBean> getViewHolder(int viewType, View view) {
        return new PolicyViewHolder(view);
    }

    class PolicyViewHolder extends RvViewHolder<NewsBean> {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_des)
        TextView tvDes;

        public PolicyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {
            ImageLoader.getInstance().displayImage(TextUtils.isEmpty(bean.getImage()) ? "" : bean
                    .getImage(), iv, BaseApplication.getDisplayImageOptions(R.mipmap.img_policy));

            tvNum.setText("1");

            tvTitle.setText(bean.getTitle());
            tvDes.setText(TextUtils.isEmpty(bean.getDescription()) ? "暂无介绍" : bean.getDescription
                    ());
        }
    }
}
