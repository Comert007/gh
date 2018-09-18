package com.ww.android.governmentheart.adapter.home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.model.home.UserMemberBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

public class OrgMembersAdapter extends RvAdapter<UserMemberBean> {


    public OrgMembersAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_org_members;
    }

    @Override
    protected RvViewHolder<UserMemberBean> getViewHolder(int viewType, View view) {
        return new OrgMembersViewHolder(view);
    }

    class OrgMembersViewHolder extends RvViewHolder<UserMemberBean>{
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_position)
        TextView tvPosition;
        @BindView(R.id.tv_attribution)
        TextView tvAttribution;

        public OrgMembersViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, UserMemberBean bean) {
            tvName.setText(bean.getName());
            tvPosition.setText(bean.getWorkplace());
            tvAttribution.setText(bean.getNativeplace());
        }
    }
}
