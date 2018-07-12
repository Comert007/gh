package com.ww.android.governmentheart.adapter.home;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.home.OrganizationBean;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class MemberAdapter extends RvAdapter<OrganizationBean> {

    public MemberAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        if (OrganizationBean.MULTIPLE_HEADER == viewType){
           return R.layout.adapter_member_header;
        }else if (OrganizationBean.MULTIPLE_TITLE ==  viewType){
            return R.layout.adapter_join_title;
        }else {
            return R.layout.adapter_join_body;
        }
    }

    @Override
    protected RvViewHolder<OrganizationBean> getViewHolder(int viewType, View view) {
        if (OrganizationBean.MULTIPLE_HEADER == viewType){
            return new HeaderViewHolder(view);
        }else if (OrganizationBean.MULTIPLE_TITLE ==  viewType){
            return new TitleViewHolder(view);
        }else {
            return new BodyViewHolder(view);
        }
    }


    class TitleViewHolder extends RvViewHolder<OrganizationBean>{

        public TitleViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, OrganizationBean bean) {

        }
    }

    class HeaderViewHolder extends RvViewHolder<OrganizationBean>{

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, OrganizationBean bean) {

        }
    }


    class BodyViewHolder extends RvViewHolder<OrganizationBean>{

        public BodyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, OrganizationBean bean) {

        }
    }
}
