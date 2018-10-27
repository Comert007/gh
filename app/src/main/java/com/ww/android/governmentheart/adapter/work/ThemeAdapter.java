package com.ww.android.governmentheart.adapter.work;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.work.ThemeEntity;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;
import ww.com.core.utils.TimeUtils;

public class ThemeAdapter extends RvAdapter<ThemeEntity>{

    public ThemeAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_theme;
    }

    @Override
    protected RvViewHolder<ThemeEntity> getViewHolder(int viewType, View view) {
        return new ThemeViewHolder(view);
    }

    class ThemeViewHolder extends RvViewHolder<ThemeEntity>{

        @BindView(R.id.tv_title_name)
        TextView tvTitleName;
        @BindView(R.id.tv_eyes)
        TextView tvEyes;
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.comment_container)
        LinearLayout commentContainer;
        @BindView(R.id.container)
        LinearLayout container;

        public ThemeViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, ThemeEntity bean) {
            tvTitleName.setText(bean.getTitle());
            tvTime.setText(TimeUtils.milliseconds2String(bean.getCreateTime()));
            tvComment.setText(String.format("%s", bean.getCommentCount()));
            tvEyes.setText(String.format("%s", bean.getSeeCount()));
        }
    }
}
