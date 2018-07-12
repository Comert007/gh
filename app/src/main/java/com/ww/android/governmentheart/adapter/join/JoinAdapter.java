package com.ww.android.governmentheart.adapter.join;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.config.listener.OnAdapterItemListener;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @author feng
 * @Date 2018/6/21.
 */
public class JoinAdapter extends RvAdapter<NewsBean> {

    private OnAdapterItemListener mOnAdapterItemListener;

    public JoinAdapter(Context context) {
        super(context);
    }

    public void setOnAdapterItemListener(OnAdapterItemListener onAdapterItemListener) {
        mOnAdapterItemListener = onAdapterItemListener;
    }

    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getItemType();
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_join;
    }

    @Override
    protected RvViewHolder<NewsBean> getViewHolder(int viewType, View view) {
        return new JoinBodyViewHolder(view);
    }


    class JoinBodyViewHolder extends RvViewHolder<NewsBean> {
        @BindView(R.id.it_left)
        ImageView itLeft;
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.container)
        RelativeLayout container;

        public JoinBodyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {
            ImageLoader.getInstance().displayImage(bean.isVisible ? bean.getImage() : bean
                    .getUnImage(), iv, BaseApplication.getDisplayImageOptions(R.mipmap.ic_header_default));

            itLeft.setVisibility(bean.isVisible ? View.VISIBLE : View.GONE);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selected(position);
                    if (mOnAdapterItemListener != null) {
                        mOnAdapterItemListener.onAdapterItem(container, position);
                    }
                }
            });
        }

    }

    public void selected(int position) {
        for (NewsBean newsBean : getList()) {
            newsBean.isVisible = false;
        }

        getItem(position).isVisible = true;
        notifyDataSetChanged();
    }

}
