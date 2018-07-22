package com.ww.android.governmentheart.adapter.home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.home.CommentBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;
import ww.com.core.widget.RoundImageView;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class CommentsAdapter extends RvAdapter<CommentBean> {

    public CommentsAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_comments;
    }

    @Override
    protected RvViewHolder<CommentBean> getViewHolder(int viewType, View view) {
        return new CommentsViewHolder(view);
    }

    class CommentsViewHolder extends RvViewHolder<CommentBean> {

        @BindView(R.id.riv)
        RoundImageView riv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_des)
        TextView tvDes;


        public CommentsViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, CommentBean bean) {
            if (bean!=null && bean.getUser()!=null){
                ImageLoader.getInstance().displayImage(bean.getUser().getPhoto(), riv,BaseApplication
                        .getDisplayImageOptions(R.mipmap.ic_header_default));
                tvName.setText(bean.getUser().getName());
                tvTime.setText(bean.getCreateDate());
                tvDes.setText(bean.getContent());
            }
        }
    }
}
