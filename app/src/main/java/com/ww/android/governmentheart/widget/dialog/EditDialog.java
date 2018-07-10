package com.ww.android.governmentheart.widget.dialog;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditDialog extends BaseDialog implements View.OnClickListener {

    @BindView(R.id.edit_text)
    EditText mEditText;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_commit)
    TextView tvCommit;
    @BindView(R.id.txt_nums)
    TextView txtNums;

    Context context;
    private TextView contView;
    private int maxlines;
    private int emsNums = 1000;
    private EditDialogClickInterface clickInterface;
    private boolean hasNums;


    public void setNums(boolean hasNums, int emsNums) {
        this.emsNums = emsNums;
        this.hasNums = hasNums;
        if (hasNums) {
            txtNums.setText((emsNums - mEditText.getText().toString().length()) + "");
        } else {
            txtNums.setVisibility(View.GONE);
        }
        mEditText.addTextChangedListener(watcher);
    }

    public EditDialog(Context context, int maxlines) {
        super(context, R.style.EditDialog);
        this.context = context;
        this.maxlines = maxlines;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit_text, null);
        ButterKnife.bind(this, mView);
        txtNums = (TextView) mView.findViewById(R.id.txt_nums);
        mEditText.setMaxLines(maxlines);
        mEditText.setMinLines(maxlines);
        mEditText.setOnClickListener(this);
        super.setContentView(mView);
        getWindow().setWindowAnimations(R.style.BottomDialog_AnimationStyle);
    }

    public EditDialog setParms(TextView contView, String s) {
        this.contView = contView;
        if (contView.getText().toString() != null) {
            mEditText.setText(contView.getText().toString());
            mEditText.setSelection(mEditText.getText().length());
        }
        mEditText.setHint(s);

        return this;
    }

    public EditDialog setParms(TextView contView, String s, int maxLength) {
        this.contView = contView;
        if (contView.getText().toString() != null) {
            mEditText.setText(contView.getText().toString());
            mEditText.setSelection(mEditText.getText().length());
        }
        mEditText.setHint(s);
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        return this;
    }

    public EditDialog setParms(TextView contView, String s, int maxLength, boolean isNumber) {
        this.contView = contView;
        if (contView.getText().toString() != null) {
            if (isNumber)
                mEditText.setInputType(InputType.TYPE_CLASS_PHONE);
            mEditText.setText(contView.getText().toString());
            mEditText.setSelection(mEditText.getText().length());
        }
        mEditText.setHint(s);
        mEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
        return this;
    }


    public void setEdiClickInterface(EditDialogClickInterface ediClickInterface) {
        this.clickInterface = ediClickInterface;
    }

    @OnClick({R.id.tv_commit,R.id.tv_cancel})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_commit:
                String editStr = mEditText.getText().toString();
                if (clickInterface != null) {
                    if (!"".equals(editStr)) {
                        clickInterface.doConfirm(mEditText.getText().toString());
                    }
                } else {
                    contView.setText(editStr);
                }
                this.cancel();
            case R.id.tv_cancel:
                this.cancel();
                break;
        }
    }

    @Override
    public void show() {
        super.show();
        setGravity(Gravity.BOTTOM);
        setSize(DensityUtil.width(), 0);
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            int i = mEditText.getText().toString().length();
            if (i <= 0) {
                tvCommit.setTextColor(ContextCompat.getColor(context, R.color.color_hint));
                if (hasNums)
                    txtNums.setText(emsNums + "");
            } else {
                if (i<5){
                    tvCommit.setTextColor(ContextCompat.getColor(context, R.color.color_hint));
                }else {
                    tvCommit.setTextColor(ContextCompat.getColor(context, R.color
                            .color_start_titleBar));
                }
                if (hasNums)
                    txtNums.setText((emsNums - i) + "");
            }

            tvCommit.setEnabled(i >= 5);
        }
    };

    public interface EditDialogClickInterface {
        void doConfirm(String str);
    }

    @Override
    public void dismiss() {
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context
                .INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getApplicationWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
        super.dismiss();
    }
}