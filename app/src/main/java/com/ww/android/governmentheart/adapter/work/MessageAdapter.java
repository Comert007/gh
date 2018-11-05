package com.ww.android.governmentheart.adapter.work;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.wisdom.DataTransmissionActivity;
import com.ww.android.governmentheart.mvp.bean.work.MessageEntity;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

public class MessageAdapter extends RvAdapter<MessageEntity>{

    public MessageAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_message;
    }

    @Override
    protected RvViewHolder<MessageEntity> getViewHolder(int viewType, View view) {
        return new MessageViewHolder(view);
    }

    class MessageViewHolder extends RvViewHolder<MessageEntity>{
        @BindView(R.id.iv_image)
        ImageView ivImage;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;


        public MessageViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, MessageEntity bean) {
            ivImage.setImageResource(position%2 ==0?R.mipmap.ic_img_message_blue:R.mipmap.ic_img_message_yellow);
            tvTitle.setText(bean.getTitle());
            tvContent.setText(bean.getCreateDate());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataTransmissionActivity.start(getContext(),bean.getDataId(),0);
//                    EasyRequestBean easyRequestBean = new EasyRequestBean.Builder()
//                            .setId(bean.getDataId())
//                            .setName(bean.getTitle())
//                            .setType(bean.getMenuCode()).build();
//                    WebViewActivity.start(getContext(),easyRequestBean);
                }
            });
        }
    }

}
