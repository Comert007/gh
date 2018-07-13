package com.ww.android.governmentheart.activity.wisdom;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.vu.wisdom.QuestionView;
import com.ww.android.governmentheart.network.BaseObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.OnClick;

/**
 * @author feng
 * @Date 2018/7/13.
 */
public class QuestionActivity extends BaseActivity<QuestionView,WisdomModel> {

    public static void start(Context context) {
        Intent intent = new Intent(context, QuestionActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_question;
    }

    @Override
    protected void init() {

    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }


    @OnClick({R.id.btn_commit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_commit:
                saveQuestion();
                break;
        }
    }

    private void saveQuestion() {
        if (TextUtils.isEmpty(v.getTitle())) {
            showToast("请输入标题");
            return;
        }

        if (TextUtils.isEmpty(v.getContent())) {
            showToast("请输入内容");
            return;
        }
        Map map = new HashMap();
        map.put("title", v.getTitle());
        map.put("con", v.getContent());
        m.addQuestion(map, new BaseObserver<String>(this, bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable String s, @Nullable List<String> list, @Nullable
                    PageBean<String> page) {
                CommitSuccessActivity.start(QuestionActivity.this);
                finish();
            }
        });
    }

}
