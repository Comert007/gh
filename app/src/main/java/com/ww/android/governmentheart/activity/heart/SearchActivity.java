package com.ww.android.governmentheart.activity.heart;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.home.SearchAdapter;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.Arrays;

/**
 * @Author feng
 * @Date 2018/6/20
 */
public class SearchActivity extends BaseActivity<RefreshView,VoidModel> {

    private SearchAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        initListener();
        initRecycler();
    }

    private void initListener(){
        if (v.srl != null) {
            return;
        }
        v.setRefreshType(RefreshType.NOT_ENABLE);
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultManager(this),RecyclerHelper.defaultSingleDecoration(this));

        adapter = new SearchAdapter(this);
        v.crv.setAdapter(adapter);
        adapter.addList(Arrays.asList("1","3","3","3","3","3","3","3","3","3","3","3","3"));
    }



    @Override
    protected boolean isDefaultImmersionBar() {
        return false;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.color_white).init();
    }


    private void initFluid(){

    }


}
