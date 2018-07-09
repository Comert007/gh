package com.ww.android.governmentheart.adapter.join;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.MultipleBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @author feng
 * @Date 2018/6/21.
 */
public class JoinAdapter extends RvAdapter<NewsBean>{

    public JoinAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getItemType();
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        if (NewsBean.MULTIPLE_HEADER == viewType){
            return R.layout.layout_join_header;
        }else {
            return R.layout.adapter_join_body;
        }
    }

    @Override
    protected RvViewHolder<NewsBean> getViewHolder(int viewType, View view) {
        if (MultipleBean.MULTIPLE_HEADER == viewType){
            return new JoinHeaderViewHolder(view);
        }else {
            return new JoinBodyViewHolder(view);
        }
    }

    class JoinHeaderViewHolder extends RvViewHolder<NewsBean>{

        public JoinHeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {

        }
    }


    class JoinBodyViewHolder extends RvViewHolder<NewsBean> {

        public JoinBodyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {

        }
    }


    class EmptyViewHolder extends RvViewHolder<NewsBean>{

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {

        }
    }
}
