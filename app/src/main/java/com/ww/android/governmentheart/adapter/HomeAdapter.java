package com.ww.android.governmentheart.adapter;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class HomeAdapter extends RvAdapter<String> {

    public HomeAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_home;
    }

    @Override
    protected RvViewHolder<String> getViewHolder(int viewType, View view) {
        return new HomeViewHolder(view);
    }

    class HomeViewHolder extends RvViewHolder<String>{

        public HomeViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, String bean) {

        }
    }
}
