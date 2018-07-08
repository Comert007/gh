package com.ww.android.governmentheart.adapter.style;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.MultipleBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class FeaturesAdapter extends RvAdapter<NewsTypeBean> {

    public FeaturesAdapter(Context context) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getItemType();
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        if (MultipleBean.MULTIPLE_HEADER == viewType){
            return R.layout.adapter_features_header;
        }else if (MultipleBean.MULTIPLE_BODY == viewType){
            return R.layout.adapter_features_body;
        }else {
            return R.layout.layout_empty;
        }

    }

    @Override
    protected RvViewHolder<NewsTypeBean> getViewHolder(int viewType, View view) {
        if (MultipleBean.MULTIPLE_HEADER == viewType){
            return new FeaturesHeaderViewHolder(view);
        }else if (MultipleBean.MULTIPLE_BODY == viewType){
            return new FeaturesBodyViewHolder(view);
        }else {
            return new FailureViewHolder(view);
        }
    }


    class FeaturesHeaderViewHolder extends RvViewHolder<NewsTypeBean>{

        public FeaturesHeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsTypeBean bean) {

        }
    }


    class FeaturesBodyViewHolder extends RvViewHolder<NewsTypeBean>{
        @BindView(R.id.rv)
        RecyclerView rv;
        public FeaturesBodyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsTypeBean bean) {
            FeaturesBodyAdapter adapter = new FeaturesBodyAdapter(getContext());
            rv.setLayoutManager(RecyclerHelper.gridManager(getContext(),2));
            rv.setAdapter(adapter);
            adapter.addList(bean.getNews());
        }
    }

    class FailureViewHolder extends RvViewHolder<NewsTypeBean>{

        public FailureViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsTypeBean bean) {

        }
    }

}
