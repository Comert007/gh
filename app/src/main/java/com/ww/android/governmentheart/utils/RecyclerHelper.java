package com.ww.android.governmentheart.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ww.android.governmentheart.R;

/**
 * @Author feng
 * @Date 2018/6/16
 */
public class RecyclerHelper {

    public static DividerItemDecoration defalutMoreDecoration(Context context){
        DividerItemDecoration decoration = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.shape_divider_more));
        return decoration;
    }

    public static DividerItemDecoration defalutSingleDecoration(Context context){
        DividerItemDecoration decoration = new DividerItemDecoration(context,DividerItemDecoration.VERTICAL);
        decoration.setDrawable(ContextCompat.getDrawable(context, R.drawable.shape_divider_single));
        return decoration;
    }


    public static RecyclerView.LayoutManager defaultManager(Context context){
        return new LinearLayoutManager(context);
    }

}
