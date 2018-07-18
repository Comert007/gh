package com.ww.android.governmentheart.adapter.together;

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
import com.ww.android.governmentheart.config.listener.OnActionListener;
import com.ww.android.governmentheart.config.type.CommentType;
import com.ww.android.governmentheart.mvp.bean.home.EasyRequestBean;
import com.ww.android.governmentheart.mvp.bean.together.ActBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class ActivityAdapter extends RvAdapter<ActBean> {

    private OnActionListener mOnActionListener;

    public ActivityAdapter(Context context) {
        super(context);
    }

    public void setOnActionListener(OnActionListener onActionListener) {
        mOnActionListener = onActionListener;
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_activity;
    }

    @Override
    protected RvViewHolder<ActBean> getViewHolder(int viewType, View view) {
        return new PublishViewHolder(view);
    }

    class PublishViewHolder extends RvViewHolder<ActBean> {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_time_delay)
        TextView tvTimeDelay;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_origin_name)
        TextView tvOriginName;
        @BindView(R.id.tv_join)
        TextView tvJoin;
        @BindView(R.id.container)
        LinearLayout container;
        @BindView(R.id.ll_style)
        LinearLayout llStyle;

        public PublishViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, ActBean bean) {
            ImageLoader.getInstance().displayImage(bean.getUrl(), iv, BaseApplication
                    .getDisplayImageOptions(R.mipmap.ic_pic_default));

            tvTitle.setText(bean.getTitle());
//            tvTimeDelay.setText("还有"+bean.getDate()+"天开始");
            tvTime.setText("时间："+bean.getDate());
//            tvOriginName.setText(bean.get);
            //t 1 即将开始 2 正在进行 3 结束
            if (bean.getType().equals("1")){
                tvTimeDelay.setText("即将开始");
                tvJoin.setVisibility(View.INVISIBLE);
            }else if (bean.getType().equals("2")){
                tvTimeDelay.setText("还有"+bean.getDate()+"天开始");
                tvJoin.setVisibility(View.VISIBLE);
            }else {
                tvTimeDelay.setText("已结束");
                tvJoin.setVisibility(View.INVISIBLE);
            }

            tvJoin.setOnClickListener(v -> {
                if (mOnActionListener!=null){
                    mOnActionListener.onAction(tvJoin,getAdapterPosition());
                }
            });

            EasyRequestBean easyRequestBean = new EasyRequestBean.Builder()
                    .setId(bean.getId())
                    .setName(bean.getTitle())
                    .setUrl(bean.getUrl())
                    .setType(CommentType.TYPE_ACT)
                    .setNum(TextUtils.isEmpty(bean.getCommentNum()) ? 0 : Integer.valueOf
                            (bean.getCommentNum()))
                    .build();
            container.setOnClickListener(v -> ContentDetailActivity.start(getContext(),easyRequestBean));
        }
    }
}
