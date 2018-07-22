package com.ww.android.governmentheart.activity.wisdom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.QuestionDetailBean;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.vu.wisdom.ShowQuestionView;
import com.ww.android.governmentheart.network.BaseObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ww.com.core.Debug;

/**
 * @author feng
 * @Date 2018/6/23.
 */
public class ShowQuestionActivity extends BaseActivity<ShowQuestionView, WisdomModel> {

    private String id;

    public static void start(Context context, String id) {
        Intent intent = new Intent(context, ShowQuestionActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_show_question;
    }

    @Override
    protected void init() {
        id = getIntent().getStringExtra("id");
        questionDetail();
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    /**
     * 详情
     */
    private void questionDetail() {
        Map map = new HashMap();
        map.put("id", id);
        Debug.d("id:"+id);
        m.questionDetail(map, new BaseObserver<PageListBean<QuestionDetailBean>>(this,
                bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable PageListBean<QuestionDetailBean>
                                             suggestDetailBeanPageListBean, @Nullable
                                             List<PageListBean<QuestionDetailBean>> list,
                                     @Nullable PageBean<PageListBean<QuestionDetailBean>> pageBean) {
                QuestionDetailBean detailBean = suggestDetailBeanPageListBean.getData();
               if (detailBean!=null){
                   v.setTitle(detailBean.getTitle());
                   v.setContent(detailBean.getContent());
                   if (detailBean.getComment()!=null && detailBean.getComment().size()>0){
                       String reply = detailBean.getComment().get(0).getContent();
                       v.setTitle(TextUtils.isEmpty(reply)?"暂无回复":reply);
                   }
               }
            }
        });
    }
}
