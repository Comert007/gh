package com.ww.android.governmentheart.fragment;

import android.widget.Button;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.VoidView;

import butterknife.BindColor;
import butterknife.BindView;

/**
 * @Author feng
 * @Date 2018/6/10
 */
public class JoinFragment extends BaseFragment<VoidView,VoidModel> {
    @BindView(R.id.btn_title_left)
    Button btnTitleLeft;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindColor(R.color.color_black)
    int colorBlack;


    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_join;
    }

    @Override
    protected void init() {

    }

}
