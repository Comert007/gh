package com.ww.android.governmentheart.adapter.wisdom;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/18
 */
public class TransmissionAdapter extends RvAdapter<String> {

    public TransmissionAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_transmission;
    }

    @Override
    protected RvViewHolder<String> getViewHolder(int viewType, View view) {
        return null;
    }
}
