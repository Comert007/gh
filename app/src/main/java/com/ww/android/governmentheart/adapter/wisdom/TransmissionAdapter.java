package com.ww.android.governmentheart.adapter.wisdom;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.wisdom.DataTransmissionActivity;
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
        @BindView(R.id.container)
        LinearLayout container;
        @BindView(R.id.tv_num)
        TextView tvNum;


        public TransmissionViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, TransmissionBean bean) {
            tvTitle.setText(bean.getTitle());
            tvTime.setText(bean.getCreateDate());
            tvNum.setText(String.format("%s/%s", bean.getReadNum(), bean.getUnReadNum()));
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataTransmissionActivity.start(getContext(),bean.getId(),1);
                }
            });
        }
    }
}
