package com.ww.android.governmentheart.mvp.vu.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.work.ThemeDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import ww.com.core.utils.TimeUtils;

public class ThemeContentHeaderHolder {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_view_num)
    TextView tvViewNum;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;

    private Context mContext;
    private View mView;

    public ThemeContentHeaderHolder(Context context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.adapter_theme_content_header,null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mView.setLayoutParams(lp);
        ButterKnife.bind(this,mView);
    }

    public void showInfo(ThemeDetailBean bean){
        tvTitle.setText(bean.getTitle());
        tvTime.setText(TimeUtils.milliseconds2String(bean.getCreateTime()));
        tvViewNum.setText(String.format("%s",bean.getSeeCount()));
        tvCommentNum.setText(String.format("%s",bean.getCommentCount()));
    }

    public View getView() {
        return mView;
    }

}
