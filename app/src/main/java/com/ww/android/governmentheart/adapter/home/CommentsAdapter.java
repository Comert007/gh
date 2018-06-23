package com.ww.android.governmentheart.adapter.home;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class CommentsAdapter extends RvAdapter<String>{

    public CommentsAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_comments;
    }

    @Override
    protected RvViewHolder<String> getViewHolder(int viewType, View view) {
        return new CommentsViewHolder(view);
    }

    class CommentsViewHolder extends RvViewHolder<String>{

        public CommentsViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, String bean) {

        }
    }
}
