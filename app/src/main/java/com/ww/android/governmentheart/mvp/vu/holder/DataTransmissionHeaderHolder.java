package com.ww.android.governmentheart.mvp.vu.holder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.base.OnLineReadActivity;
import com.ww.android.governmentheart.adapter.wisdom.ShowImageAdapter;
import com.ww.android.governmentheart.mvp.bean.wisdom.ImagePickBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionDetailBean;
import com.ww.android.governmentheart.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ww.com.core.widget.CustomRecyclerView;

public class DataTransmissionHeaderHolder {

    private ShowImageAdapter adapter;


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.crv)
    CustomRecyclerView mRecyclerView;
    @BindView(R.id.tv_images)
    TextView tvImages;
    @BindView(R.id.ll_file_layout)
    LinearLayout fileLayout;

    private ImagePickBean file;

    private Context mContext;
    private View mView;

    public DataTransmissionHeaderHolder(Context context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.adapter_data_transmission,null);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mView.setLayoutParams(lp);
        ButterKnife.bind(this,mView);

        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,4));
        adapter = new ShowImageAdapter((Activity) mContext);
        mRecyclerView.setAdapter(adapter);
    }

    public void showInfo(TransmissionDetailBean notifyEntity){
        tvTitle.setText(notifyEntity.getTitle());
        tvTime.setText(notifyEntity.getCreateDate());
        tvContent.setText(notifyEntity.getSummary());


    }

    public void showFile(List<ImagePickBean> docs,List<ImagePickBean> imgs){
        if (docs== null || docs.size() ==0){
            fileLayout.setVisibility(View.GONE);
        }else {
            fileLayout.setVisibility(View.VISIBLE);
        }
        if (imgs == null || imgs.size() ==0){
            tvImages.setVisibility(View.GONE);
        }else {
            tvImages.setVisibility(View.VISIBLE);
        }
        if (docs!=null && docs.size() >0){
            file = docs.get(0);
        }
        adapter.addList(imgs);
    }

    @OnClick(R.id.ll_file_layout)
    public void onClick(){
        if (file!=null){
            OnLineReadActivity.start(mContext, file.name, file.path);
        }else {
            ToastUtils.showToast("当前附件格式不支持");
        }
    }

    public View getView() {
        return mView;
    }
}
