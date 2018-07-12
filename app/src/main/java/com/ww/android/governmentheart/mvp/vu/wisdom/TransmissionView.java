package com.ww.android.governmentheart.mvp.vu.wisdom;

import android.widget.EditText;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.widget.ClearEditText;

import butterknife.BindView;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class TransmissionView extends RefreshView {

    @BindView(R.id.et_title)
    ClearEditText etTitle;

    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_username)
    TextView tvUserName;
    @BindView(R.id.tv_file_picker)
    TextView tvFile;

    public String getTitle() {
        return etTitle.getText().toString();
    }

    public String getContent() {
        return etContent.getText().toString();
    }

    public void setUserName(String str){
        tvUserName.setText(str);
    }

    public void setFileName(String fileName){
        tvFile.setText(fileName);
    }
}
