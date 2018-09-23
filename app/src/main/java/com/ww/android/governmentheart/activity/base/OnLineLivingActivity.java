package com.ww.android.governmentheart.activity.base;

import android.content.Context;
import android.content.Intent;
import android.view.SurfaceView;

import com.alivc.player.AliVcMediaPlayer;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.model.VoidModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;

import butterknife.BindView;

/**
 * @author feng
 * @Date 2018/7/16.
 */
public class OnLineLivingActivity extends BaseActivity<VoidView,VoidModel> {

    @BindView(R.id.surface_view)
    SurfaceView mSurfaceView;

    private AliVcMediaPlayer mPlayer;

    private String url;

    public static void start(Context context,String url) {
        Intent intent = new Intent(context, OnLineLivingActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_online_living;
    }

    @Override
    protected void init() {
        url =getIntent().getStringExtra("url");
        createPlayer();
    }

    private void createPlayer(){
        //初始化播放器（只需调用一次即可，建议在application中初始化）
        AliVcMediaPlayer.init(getApplicationContext());
        //创建播放器的实例
        mPlayer = new AliVcMediaPlayer(this, mSurfaceView);
        mPlayer.prepareAndPlay(url);
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

}
