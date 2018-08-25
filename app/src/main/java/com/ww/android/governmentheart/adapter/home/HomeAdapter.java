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
import com.ww.android.governmentheart.activity.base.ContentDetailActivity;
import com.ww.android.governmentheart.activity.heart.SearchActivity;
import com.ww.android.governmentheart.activity.home.UserLocationActivity;
import com.ww.android.governmentheart.config.type.CommentType;
import com.ww.android.governmentheart.mvp.bean.MultipleBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;
import com.ww.android.governmentheart.mvp.bean.home.EasyRequestBean;

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
        } else {
            return R.layout.adapter_heart_body;
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
        } else {
            return new HomeViewHolder(view);
        }
    }

    class HeaderViewHolder extends RvViewHolder<NewsBean> {
        @BindView(R.id.et_clear)
        TextView tvClear;
        @BindView(R.id.tv_num)
        TextView tvNum;
        @BindView(R.id.iv)
        ImageView iv;

        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {
            ImageLoader.getInstance().displayImage(bean.mainpic, iv, BaseApplication
                    .getDisplayImageOptions(R.mipmap.ic_pic_default));
            tvClear.setOnClickListener(v -> SearchActivity.start(getContext()));
            tvNum.setText("共" + bean.totalNum + "条");
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UserLocationActivity.launch(getContext());
                }
            });
        }
    }


    class HomeViewHolder extends RvViewHolder<NewsBean> {
        @BindView(R.id.iv)
        ImageView iv;
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

        public HomeViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsBean bean) {
            ImageLoader.getInstance().displayImage(bean.getImage(), iv, BaseApplication
                    .getDisplayImageOptions(R.mipmap.ic_pic_default));
            tvTitleName.setText(bean.getTitle());
            tvEyes.setText(bean.getHits());
            tvComment.setText(bean.getCommentCount());
            tvTime.setText(bean.getCreateDate());
            EasyRequestBean easyRequestBean = new EasyRequestBean.Builder()
                    .setId(bean.getId())
                    .setName(bean.getTitle())
                    .setUrl(bean.getUrl())
                    .setType(CommentType.TYPE_NEWS)
                    .setNum(TextUtils.isEmpty(bean.getCommentCount()) ? 0 : Integer.valueOf
                            (bean.getCommentCount()))
                    .build();
            container.setOnClickListener(v -> ContentDetailActivity.start(getContext(),
                    easyRequestBean));
        }
    }
}
