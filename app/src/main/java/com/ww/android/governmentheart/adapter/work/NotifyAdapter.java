package com.ww.android.governmentheart.adapter.work;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.work.ReceptionActivity;
import com.ww.android.governmentheart.mvp.bean.work.ReceptionEntity;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

public class NotifyAdapter extends RvAdapter<ReceptionEntity>{

    public NotifyAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_notify;
    }

    @Override
    protected RvViewHolder<ReceptionEntity> getViewHolder(int viewType, View view) {
        return new NotifyViewHolder(view);

    }

    class NotifyViewHolder extends RvViewHolder<ReceptionEntity>{
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_split)
        TextView tvSplit;


        public NotifyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, ReceptionEntity bean) {
//            ivImage.setVisibility(bean.isSelf()?View.INVISIBLE:View.VISIBLE);
            tvTitle.setText(bean.getTitle());
            tvContent.setText(bean.getCreateDate());
            tvSplit.setText(String.format("%s/%s", bean.getReadNum(), bean.getReadNum() + bean
                    .getUnReadNum()));

            itemView.setOnClickListener(v -> ReceptionActivity.start(getContext(),bean.getId()));
        }
    }
}
