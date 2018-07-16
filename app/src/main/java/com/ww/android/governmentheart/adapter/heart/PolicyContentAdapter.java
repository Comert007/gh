package com.ww.android.governmentheart.adapter.heart;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.base.ContentDetailActivity;
import com.ww.android.governmentheart.config.type.CommentType;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;
import com.ww.android.governmentheart.mvp.bean.home.EasyRequestBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @author feng
 * @Date 2018/7/16.
 */
public class PolicyContentAdapter extends RvAdapter<NewsBean> {

    public PolicyContentAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_policy_content;
    }

    @Override
    protected RvViewHolder<NewsBean> getViewHolder(int viewType, View view) {
        return new PolicyViewHolder(view);
    }

    class PolicyViewHolder extends RvViewHolder<NewsBean> {
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.container)
        LinearLayout container;

        public PolicyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {
            tvTitle.setText(bean.getTitle());
            tvDes.setText(bean.getDescription());
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EasyRequestBean requestBean = new EasyRequestBean.Builder()
                            .setId(bean.getId())
                            .setName(bean.getTitle())
                            .setUrl(bean.getUrl())
                            .setType(CommentType.TYPE_NEWS)
                            .setNum(TextUtils.isEmpty(bean.getCommentNum()) ? 0 : Integer.valueOf
                                    (bean.getCommentNum())).build();

                    ContentDetailActivity.start(getContext(), requestBean);
                }
            });
        }
    }
}
