package com.ww.android.governmentheart.adapter.together;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class ActivityAdapter extends RvAdapter<String> {

    public ActivityAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_default;
    }

    @Override
    protected RvViewHolder<String> getViewHolder(int viewType, View view) {
        return new PublishViewHolder(view);
    }

    class PublishViewHolder extends RvViewHolder<String> {

        public PublishViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, String bean) {

        }
    }
}
