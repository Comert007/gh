package com.ww.android.governmentheart.mvp.vu.wisdom;

import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.vu.RefreshView;

import butterknife.BindView;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class ShowTransmissionView extends RefreshView {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;


    public void setTitle(String title) {
        this.tvTitle.setText(title);
    }

    public void setContent(String content) {
        this.tvContent.setText(content);
    }
}
