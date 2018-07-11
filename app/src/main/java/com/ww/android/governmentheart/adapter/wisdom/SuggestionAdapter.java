package com.ww.android.governmentheart.adapter.wisdom;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.wisdom.ShowAdviceActivity;
import com.ww.android.governmentheart.mvp.bean.wisdom.SuggestBean;

import butterknife.BindColor;
import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;
import ww.com.core.utils.TimeUtils;

/**
 * @Author feng
 * @Date 2018/6/17
 */
public class SuggestionAdapter extends RvAdapter<SuggestBean> {

    public SuggestionAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_suggestion;
    }

    @Override
    protected RvViewHolder<SuggestBean> getViewHolder(int viewType, View view) {
        return new SuggestionViewHolder(view);
    }

    class SuggestionViewHolder extends RvViewHolder<SuggestBean> {
        @BindView(R.id.vi_line)
        View viLine;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_username)
        TextView tvUserName;
        @BindView(R.id.tv_deal_time)
        TextView tvDealTime;
        @BindView(R.id.tv_deal_sign)
        TextView tvDealSign;
        @BindView(R.id.tv_view_num)
        TextView tvViewNum;
        @BindView(R.id.tv_comment_num)
        TextView tvCommentNum;
        @BindView(R.id.container_detail)
        LinearLayout containerDetail;

        @BindColor(R.color.color_text_unprocessed)
        int colorUnProcessed;
        @BindColor(R.color.color_text_processing)
        int colorProcessing;
        @BindColor(R.color.color_text_processed)
        int colorProcessed;


        public SuggestionViewHolder(View itemView) {
            super(itemView);
            viLine.setTranslationX(-5f);
        }

        @Override
        public void onBindData(int position, SuggestBean bean) {
            tvTitle.setText(bean.getTitle());
            tvTime.setText(TimeUtils.milliseconds2String(bean.getAcceptDate()));
            if (!bean.getStatus().equals("0")){
                tvUserName.setText(bean.getUserAgent());
                tvDealTime.setText(TimeUtils.milliseconds2String(bean.getProposalDate()));
            }else {
                tvUserName.setText("");
                tvDealTime.setText("");
            }
            tvViewNum.setText(bean.getViewNum());
            tvCommentNum.setText(bean.getCommentNum());
            showStatus(bean);
            containerDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShowAdviceActivity.start(getContext(),bean.getId());
                }
            });
        }


        //0(未办理) 1（办理中） 2（已回复） ,3 已关闭
        private void showStatus(SuggestBean bean){
           switch (bean.getStatus()){
               case "0":
                   tvDealSign.setTextColor(colorUnProcessed);
                   tvDealSign.setText("未办理");
                   break;
               case "1":
                   tvDealSign.setTextColor(colorProcessing);
                   tvDealSign.setText("办理中");
                   break;
               case "2":
                   tvDealSign.setTextColor(colorProcessed);
                   tvDealSign.setText("已回复");
                   break;
               case "3":
                   tvDealSign.setTextColor(colorUnProcessed);
                   tvDealSign.setText("已关闭");
                   break;
           }
        }
    }


}
