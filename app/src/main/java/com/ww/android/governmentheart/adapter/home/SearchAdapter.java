package com.ww.android.governmentheart.adapter.home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ww.android.governmentheart.R;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @author feng
 * @Date 2018/6/22.
 */
public class SearchAdapter extends RvAdapter<String>{

    public SearchAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_search;
    }

    @Override
    protected RvViewHolder<String> getViewHolder(int viewType, View view) {
        return new SearchViewHolder(view);
    }

    class SearchViewHolder extends RvViewHolder<String>{
        @BindView(R.id.tv_search_title)
        TextView tvSearchTitle;

        public SearchViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, String bean) {
            tvSearchTitle.setText(bean);
        }
    }
}
