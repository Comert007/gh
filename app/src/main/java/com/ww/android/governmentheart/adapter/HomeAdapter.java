package com.ww.android.governmentheart.adapter;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.MultipleBean;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class HomeAdapter extends RvAdapter<MultipleBean> {

    public HomeAdapter(Context context) {
        super(context);
    }


    @Override
    protected int getItemLayoutResId(int viewType) {
        if (MultipleBean.MULTIPLE_HEADER == viewType) {
            return R.layout.adapter_heart_header;
        } else if (MultipleBean.MULTIPLE_BODY == viewType) {
            return R.layout.adapter_heart_body;
        } else {
            return R.layout.layout_empty;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getItemType();
    }

    @Override
    protected RvViewHolder<MultipleBean> getViewHolder(int viewType, View view) {
        if (MultipleBean.MULTIPLE_HEADER == viewType) {
            return new HeaderViewHolder(view);
        } else if (MultipleBean.MULTIPLE_BODY == viewType) {
            return new HomeViewHolder(view);
        } else {
            return new EmptyViewHolder(view);
        }
    }

    class HeaderViewHolder extends RvViewHolder<MultipleBean> {

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, MultipleBean bean) {

        }
    }


    class HomeViewHolder extends RvViewHolder<MultipleBean> {

        public HomeViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, MultipleBean bean) {

        }
    }

    class EmptyViewHolder extends RvViewHolder<MultipleBean> {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, MultipleBean bean) {

        }
    }
}
