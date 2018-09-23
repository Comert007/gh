package com.ww.android.governmentheart.activity.home;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.model.home.UserMemberBean;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;

import butterknife.BindView;

public class MemberDetailActivity extends BaseActivity<VoidView,VoidModel>{
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.tv_nation)
    TextView tvNation;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_mobile)
    TextView tvMobile;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_education)
    TextView tvEducation;
    @BindView(R.id.tv_intro)
    TextView tvIntro;

    private UserMemberBean mUserMemberBean;

    public static void start(Context context, UserMemberBean userMemberBean) {
        Intent starter = new Intent(context, MemberDetailActivity.class);
        starter.putExtra("userInfo",userMemberBean);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_member_detail;
    }

    @Override
    protected void init() {
        mUserMemberBean = (UserMemberBean) getIntent().getSerializableExtra("userInfo");
        initData();
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }


    private void initData(){
        tvName.setText(mUserMemberBean.getName());
        tvSex.setText("1".equals(mUserMemberBean.getSex())?"男":"女");
        tvNation.setText(mUserMemberBean.getNativeplace());
        tvEmail.setText(mUserMemberBean.getEmail());
        tvPhone.setText(mUserMemberBean.getPhone());
        tvMobile.setText(mUserMemberBean.getMobile());
        tvBirthday.setText(mUserMemberBean.getBirthday());
        tvEducation.setText(mUserMemberBean.getDegree());
        tvLocation.setText(mUserMemberBean.getNativeplace());
        tvIntro.setText(mUserMemberBean.getWorkplace());
    }
}
