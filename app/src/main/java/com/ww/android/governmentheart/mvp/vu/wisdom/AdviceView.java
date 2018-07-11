package com.ww.android.governmentheart.mvp.vu.wisdom;

import android.widget.EditText;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.widget.ClearEditText;

import butterknife.BindView;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class AdviceView extends RefreshView {

    @BindView(R.id.et_title)
    ClearEditText etTitle;

    @BindView(R.id.et_content)
    EditText etContent;

    public String getTitle() {
        return etTitle.getText().toString();
    }

    public String getContent() {
        return etContent.getText().toString();
    }
}
