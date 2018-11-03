package com.ww.android.governmentheart.mvp.vu.work;

import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.vu.RefreshView;

import butterknife.BindView;

public class ReceptionView extends RefreshView {

    @BindView(R.id.tv_title_name)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_file_picker)
    TextView tvFileName;
    @BindView(R.id.tv_username)
    TextView tvUserName;


    public void setTitle(String title) {
        this.tvTitle.setText(title);
    }

    public void setContent(String content) {
        this.tvContent.setText(content);
    }

    public void setFileName(String name) {
        this.tvFileName.setText(name);
    }

    public void setUserName(String username) {
        this.tvUserName.setText(username);
    }

}
