package com.ww.android.governmentheart.adapter.home;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.heart.SearchActivity;
import com.ww.android.governmentheart.mvp.bean.MultipleBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class HomeAdapter extends RvAdapter<NewsBean> {

    public HomeAdapter(Context context) {
        super(context);
    }


    @Override
    protected int getItemLayoutResId(int viewType) {
        if (MultipleBean.MULTIPLE_HEADER == viewType) {
            return R.layout.adapter_heart_header;
        } else if (MultipleBean.MULTIPLE_BODY == viewType) {
            return R.layout.adapter_heart_body;
        } else {
            return R.layout.layout_empty;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getItemType();
    }

    @Override
    protected RvViewHolder<NewsBean> getViewHolder(int viewType, View view) {
        if (MultipleBean.MULTIPLE_HEADER == viewType) {
            return new HeaderViewHolder(view);
        } else if (MultipleBean.MULTIPLE_BODY == viewType) {
            return new HomeViewHolder(view);
        } else {
            return new EmptyViewHolder(view);
        }
    }

    class HeaderViewHolder extends RvViewHolder<NewsBean> {
        @BindView(R.id.et_clear)
        TextView tvClear;

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {
            tvClear.setOnClickListener(v -> SearchActivity.start(getContext()));
        }
    }


    class HomeViewHolder extends RvViewHolder<NewsBean> {

        public HomeViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {

        }
    }

    class EmptyViewHolder extends RvViewHolder<NewsBean> {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {

        }
    }
}
