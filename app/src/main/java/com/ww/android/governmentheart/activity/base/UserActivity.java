package com.ww.android.governmentheart.activity.base;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.activity.home.LoginActivity;
import com.ww.android.governmentheart.activity.home.MyCommentsActivity;
import com.ww.android.governmentheart.activity.home.MySuggestActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.utils.DialogUtils;

import butterknife.BindView;
import butterknife.OnClick;
import ww.com.core.widget.RoundImageView;

/**
 * @author feng
 * @Date 2018/7/30.
 */
public class UserActivity extends BaseActivity<VoidView, VoidModel> {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.riv)
    RoundImageView riv;

    public static void start(Context context) {
        Intent intent = new Intent(context, UserActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_user;
    }

    @Override
    protected void init() {
        UserBean user = (UserBean) BaseApplication.getInstance().getUserInfo();
        if (user != null) {
            tvName.setText(user.getUser().getName());
            tvPhone.setText(user.getUser().getMobile());
            tvTime.setText("暂无");
            ImageLoader.getInstance().displayImage(user.getUser().getPhoto(), riv,
                    BaseApplication.getDisplayImageOptions(R.mipmap.ic_header_default));
        }
    }

    @OnClick({R.id.btn_quit,R.id.ll_comment_layout,R.id.ll_suggest_layout})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_quit:
                DialogUtils.showDialog(this, "提示", "你确定退出当前登录吗？", "退出", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    BaseApplication.getInstance().saveUserInfo(null);
                    BaseApplication.getInstance().clearTopTask(UserActivity.this);
                    LoginActivity.launch(UserActivity.this);
                    finish();
                });
                break;
            case R.id.ll_comment_layout:
                MyCommentsActivity.start(this);
                break;
            case R.id.ll_suggest_layout:
                MySuggestActivity.start(this);

                break;
        }

    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.BLUE;
    }
}
