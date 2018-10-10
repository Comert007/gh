package com.ww.android.governmentheart.utils.rxbus;

import ww.com.core.Debug;

public abstract class OnListener<T> implements RxBusHelper.OnEventListener<T> {

    @Override
    public void onError(ErrorBean errorBean) {
        Debug.e("the rx_bus error");
    }
}
