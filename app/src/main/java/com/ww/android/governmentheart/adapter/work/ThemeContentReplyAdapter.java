package com.ww.android.governmentheart.adapter.work;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.work.ThemeReplyEntity;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

public class ThemeContentReplyAdapter extends RvAdapter<ThemeReplyEntity>{

    public ThemeContentReplyAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_theme_content_reply;
    }

    @Override
    protected RvViewHolder<ThemeReplyEntity> getViewHolder(int viewType, View view) {
        return new ThemeContentViewHolder(view);
    }

    class ThemeContentViewHolder extends RvViewHolder<ThemeReplyEntity>{
        @BindView(R.id.tv_reply)
        TextView tvReply;

        public ThemeContentViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, ThemeReplyEntity bean) {
            Spanned spanned = Html.fromHtml(String.format("<font color ='#cccccc'>%s@%s：</font>%s",
                    bean.getUserName(), bean.getFatherUserName(), bean.getContent()));
            tvReply.setText(spanned);

        }
    }
}
