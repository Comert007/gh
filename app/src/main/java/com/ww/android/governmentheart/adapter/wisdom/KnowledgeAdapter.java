package com.ww.android.governmentheart.adapter.wisdom;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/7/11
 */
public class KnowledgeAdapter extends RvAdapter<String> {

    public KnowledgeAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_default;
    }

    @Override
    protected RvViewHolder<String> getViewHolder(int viewType, View view) {
        return null;
    }

    class KnowledgeViewHolder extends RvViewHolder<String>{

        public KnowledgeViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, String bean) {


        }
    }
}
