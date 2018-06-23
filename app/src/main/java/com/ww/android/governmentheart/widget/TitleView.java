package com.ww.android.governmentheart.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.util.DensityUtil;
import com.ww.android.governmentheart.R;

import butterknife.ButterKnife;
import ww.com.core.Debug;
import ww.com.core.ScreenUtil;

/**
 * @author feng
 * @Date 2017/12/21.
 */

public class TitleView extends RelativeLayout {

    public Button btnTitleRight;
    public Button btnTitleLeft;
    public TextView tvTitle;

    private Drawable titleRightIco;
    private Drawable titleLeftIco;
    private CharSequence titleRightText;
    private CharSequence titleLeftText;
    private CharSequence titleText;

    private int titleColor;
    private int titleLeftColor;
    private int titleRightColor;

    private int colorWhite;

    public TitleView(Context context) {
        super(context);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TitleView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void setTitle(CharSequence text) {
        tvTitle.setText(text);
    }

    public void setTitleLeftText(CharSequence text) {
        btnTitleLeft.setText(text);
    }

    public void setTitleLeftIcoRes(int icoRes) {
        Drawable drawable = ScreenUtil.scaleBoundsDrawable(getResources().getDrawable(icoRes));
        btnTitleLeft.setCompoundDrawables(drawable, null, null, null);
    }

    public void setTitleRightText(CharSequence text) {
        btnTitleRight.setText(text);
    }

    public void setTitleRightIcoRes(int icoRes) {
        Drawable drawable = ScreenUtil.scaleBoundsDrawable(getResources().getDrawable(icoRes));
        btnTitleRight.setCompoundDrawables(null, null, drawable, null);
    }

    public void setTitleRightClick(OnClickListener listener) {
        btnTitleRight.setOnClickListener(listener);
    }

    public void setTitleLeftClick(OnClickListener listener) {
        btnTitleLeft.setOnClickListener(listener);
    }

    public void setTitleColor(int titleColor) {
        tvTitle.setTextColor(titleColor);
    }

    public void setTitleLeftColor(int titleLeftColor) {
        btnTitleLeft.setTextColor(titleLeftColor);
    }

    public void setTitleRightColor(int titleRightColor) {
        btnTitleRight.setTextColor(titleRightColor);
    }

    private void init(Context context, AttributeSet attrs) {
        colorWhite = context.getResources().getColor(R.color.color_white);
        TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.TitleView);
        if (typedArray != null) {
            titleLeftIco = typedArray.getDrawable(R.styleable.TitleView_titleLeftIco);
            titleRightIco = typedArray.getDrawable(R.styleable.TitleView_titleRightIco);
            titleText = typedArray.getText(R.styleable.TitleView_titleText);
            titleLeftText = typedArray.getText(R.styleable.TitleView_titleLeftText);
            titleRightText = typedArray.getText(R.styleable.TitleView_titleRightText);
            titleColor = typedArray.getColor(R.styleable.TitleView_titleTextColor, colorWhite);
            titleLeftColor = typedArray.getColor(R.styleable.TitleView_titleLeftTextColor,
                    colorWhite);
            titleRightColor = typedArray.getColor(R.styleable.TitleView_titleRightTextColor,
                    colorWhite);
            typedArray.recycle();
        }


        inflate(getContext(), R.layout.view_title, this);
        btnTitleRight = ButterKnife.findById(this, R.id.btn_title_right);
        btnTitleLeft = ButterKnife.findById(this, R.id.btn_title_left);
        tvTitle = ButterKnife.findById(this, R.id.tv_title);

        if (titleLeftIco != null) {
            titleLeftIco.setBounds(0, 0, DensityUtil.dp2px(18), DensityUtil.dp2px(18));
            btnTitleLeft.setCompoundDrawables(titleLeftIco,
                    null, null, null);
        }

        if (titleRightIco != null) {
            titleRightIco.setBounds(0, 0, DensityUtil.dp2px(18), DensityUtil.dp2px(18));
            btnTitleRight.setCompoundDrawables(null, null, titleRightIco, null);
        }

        Debug.d("titleColor:"+titleColor);

        setTitle(titleText);
        setTitleLeftText(titleLeftText);
        setTitleRightText(titleRightText);
        setTitleColor(titleColor);
        setTitleLeftColor(titleLeftColor);
        setTitleRightColor(titleRightColor);
    }
}
