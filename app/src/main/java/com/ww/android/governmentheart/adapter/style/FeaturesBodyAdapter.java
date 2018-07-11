package com.ww.android.governmentheart.adapter.style;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.base.ContentDetailActivity;
import com.ww.android.governmentheart.config.type.CommentType;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;
import com.ww.android.governmentheart.mvp.bean.home.EasyRequestBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class FeaturesBodyAdapter extends RvAdapter<NewsBean> {

    public FeaturesBodyAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.layout_features_body;
    }

    @Override
    protected RvViewHolder<NewsBean> getViewHolder(int viewType, View view) {
        return new FeaturesViewHolder(view);
    }

    class FeaturesViewHolder extends RvViewHolder<NewsBean> {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_view_num)
        TextView tvViewNum;
        @BindView(R.id.container)
        LinearLayout container;



        public FeaturesViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {
            ImageLoader.getInstance().displayImage(bean.getImage(), iv, BaseApplication
                    .getDisplayImageOptions(R.mipmap.ic_pic_default));
            tvTitle.setText(bean.getTitle());
            tvViewNum.setText(TextUtils.isEmpty(bean.getViewNum())?"0":bean.getViewNum());

            EasyRequestBean easyRequestBean = new EasyRequestBean.Builder()
                    .setId(bean.getId())
                    .setName(bean.getTitle())
                    .setUrl(bean.getLink())
                    .setType(CommentType.TYPE_NEWS)
                    .build();
            container.setOnClickListener(v -> ContentDetailActivity.start(getContext(),easyRequestBean));
        }
    }
}
