package com.ww.android.governmentheart.adapter.home;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.mvp.bean.home.OrganizationBean;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class OrganizationTopAdapter extends RvAdapter<OrganizationBean> {

    public OrganizationTopAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return 0;
    }

    @Override
    protected RvViewHolder<OrganizationBean> getViewHolder(int viewType, View view) {
        return null;
    }
}
