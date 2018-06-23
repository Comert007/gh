package com.ww.android.governmentheart.activity.wisdom;

import android.content.Context;
import android.content.Intent;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.wisdom.ImagePickAdapter;
import com.ww.android.governmentheart.mvp.bean.wisdom.ImagePickBean;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.wisdom.AdviceView;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.Arrays;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class AdviceActivity extends BaseActivity<AdviceView, VoidModel> {

    private ImagePickAdapter adapter;

    public static void start(Context context) {
        Intent intent = new Intent(context, AdviceActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_advice;
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
        adapter = new ImagePickAdapter(this);
        v.crv.setAdapter(adapter);
        adapter.addList(Arrays.asList(new ImagePickBean(ImagePickBean.MULTIPLE_DEFAULT_IMAGE)));
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.onActivityResult(requestCode, requestCode, data);
    }

}
