package com.ww.android.governmentheart.adapter.style;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.MultipleBean;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.Arrays;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class FeaturesAdapter extends RvAdapter<MultipleBean> {

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
    protected RvViewHolder<MultipleBean> getViewHolder(int viewType, View view) {
        if (MultipleBean.MULTIPLE_HEADER == viewType){
            return new FeaturesHeaderViewHolder(view);
        }else if (MultipleBean.MULTIPLE_BODY == viewType){
            return new FeaturesBodyViewHolder(view);
        }else {
            return new FailureViewHolder(view);
        }
    }


    class FeaturesHeaderViewHolder extends RvViewHolder<MultipleBean>{

        public FeaturesHeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, MultipleBean bean) {

        }
    }


    class FeaturesBodyViewHolder extends RvViewHolder<MultipleBean>{
        @BindView(R.id.rv)
        RecyclerView rv;
        public FeaturesBodyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, MultipleBean bean) {
            FeaturesBodyAdapter adapter = new FeaturesBodyAdapter(getContext());
            rv.setLayoutManager(RecyclerHelper.grideManager(getContext(),2));
            rv.setAdapter(adapter);

            adapter.addList(Arrays.asList("1","2","3"));
        }
    }

    class FailureViewHolder extends RvViewHolder<MultipleBean>{

        public FailureViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, MultipleBean bean) {

        }
    }

}
