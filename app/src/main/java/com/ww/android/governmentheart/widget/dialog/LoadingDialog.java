package com.ww.android.governmentheart.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wang.avi.AVLoadingIndicatorView;
import com.ww.android.governmentheart.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadingDialog extends Dialog {

    @BindView(R.id.loading_view)
    AVLoadingIndicatorView loadingView;
    @BindView(R.id.tv_message)
    TextView tvMessage;

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.CustomTheme_Dialog);

        View loadingView = LayoutInflater.from(context).inflate(R.layout.layout_loading_view,
                null);
        ButterKnife.bind(this, loadingView);
    }


    public void setMessage(String  message){
        tvMessage.setText(message);
    }


    @Override
    public void show() {
        start();
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        stop();
    }

    private void start() {
        if (loadingView!=null){
            loadingView.show();
        }
    }

    private void stop(){
        if (loadingView !=null){
            loadingView.hide();
        }
    }

}
