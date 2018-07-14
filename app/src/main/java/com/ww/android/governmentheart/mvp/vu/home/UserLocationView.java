package com.ww.android.governmentheart.mvp.vu.home;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.vu.base.BaseView;

import butterknife.BindView;

/**
 * @Author feng
 * @Date 2018/7/14
 */
public class UserLocationView extends BaseView {
    @BindView(R.id.container_user_detail)
    LinearLayout containerUserDetail;
    @BindView(R.id.container_members)
    LinearLayout containerMembers;
    @BindView(R.id.tv_member_num)
    TextView tvMemberNum;
    @BindView(R.id.tv_member_name)
    TextView tvMemberName;
    @BindView(R.id.tv_member_description)
    TextView tvMemberDescription;
    @BindView(R.id.tv_organization_name)
    TextView tvOrganizationName;


    @Override
    public void onAttach() {

    }

    /**
     * 设置底部显示状态 1 详情显示 2 组织成员显示 0:隐藏全部
     *
     */
    public void setBottomVisible(int state) {
        if (state == 0) {
            containerUserDetail.setVisibility(View.GONE);
            containerMembers.setVisibility(View.GONE);
        } else {
            containerUserDetail.setVisibility(state == 1 ? View.VISIBLE : View.GONE);
            containerMembers.setVisibility(state != 1 ? View.VISIBLE : View.GONE);
        }

    }

    public void showInfo(String name, String description) {
        tvMemberName.setText(name);
        tvMemberDescription.setText(description);
    }

    public void showMemberNum(String num) {
        tvMemberNum.setText(num);
    }

    public void showOrganizationName(String name) {
        tvOrganizationName.setText(name);
    }
}
