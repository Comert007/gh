package com.ww.android.governmentheart.activity.heart;

import android.content.Context;
import android.content.Intent;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.home.CommentsAdapter;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.Arrays;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class CommentsActivity extends BaseActivity<RefreshView,VoidModel> {

    private CommentsAdapter adapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, CommentsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_comments;
    }

    @Override
    protected void init() {
        initListener();
        initRecycler();
    }

    @Override
    public void onTitleLeft() {
        super.onTitleLeft();
    }

    private void initListener() {
        if (v.srl == null) {
            return;
        }
        v.setRefreshType(RefreshType.NOT_ENABLE);
    }

    private void initRecycler() {
        v.initRecycler(RecyclerHelper.gridManager(this,4));
        adapter = new CommentsAdapter(this);
        v.crv.setAdapter(adapter);
        adapter.addList(Arrays.asList("1","2","3","1","2","3","1","2","3"));
    }

    @Override
    protected boolean isDefaultImmersionBar() {
        return false;
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.color_white).statusBarDarkFont
                (true, 0.2f).init();
    }
}
