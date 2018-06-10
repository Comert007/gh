package com.ww.android.governmentheart.fragment;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.VoidView;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class NewsFragment extends BaseFragment<VoidView,VoidModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void init() {

    }
}
