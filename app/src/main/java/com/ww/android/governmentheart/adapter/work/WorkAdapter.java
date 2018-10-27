package com.ww.android.governmentheart.adapter.work;

import android.content.Context;
import android.view.View;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.work.WorkEntity;

import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

public class WorkAdapter extends RvAdapter<WorkEntity>{

    public WorkAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_work;
    }

    @Override
    protected RvViewHolder<WorkEntity> getViewHolder(int viewType, View view) {
        return null;
    }
}
