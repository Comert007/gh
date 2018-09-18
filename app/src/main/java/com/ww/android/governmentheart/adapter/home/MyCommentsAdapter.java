package com.ww.android.governmentheart.adapter.home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.home.CommentBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

public class MyCommentsAdapter extends RvAdapter<CommentBean> {

    public MyCommentsAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_my_comments;
    }

    @Override
    protected RvViewHolder<CommentBean> getViewHolder(int viewType, View view) {
        return new MyCommentsViewHolder(view);
    }


    class MyCommentsViewHolder extends RvViewHolder<CommentBean>{
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_des)
        TextView tvDes;

        public MyCommentsViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, CommentBean bean) {
            tvName.setText(bean.getUser().getName());
            tvTime.setText(bean.getCreateDate());
            tvDes.setText(bean.getContent());
        }
    }
}
