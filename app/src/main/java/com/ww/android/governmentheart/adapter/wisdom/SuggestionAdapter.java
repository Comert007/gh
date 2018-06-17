package com.ww.android.governmentheart.adapter.wisdom;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/17
 */
public class SuggestionAdapter extends RvAdapter<String> {

    public SuggestionAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_suggestion;
    }

    @Override
    protected RvViewHolder<String> getViewHolder(int viewType, View view) {
        return new SuggestionViewHolder(view);
    }

    class SuggestionViewHolder extends RvViewHolder<String> {

        public SuggestionViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, String bean) {

        }
    }
}
