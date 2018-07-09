package com.ww.android.governmentheart.widget.dialog;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.ww.android.governmentheart.R;

/**
 * @author feng
 * @Date 2018/7/9.
 */
public class EditTextDialog extends BaseBottomDialog {

    private EditText mEditText;

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_edit_text;
    }

    @Override
    public void bindView(View v) {
        mEditText = (EditText) v.findViewById(R.id.edit_text);
        mEditText.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm =
                        (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(mEditText, 0);
            }
        });
    }

    @Override
    public float getDimAmount() {
        return 0.9f;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
