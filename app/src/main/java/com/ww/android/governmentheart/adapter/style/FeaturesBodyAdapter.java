package com.ww.android.governmentheart.adapter.style;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class FeaturesBodyAdapter extends RvAdapter<String> {

    public FeaturesBodyAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.layout_features_body;
    }

    @Override
    protected RvViewHolder<String> getViewHolder(int viewType, View view) {
        return new FeaturesViewHolder(view);
    }

    class FeaturesViewHolder extends RvViewHolder<String>{

        public FeaturesViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, String bean) {

        }
    }
}
