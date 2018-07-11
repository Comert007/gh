package com.ww.android.governmentheart.adapter.together;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.together.OnlineBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class OnlineAdapter extends RvAdapter<OnlineBean> {

    public OnlineAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_online;
    }

    @Override
    protected RvViewHolder<OnlineBean> getViewHolder(int viewType, View view) {
        return new PublishViewHolder(view);
    }

    class PublishViewHolder extends RvViewHolder<OnlineBean> {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_title_name)
        TextView tvTitleName;
        @BindView(R.id.tv_eyes)
        TextView tvEyes;
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.comment_container)
        LinearLayout commentContainer;
        @BindView(R.id.container)
        LinearLayout container;

        public PublishViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, OnlineBean bean) {
            ImageLoader.getInstance().displayImage(bean.getImage(), iv, BaseApplication
                    .getDisplayImageOptions(R.mipmap.ic_pic_default));
            tvTitleName.setText(bean.getTitle());
            tvEyes.setText(String.format("%d", bean.getViewNum()));
            tvComment.setText(String.format("%d", bean.getCommentNum()));
            tvTime.setText(bean.getCreateDate());

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转视频直播页面
                }
            });
        }
    }
}
