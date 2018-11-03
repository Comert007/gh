package com.ww.android.governmentheart.adapter.wisdom;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.wisdom.ShowQuestionActivity;
import com.ww.android.governmentheart.mvp.bean.wisdom.QuestionBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/7/11
 */
public class QuestionAdapter extends RvAdapter<QuestionBean> {

    public QuestionAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_default;
    }

    @Override
    protected RvViewHolder<QuestionBean> getViewHolder(int viewType, View view) {
        return new KnowledgeViewHolder(view);
    }

    class KnowledgeViewHolder extends RvViewHolder<QuestionBean> {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_title_name)
        TextView tvTitleName;
        @BindView(R.id.tv_eyes)
        TextView tvEyes;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.container)
        LinearLayout container;
        @BindView(R.id.tv_status)
        TextView tvStatusName;


        public KnowledgeViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, QuestionBean bean) {
            ImageLoader.getInstance().displayImage(bean.getImage(), iv, BaseApplication
                    .getDisplayImageOptions(R.mipmap.ic_pic_default));
            tvTitleName.setText(bean.getTitle());
            tvEyes.setText(String.format("%d", bean.getViewNum()));
            tvTime.setText(bean.getQuestionDate());

            tvStatusName.setText(getStatusName(bean.getStatus()));
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowQuestionActivity.start(getContext(),bean.getId());
                }
            });
        }

        private String getStatusName(String status){
            String str= "未办理";
            if (TextUtils.equals(status,"1")){
                str = "未办理";
            }else if (TextUtils.equals(status,"2")){
                str ="已办理";
            }else if (TextUtils.equals(status,"3")){
                str ="已回复";
            }
            return str;
        }
    }
}
