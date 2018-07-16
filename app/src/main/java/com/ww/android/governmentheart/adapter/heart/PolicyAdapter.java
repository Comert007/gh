package com.ww.android.governmentheart.adapter.heart;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.heart.PolicyContentActivity;
import com.ww.android.governmentheart.mvp.bean.login.NewsChildTypeBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class PolicyAdapter extends RvAdapter<NewsChildTypeBean> {

    public PolicyAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_policy_core;
    }

    @Override
    protected RvViewHolder<NewsChildTypeBean> getViewHolder(int viewType, View view) {
        return new PolicyViewHolder(view);
    }

    class PolicyViewHolder extends RvViewHolder<NewsChildTypeBean> {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.container)
        LinearLayout container;

        public PolicyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsChildTypeBean bean) {
            ImageLoader.getInstance().displayImage(TextUtils.isEmpty(bean.getImage()) ? "" : bean
                    .getImage(), iv, BaseApplication.getDisplayImageOptions(R.mipmap.img_policy));

            tvNum.setText(bean.getCount());
            tvTitle.setText(bean.getName());
            tvDes.setText(TextUtils.isEmpty(bean.getDescription()) ? "暂无介绍" : bean.getDescription
                    ());
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PolicyContentActivity.start(getContext(),bean.getId());
                }
            });
        }
    }
}
