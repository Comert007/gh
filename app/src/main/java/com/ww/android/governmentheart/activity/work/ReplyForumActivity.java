package com.ww.android.governmentheart.activity.work;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.EditText;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.Constant;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.config.type.RequestType;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.model.work.WorkModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.utils.ToastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

@SuppressLint("Registered")
public class ReplyForumActivity extends BaseActivity<VoidView,WorkModel>{

    @BindView(R.id.et_content)
    EditText etContent;

    /**
     * 0: 回复评论 1：回复帖子
     */
    private int type = 1;
    private String id;
    
    public static void start(Activity context, int type, String id) {
        Intent starter = new Intent(context, ReplyForumActivity.class);
        starter.putExtra("type",type);
        starter.putExtra("id",id);
        context.startActivityForResult(starter, RequestType.REQUEST_REPLY_FORUM);
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_reply_forum;
    }

    @Override
    protected void init() {
        type = getIntent().getIntExtra("type",1);
        id = getIntent().getStringExtra("id");
    }

    @OnClick(R.id.btn_reply)
    public void onClick(){
        onReply();
    }

    private void onReply(){
        Map map = new HashMap();
        if (type == 1){
            map.put("topicId",id);
        }else {
            map.put("fatherId",id);
        }
        String content = etContent.getText().toString();
        if (TextUtils.isEmpty(content)){
            ToastUtils.showToast("需要输入回帖内容哦～");
            return;
        }

        map.put("content",content);

        m.replayForum(map, new BaseObserver<String>(this,bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable String string,
                                     @Nullable List<String> list,
                                     @Nullable PageBean<String> page) {

            }

            @Override
            protected void onResponse(ResponseBean<String> responseBean) {
                super.onResponse(responseBean);
                if (responseBean.getStatus().equals(Constant.STATUS_OK)){
                    Intent intent = getIntent();
                    setResult(Activity.RESULT_OK,intent);
                    finish();
                }
            }

            @Override
            protected boolean isIntercept() {
                return true;
            }
        });
    }
}
