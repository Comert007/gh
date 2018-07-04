package com.ww.android.governmentheart.network;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.activity.home.LoginActivity;
import com.ww.android.governmentheart.config.Constant;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.network.exception.ApiException;
import com.ww.android.governmentheart.utils.ToastUtils;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author feng
 * @Date 2018/7/4.
 */
public abstract class BaseObserver<T> implements Observer<ResponseBean<T>> {

    private static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    private static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String UNKNOWN_HOST_EXCEPTION = "网络异常，请检查您的网络状态";
    private static final String STATUS_EXCEPTION = "状态异常";
    private static final String CONVERT_EXCEPTION = "网络数据转换失败";

    private Context context;
    private LifecycleTransformer<ResponseBean<T>> transformer;

    public BaseObserver(Context context) {
        this.context = context;
    }

    public BaseObserver(@NonNull Context context, @NonNull LifecycleTransformer<ResponseBean<T>> transformer) {
        this.context = context;
        this.transformer = transformer;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof ApiException) {
            String status = ((ApiException) e).getStatus();
            if (Constant.TOKEN_INVALID.equals(status)) {
                BaseApplication.getInstance().clearTopTask((Activity) context);
                LoginActivity.launch(context);
                ((Activity) context).finish();
            } else {
                resultError(e);
            }
        } else {
            resultError(e);
        }
    }

    @Override
    public void onNext(ResponseBean<T> responseBean) {
        if (responseBean != null) {
            if (responseBean.getStatus().equals(Constant.STATUS_OK)) {
                resultSuccess(responseBean);
            } else {
                throw new ApiException(responseBean.getStatus(), responseBean.getMsg());
            }
        } else {
            throw new ApiException(ApiException.UNKNOWN_HOST_CODE, UNKNOWN_HOST_EXCEPTION);
        }
    }

    private void resultError(Throwable e) {
        ToastUtils.showToast(e.getMessage());
    }

    private void resultSuccess(ResponseBean<T> responseBean) {
        onSuccess(responseBean.getData(),responseBean.getList(),responseBean.getPage());
    }


    protected abstract void onSuccess(@Nullable T t,@Nullable  List<T> list,@Nullable  PageBean<T> page);


    public LifecycleTransformer<ResponseBean<T>> getTransformer() {
        return transformer;
    }
}
