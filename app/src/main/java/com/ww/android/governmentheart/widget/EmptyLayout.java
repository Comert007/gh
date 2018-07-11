package com.ww.android.governmentheart.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.IntDef;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ww.android.governmentheart.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmptyLayout extends FrameLayout {

    public static final int STATUS_HIDE = 1001;
    public static final int STATUS_LOADING = 1;
    public static final int STATUS_NO_NET = 2;
    public static final int STATUS_NO_DATA = 3;
    private Context mContext;
    private OnRetryListener mOnRetryListener;
    private int mEmptyStatus = STATUS_LOADING;
    private int mBgColor;

    @BindView(R.id.tv_net_error)
    TextView mTvEmptyMessage;
    @BindView(R.id.rl_empty_container)
    View mRlEmptyContainer;
    @BindView(R.id.empty_loading)
    LinearLayout mEmptyLoading;
    @BindView(R.id.empty_layout)
    FrameLayout mEmptyLayout;
    @BindView(R.id.tv_net_error_icon)
    IconFontTextView mIconFontTextView;

    @BindView(R.id.tv_xuanzhuan)
    ImageView tvXuanZhuan;

    @BindView(R.id.tv_net_reload)
    TextView tvReload;

    public EmptyLayout(Context context) {
        this(context, null);
    }

    public EmptyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init(attrs);
    }

    /**
     * 初始化
     */
    private void init(AttributeSet attrs) {
        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.EmptyLayout);
        try {
            mBgColor = a.getColor(R.styleable.EmptyLayout_background_color, Color.WHITE);
        } finally {
            a.recycle();
        }
        View.inflate(mContext, R.layout.layout_empty_loading, this);
        ButterKnife.bind(this);
        mEmptyLayout.setBackgroundColor(mBgColor);
//        _switchEmptyView();

        setOnTouchListener(new OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    /**
     * 隐藏视图
     */
    public void hide() {
        mEmptyStatus = STATUS_HIDE;
        _switchEmptyView();
    }

    /**
     * 设置状态
     *
     * @param emptyStatus
     */
    public void setEmptyStatus(@EmptyStatus int emptyStatus) {
        mEmptyStatus = emptyStatus;
        _switchEmptyView();
    }

    /**
     * 获取状态
     *
     * @return 状态
     */
    public int getEmptyStatus() {
        return mEmptyStatus;
    }

    /**
     * 设置异常消息
     *
     * @param msg 显示消息
     */
    public void setEmptyMessage(String msg) {
        mTvEmptyMessage.setText(msg);
    }

    public void hideErrorIcon() {
        mTvEmptyMessage.setCompoundDrawables(null, null, null, null);
    }


    /**
     * 切换视图
     */
    private void _switchEmptyView() {
        switch (mEmptyStatus) {
            case STATUS_LOADING:
                setVisibility(VISIBLE);
                mRlEmptyContainer.setVisibility(GONE);
                mEmptyLoading.setVisibility(VISIBLE);
                startRotate();
                break;
            case STATUS_NO_DATA:
                setVisibility(VISIBLE);
                stopRotate();
                showNoData();
                break;
            case STATUS_NO_NET:
                setVisibility(VISIBLE);
                mEmptyLoading.setVisibility(GONE);
                tvReload.setVisibility(View.VISIBLE);
                mRlEmptyContainer.setVisibility(VISIBLE);
                mIconFontTextView.setText("\ue6a3");
                stopRotate();
                break;
            case STATUS_HIDE:
                setVisibility(GONE);
                stopRotate();
                break;
        }
    }

    private void showNoData(){
        mEmptyLoading.setVisibility(GONE);
        mRlEmptyContainer.setVisibility(VISIBLE);
        mIconFontTextView.setText("\ue6a2");
        mTvEmptyMessage.setText("没有更多了哦~");
    }

    public void switchNoda(String hintWenzi, String hintTubiao, OnClickListener onClickListener) {
        setVisibility(VISIBLE);
        mEmptyStatus = STATUS_NO_DATA;
        mEmptyLoading.setVisibility(GONE);
        mRlEmptyContainer.setVisibility(VISIBLE);
        mIconFontTextView.setText(hintTubiao);
        mTvEmptyMessage.setText(hintWenzi);
        tvReload.setVisibility(View.GONE);
        mRlEmptyContainer.setOnClickListener(onClickListener);
    }

    /**
     * 设置重试监听器
     *
     * @param retryListener 监听器
     */
    public void setRetryListener(OnRetryListener retryListener) {
        this.mOnRetryListener = retryListener;
    }

    @OnClick(R.id.tv_net_reload)
    public void onClick() {
        if (mOnRetryListener != null) {
            mOnRetryListener.onRetry();
        }
    }

    /**
     * 点击重试监听器
     */
    public interface OnRetryListener {
        void onRetry();
    }

    RotateAnimation rotate;

    public void startRotate() {
        rotate = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        LinearInterpolator lin = new LinearInterpolator();
        rotate.setInterpolator(lin);
        rotate.setDuration(600);//设置动画持续时间
        rotate.setRepeatCount(-1);//设置重复次数
        rotate.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        //执行前的等待时间
        tvXuanZhuan.setAnimation(rotate);
        tvXuanZhuan.startAnimation(rotate);
    }

    public void stopRotate() {
        tvXuanZhuan.clearAnimation();
        if (rotate != null)
            rotate.cancel();
        rotate = null;
    }


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({STATUS_LOADING, STATUS_NO_NET, STATUS_NO_DATA})
    public @interface EmptyStatus {
    }
}
