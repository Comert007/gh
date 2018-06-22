package com.ww.android.governmentheart.mvp.vu.home;

import android.view.ViewGroup;
import android.widget.TextView;

import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.mvp.vu.RefreshView;
import com.ww.android.governmentheart.utils.DensityUtil;
import com.ww.android.governmentheart.widget.FluidLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author feng
 * @Date 2018/6/22.
 */
public class SearchView extends RefreshView {
    @BindView(R.id.layout_fluid)
    FluidLayout fluidLayout;


    public void initFluid(List<String> strings) {
        if (strings == null || strings.size() == 0) {
            return;
        }
        fluidLayout.removeAllViews();
        List<TextView> viewList = new ArrayList<>();

        for (String string : strings) {
            TextView tv = new TextView(activity);
            tv.setBackgroundResource(R.drawable.shape_fluid);
            int padding = DensityUtil.dp2px(8);
            tv.setPadding(padding,padding,padding,padding);
            tv.setTextSize(12);
            tv.setText(string);
            viewList.add(tv);


            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            int margin = DensityUtil.dp2px(9);
            params.setMargins(margin, 0, margin, margin);
            fluidLayout.addView(tv, params);
        }
    }
}
