package com.ww.android.governmentheart.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ww.android.governmentheart.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingDialog extends Dialog {

    @BindView(R.id.tv_message)
    TextView tvMessage;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.CustomerDialogStyle);

        View loadingView = LayoutInflater.from(context).inflate(R.layout.layout_loading_view,
                null);
        setContentView(loadingView);
        ButterKnife.bind(this, loadingView);
    }

    public void setMessage(String  message){
        tvMessage.setVisibility(TextUtils.isEmpty(message)?View.GONE:View.VISIBLE);
        tvMessage.setText(message);
    }

}
