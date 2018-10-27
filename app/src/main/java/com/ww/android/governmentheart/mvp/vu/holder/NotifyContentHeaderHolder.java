package com.ww.android.governmentheart.mvp.vu.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.work.NotifyEntity;
import com.ww.android.governmentheart.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotifyContentHeaderHolder {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private Context mContext;
    private View mView;

    public NotifyContentHeaderHolder(Context context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.adapter_header_notify_content,null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mView.setLayoutParams(lp);
        ButterKnife.bind(this,mView);
    }

    public void showInfo(NotifyEntity notifyEntity){
        tvTitle.setText(notifyEntity.getTitle());
        tvTime.setText(notifyEntity.getCreateDate());
        tvContent.setText(notifyEntity.getContent());
    }

    @OnClick(R.id.ll_file_layout)
    public void onClick(){
        ToastUtils.showToast("当前功能待开发");
    }

    public View getView() {
        return mView;
    }
}
