package com.ww.android.governmentheart.activity.wisdom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.adapter.wisdom.ShowImageAdapter;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.SuggestDetailBean;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.utils.RefreshType;
import com.ww.android.governmentheart.mvp.vu.wisdom.ShowAdviceView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class ShowAdviceActivity extends BaseActivity<ShowAdviceView, WisdomModel> {

    private ShowImageAdapter adapter;
    private String id;

    public static void start(Context context, String id) {
        Intent intent = new Intent(context, ShowAdviceActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_show_advice;
    }

    @Override
    protected void init() {
        id = getIntent().getStringExtra("id");
        initListener();
        initRecycler();
        suggestDetail();
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
        v.initRecycler(RecyclerHelper.gridManager(this, 4));
        adapter = new ShowImageAdapter(this);
        v.crv.setAdapter(adapter);
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    /**
     * 详情
     */
    private void suggestDetail() {
        Map map = new HashMap();
        map.put("id", id);
        m.suggestDetail(map, new BaseObserver<SuggestDetailBean>(this,bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable SuggestDetailBean suggestDetailBean, @Nullable
                    List<SuggestDetailBean> list, @Nullable PageBean<SuggestDetailBean> page) {
                if (suggestDetailBean!=null){
//                    v.setTitle(suggestDetailBean.getTitle());
//                    v.setContent(suggestDetailBean.getCont());
//
//                    adapter.addList(suggestDetailBean.getImgs());
                }
            }
        });
    }
}
