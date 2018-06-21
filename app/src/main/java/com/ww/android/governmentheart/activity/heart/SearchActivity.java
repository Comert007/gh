package com.ww.android.governmentheart.activity.heart;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.RefreshView;

/**
 * @Author feng
 * @Date 2018/6/20
 */
public class SearchActivity extends BaseActivity<RefreshView,VoidModel> {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {

    }

    @Override
    protected boolean isDefaultImmersionBar() {
        return false;
    }

    @Override
    public int refreshType() {
        return RefreshType.NOT_ENABLE;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
    }



}
