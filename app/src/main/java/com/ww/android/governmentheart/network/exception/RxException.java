package com.ww.android.governmentheart.network.exception;

import com.ww.android.governmentheart.utils.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.functions.Consumer;

public class RxException<T extends Throwable> implements Consumer<T> {

    private static final String TAG = "RxException";

    private static final String SOCKET_TIMEOUT_EXCEPTION = "网络连接超时，请检查您的网络状态，稍后重试";
    private static final String CONNECT_EXCEPTION = "网络连接异常，请检查您的网络状态";
    private static final String UNKNOWN_HOST_EXCEPTION = "网络异常，请检查您的网络状态";

    private Consumer<? super Throwable> onError;

    public RxException(Consumer<? super Throwable> onError) {
        this.onError = onError;
    }

    /**
     * Consume the given value.
     *
     * @param t the value
     * @throws Exception on error
     */
    @Override
    public void accept(T t) throws Exception {
        if (t instanceof SocketTimeoutException) {

            ToastUtils.showToast(SOCKET_TIMEOUT_EXCEPTION);
            onError.accept(new Throwable(SOCKET_TIMEOUT_EXCEPTION));

        } else if (t instanceof ConnectException) {

            ToastUtils.showToast(CONNECT_EXCEPTION);
            onError.accept(new Throwable(CONNECT_EXCEPTION));

        } else if (t instanceof UnknownHostException) {

            ToastUtils.showToast(UNKNOWN_HOST_EXCEPTION);
            onError.accept(new Throwable(UNKNOWN_HOST_EXCEPTION));

        } else {
            onError.accept(t);
        }
    }
}
