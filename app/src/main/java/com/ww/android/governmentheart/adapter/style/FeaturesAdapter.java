package com.ww.android.governmentheart.adapter.style;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.base.ContentDetailActivity;
import com.ww.android.governmentheart.config.listener.OnActionListener;
import com.ww.android.governmentheart.config.type.CommentType;
import com.ww.android.governmentheart.mvp.bean.MultipleBean;
import com.ww.android.governmentheart.mvp.bean.home.EasyRequestBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsChildTypeBean;
import com.ww.android.governmentheart.utils.RecyclerHelper;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class FeaturesAdapter extends RvAdapter<NewsChildTypeBean> {

    private OnActionListener mOnActionListener;

    public FeaturesAdapter(Context context) {
        super(context);
    }

    public void setOnActionListener(OnActionListener onActionListener) {
        mOnActionListener = onActionListener;
    }

    @Override
    public int getItemViewType(int position) {
        return getList().get(position).getItemType();
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        if (MultipleBean.MULTIPLE_HEADER == viewType) {
            return R.layout.adapter_features_header;
        } else {
            return R.layout.adapter_features_body;
        }
    }

    @Override
    protected RvViewHolder<NewsChildTypeBean> getViewHolder(int viewType, View view) {
        if (MultipleBean.MULTIPLE_HEADER == viewType) {
            return new FeaturesHeaderViewHolder(view);
        } else {
            return new FeaturesBodyViewHolder(view);
        }
    }


    class FeaturesHeaderViewHolder extends RvViewHolder<NewsChildTypeBean> {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_view_num)
        TextView tvViewNum;
        @BindView(R.id.tv_des)
        TextView tvDes;
        @BindView(R.id.container)
        RelativeLayout container;


        public FeaturesHeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsChildTypeBean bean) {

            if (bean != null ) {
                ImageLoader.getInstance().displayImage(bean.getImage(), iv, BaseApplication
                        .getDisplayImageOptions(R.mipmap.ic_pic_default));
                tvDes.setText(bean.getName());
                tvViewNum.setText(TextUtils.isEmpty(bean.getViewNum()) ? "0" : bean
                        .getViewNum());

                EasyRequestBean easyRequestBean = new EasyRequestBean.Builder()
                        .setId(bean.getId())
                        .setName(bean.getName())
                        .setUrl(bean.getUrl())
                        .setType(CommentType.TYPE_NEWS)
                        .setNum(TextUtils.isEmpty(bean.getCommentNum()) ? 0 : Integer.valueOf
                                (bean.getCommentNum()))
                        .build();
                container.setOnClickListener(v -> ContentDetailActivity.start(getContext(),
                        easyRequestBean));
            }


        }
    }


    class FeaturesBodyViewHolder extends RvViewHolder<NewsChildTypeBean> {
        @BindView(R.id.rv)
        RecyclerView rv;
        @BindView(R.id.tv_column)
        TextView tvColumn;

        public FeaturesBodyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, NewsChildTypeBean bean) {
            tvColumn.setText(bean.getName());
            if (bean.getNewsBeans() == null){
                if (mOnActionListener != null) {
                    mOnActionListener.onAction(itemView,position);
                }
            }else {
                FeaturesBodyAdapter adapter = new FeaturesBodyAdapter(getContext());
                rv.setLayoutManager(RecyclerHelper.gridManager(getContext(), 2));
                rv.setAdapter(adapter);
                adapter.addList(bean.getNewsBeans());
            }
        }
    }
}
