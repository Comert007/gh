package com.ww.android.governmentheart.adapter.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.config.listener.OnAdapterItemListener;
import com.ww.android.governmentheart.mvp.bean.together.OrganizationTypeBean;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/7/12
 */
public class MemberAdapter extends RvAdapter<OrganizationTypeBean> {

    private OnTypeListener mOnTypeListener;

    public MemberAdapter(Context context) {
        super(context);
    }

    public void setOnTypeListener(OnTypeListener onTypeListener) {
        mOnTypeListener = onTypeListener;
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        if (OrganizationTypeBean.MULTIPLE_TITLE == viewType) {
            return R.layout.adapter_join_title;
        } else if (OrganizationTypeBean.MULTIPLE_HEADER == viewType) {
            return R.layout.adapter_member_header;
        } else {
            return R.layout.adapter_join_body;
        }
    }


    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getItemType();
    }

    @Override
    protected RvViewHolder<OrganizationTypeBean> getViewHolder(int viewType, View view) {
        if (OrganizationTypeBean.MULTIPLE_HEADER == viewType) {
            return new HeaderViewHolder(view);
        } else if (OrganizationTypeBean.MULTIPLE_TITLE == viewType) {
            return new TitleViewHolder(view);
        } else {
            return new BodyViewHolder(view);
        }
    }


    class HeaderViewHolder extends RvViewHolder<OrganizationTypeBean> {
        @BindView(R.id.rv)
        RecyclerView rv;

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, OrganizationTypeBean bean) {
            rv.setLayoutManager(RecyclerHelper.gridManager(getContext(), 2));
            MemberDemocracyAdapter adapter = new MemberDemocracyAdapter(getContext());
            adapter.setOnAdapterItemListener(new OnAdapterItemListener() {
                @Override
                public void onAdapterItem(View view, int position) {
                    OrganizationTypeBean organizationTypeBean = adapter.getItem(position);
                    if (mOnTypeListener!=null){
                        mOnTypeListener.onType(organizationTypeBean);
                    }
                }
            });
            rv.setAdapter(adapter);
            rv.setNestedScrollingEnabled(false);
            adapter.addList(bean.getOrganizationTypeBeans());

        }
    }

    class TitleViewHolder extends RvViewHolder<OrganizationTypeBean> {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public TitleViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, OrganizationTypeBean bean) {
            tvTitle.setText(bean.getName());
        }
    }


    class BodyViewHolder extends RvViewHolder<OrganizationTypeBean> {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.container)
        LinearLayout container;

        public BodyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, OrganizationTypeBean bean) {
            ImageLoader.getInstance().displayImage(bean.getImage(), iv, BaseApplication
                    .getDisplayImageOptions(R.mipmap.ic_header_default));
            tvName.setText(bean.getName());
            tvNum.setText(String.format("%s人", bean.getUserCount()));
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnTypeListener!=null){
                        mOnTypeListener.onType(getItem(position));
                    }
                }
            });
        }
    }

   public interface OnTypeListener{
        void onType(OrganizationTypeBean organizationTypeBean);
    }
}
