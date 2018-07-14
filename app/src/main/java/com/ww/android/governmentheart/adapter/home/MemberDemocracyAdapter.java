package com.ww.android.governmentheart.adapter.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.config.listener.OnAdapterItemListener;
import com.ww.android.governmentheart.mvp.bean.together.OrganizationTypeBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/7/14
 */
public class MemberDemocracyAdapter extends RvAdapter<OrganizationTypeBean> {

    private OnAdapterItemListener mOnAdapterItemListener;

    public MemberDemocracyAdapter(Context context) {
        super(context);
    }

    public void setOnAdapterItemListener(OnAdapterItemListener onAdapterItemListener) {
        mOnAdapterItemListener = onAdapterItemListener;
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_member_democracy;
    }

    @Override
    protected RvViewHolder<OrganizationTypeBean> getViewHolder(int viewType, View view) {
        return new MemberDemocracyViewHolder(view);
    }

    class MemberDemocracyViewHolder extends RvViewHolder<OrganizationTypeBean> {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.tv_full_name)
        TextView tvFullName;
        @BindView(R.id.line)
        View line;
        @BindView(R.id.container)
        LinearLayout container;

        public MemberDemocracyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, OrganizationTypeBean bean) {
            ImageLoader.getInstance().displayImage(bean.getImage(), iv, BaseApplication
                    .getDisplayImageOptions(R.mipmap.ic_pic_default));
            tvName.setText(bean.getName());
            tvNum.setText(String.format("（共%s人）", TextUtils.isEmpty(bean.getUserCount()) ? "0" :
                    bean.getUserCount()));
            tvFullName.setText(bean.getName());
            line.setVisibility((position + 1) % 2 == 0 ? View.GONE : View.VISIBLE);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnAdapterItemListener != null) {
                        mOnAdapterItemListener.onAdapterItem(v,position);
                    }
                }
            });

        }
    }
}
