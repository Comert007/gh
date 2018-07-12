package com.ww.android.governmentheart.adapter.wisdom;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.config.listener.OnAdapterItemListener;
import com.ww.android.governmentheart.mvp.bean.wisdom.ContactBean;
import com.ww.android.governmentheart.widget.IconFontTextView;

import butterknife.BindColor;
import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class ChooseContactAdapter extends RvAdapter<ContactBean> {

    private OnAdapterItemListener mOnAdapterItemListener;


    public ChooseContactAdapter(Context context) {
        super(context);
    }

    public void setOnAdapterItemListener(OnAdapterItemListener onAdapterItemListener) {
        mOnAdapterItemListener = onAdapterItemListener;
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_choose_contact;
    }

    @Override
    protected RvViewHolder<ContactBean> getViewHolder(int viewType, View view) {
        return new ChooseContactViewHolder(view);
    }

    class ChooseContactViewHolder extends RvViewHolder<ContactBean> {

        @BindView(R.id.it_contact)
        IconFontTextView itContact;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_organization_name)
        TextView tvOrganizationName;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.container)
        LinearLayout container;

        @BindColor(R.color.color_hint)
        int ColorGray;
        @BindColor(R.color.color_blue)
        int ColorBlue;


        public ChooseContactViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, ContactBean bean) {
            itContact.setText(bean.selectedStr);
            itContact.setTextColor(bean.selectedColor);

            tvName.setText(bean.getName());
            tvPhone.setText(TextUtils.isEmpty(bean.getPhone())?"暂无":bean.getPhone());
            tvOrganizationName.setText(TextUtils.isEmpty(bean.getTypeName())?"暂无":bean.getTypeName());

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected();
                    if (mOnAdapterItemListener != null) {
                        mOnAdapterItemListener.onAdapterItem(container, position);
                    }
                }
            });

        }

        private void selected() {
            for (ContactBean contactBean : getList()) {
                contactBean.selectedStr = "\ue61f";
                contactBean.selectedColor = ColorGray;
            }
            ContactBean contactBean = getItem(position);
            contactBean.selectedStr = "\ue61e";
            contactBean.selectedColor = ColorBlue;
            notifyDataSetChanged();
        }
    }


}
