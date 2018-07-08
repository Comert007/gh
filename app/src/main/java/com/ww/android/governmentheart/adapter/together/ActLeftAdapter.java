package com.ww.android.governmentheart.adapter.together;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.bean.together.ActLeftBean;

import butterknife.BindView;
import ww.com.core.adapter.RvAdapter;
import ww.com.core.adapter.RvViewHolder;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class ActLeftAdapter extends RvAdapter<ActLeftBean> {

    private OnMenuChangeListener mOnMenuChangeListener;

    public void setOnMenuChangeListener(OnMenuChangeListener onMenuChangeListener) {
        mOnMenuChangeListener = onMenuChangeListener;
    }

    public ActLeftAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemLayoutResId(int viewType) {
        return R.layout.adapter_act;
    }

    @Override
    protected RvViewHolder<ActLeftBean> getViewHolder(int viewType, View view) {
        return new ActViewHolder(view);
    }


    class ActViewHolder extends RvViewHolder<ActLeftBean>{

        @BindView(R.id.vi_left)
        View viLeft;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.ll_container)
        LinearLayout container;

        public ActViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onBindData(int position, ActLeftBean bean) {
            tvName.setText(bean.name);
            viLeft.setSelected(bean.isSelected);
            tvName.setSelected(bean.isSelected);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeMenu(position);
                    if (mOnMenuChangeListener!=null){
                        mOnMenuChangeListener.onMenuChange(position);
                    }
                }
            });
        }
    }

    public void changeMenu(int position) {
        for (ActLeftBean bean : getList()) {
            bean.isSelected = false;
        }
        getList().get(position).isSelected = true;
        notifyDataSetChanged();
    }

    public interface OnMenuChangeListener {
        void onMenuChange(int position);
    }


}
