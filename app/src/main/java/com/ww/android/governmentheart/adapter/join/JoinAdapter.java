package com.ww.android.governmentheart.adapter.join;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.MultipleBean;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @author feng
 * @Date 2018/6/21.
 */
public class JoinAdapter extends RvAdapter<MultipleBean>{

    public JoinAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getItemType();
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        if (MultipleBean.MULTIPLE_HEADER == viewType){
            return R.layout.layout_join_header;
        }else if (MultipleBean.MULTIPLE_BODY == viewType){
            return R.layout.adapter_join_body;
        }else {
            return R.layout.layout_empty;
        }
    }

    @Override
    protected RvViewHolder<MultipleBean> getViewHolder(int viewType, View view) {
        if (MultipleBean.MULTIPLE_HEADER == viewType){
            return new JoinHeaderViewHolder(view);
        }else if (MultipleBean.MULTIPLE_BODY == viewType){
            return new JoinBodyViewHolder(view);
        }else {
            return new EmptyViewHolder(view);
        }
    }

    class JoinHeaderViewHolder extends RvViewHolder<MultipleBean>{

        public JoinHeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, MultipleBean bean) {

        }
    }


    class JoinBodyViewHolder extends RvViewHolder<MultipleBean>{

        public JoinBodyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, MultipleBean bean) {

        }
    }


    class EmptyViewHolder extends RvViewHolder<MultipleBean>{

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, MultipleBean bean) {

        }
    }
}
