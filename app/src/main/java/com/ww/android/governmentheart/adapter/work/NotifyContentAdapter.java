package com.ww.android.governmentheart.adapter.work;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.config.Constant;
import com.ww.android.governmentheart.mvp.bean.work.NotifyContentEntity;

import butterknife.BindColor;
import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;
import ww.com.core.utils.TimeUtils;

public class NotifyContentAdapter extends RvAdapter<NotifyContentEntity>{

    public NotifyContentAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_notify_content;
    }

    @Override
    protected RvViewHolder<NotifyContentEntity> getViewHolder(int viewType, View view) {
        return new NotifyContentViewHolder(view);
    }

    class NotifyContentViewHolder extends RvViewHolder<NotifyContentEntity>{
        @BindView(R.id.ll_notify_layout)
        LinearLayout notifyLayout;
        @BindView(R.id.tv_username)
        TextView tvUserName;
        @BindView(R.id.tv_department)
        TextView tvDepartment;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.ll_time_layout)
        LinearLayout timeLayout;


        @BindColor(R.color.color_bg)
        int colorGray;
        @BindColor(R.color.color_white)
        int colorWhite;

        public NotifyContentViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NotifyContentEntity bean) {
            tvUserName.setText(bean.getUser().getName());
            tvDepartment.setText(bean.getOaNotify().getOaNotifyRecordNames());
            notifyLayout.setBackgroundColor(position%2 ==0?colorGray:colorWhite);
            boolean isRead = Constant.STATUS_OK.equals(bean.getReadFlag());
            tvStatus.setText(isRead?"已读":"未读");
            tvStatus.setSelected(!isRead);
            timeLayout.setVisibility(isRead?View.VISIBLE:View.GONE);
            tvTime.setText(TimeUtils.milliseconds2String(bean.getReadDate()));
        }
    }
}
