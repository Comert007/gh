package com.ww.android.governmentheart.activity.heart;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.home.SearchAdapter;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.home.SearchView;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.Arrays;

import butterknife.OnClick;

/**
 * @Author feng
 * @Date 2018/6/20
 */
public class SearchActivity extends BaseActivity<SearchView, VoidModel> {

    private SearchAdapter adapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init() {
        initListener();
        initRecycler();
        v.initFluid(Arrays.asList("统战","民主人士","新路子","统一战线","统战信息化","创新","见面会",
                "平安崇州","党代会","乡村"));
    }

    private void initListener() {
        if (v.srl == null) {
            return;
        }
        v.setRefreshType(RefreshType.NOT_ENABLE);
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.defaultVerticalNotScrollManager(this),
                RecyclerHelper.defaultSingleDecoration(this));

        adapter = new SearchAdapter(this);
        v.crv.setAdapter(adapter);
        adapter.addList(Arrays.asList("1", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3",
                "3"));
    }

    @OnClick({R.id.tv_cancel})
    public void onClick(View view){
        switch ( view.getId()){
            case R.id.tv_cancel:
                finish();
                break;
        }
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

}
