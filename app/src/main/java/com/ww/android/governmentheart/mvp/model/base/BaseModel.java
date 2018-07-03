package com.ww.android.governmentheart.mvp.model.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ww.android.governmentheart.mvp.model.IModel;

public class BaseModel implements IModel {

    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        mContext = context;
    }
}
