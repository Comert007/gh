package com.ww.android.governmentheart.mvp.vu.wisdom;

import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.vu.RefreshView;

import butterknife.BindView;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class ShowQuestionView extends RefreshView {

    @BindView(R.id.tv_title_name)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_reply)
    TextView tvReply;


    public void setTitle(String title) {
        this.tvTitle.setText(title);
    }

    public void setContent(String content) {
        this.tvContent.setText(content);
    }

    public void setReply(String reply) {
        this.tvReply.setText(reply);
    }
}
