package com.ww.android.governmentheart.activity.base;

import android.content.Context;
import android.content.Intent;
import android.view.SurfaceView;

import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.MediaPlayer;
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
        url="http://tv.yizykj.com/abc/php.flv?auth_key=1537798325-0-0-c285596d4888f6a17bc1dc3393cbcb81";
        createPlayer();
    }

    private void createPlayer(){
        //创建播放器的实例
        mPlayer = new AliVcMediaPlayer(this, mSurfaceView);
        initListener();
        mPlayer.prepareAndPlay(url);
    }

    private void initListener(){
        mPlayer.setPreparedListener(new MediaPlayer.MediaPlayerPreparedListener() {
            @Override
            public void onPrepared() {
                //准备完成时触发
            }
        });
        mPlayer.setPcmDataListener(new MediaPlayer.MediaPlayerPcmDataListener() {
            @Override
            public void onPcmData(byte[] bytes, int i) {
                //音频数据回调接口，在需要处理音频时使用，如拿到视频音频，然后绘制音柱。
            }
        });
        mPlayer.setFrameInfoListener(new MediaPlayer.MediaPlayerFrameInfoListener() {
            @Override
            public void onFrameInfoListener() {
                //首帧显示时触发
            }
        });
        mPlayer.setErrorListener(new MediaPlayer.MediaPlayerErrorListener() {
            @Override
            public void onError(int i, String msg) {
                //错误发生时触发，错误码见接口文档
            }
        });
        mPlayer.setCompletedListener(new MediaPlayer.MediaPlayerCompletedListener() {
            @Override
            public void onCompleted() {
                //视频正常播放完成时触发
            }
        });
        mPlayer.setSeekCompleteListener(new MediaPlayer.MediaPlayerSeekCompleteListener() {
            @Override
            public void onSeekCompleted() {
                //视频seek完成时触发
            }
        });
        mPlayer.setStoppedListener(new MediaPlayer.MediaPlayerStoppedListener() {
            @Override
            public void onStopped() {

            }
        });
        mPlayer.setCircleStartListener(new MediaPlayer.MediaPlayerCircleStartListener(){
            @Override
            public void onCircleStart() {
                //循环播放开始
            }
        });
        //SEI数据回调
        mPlayer.setSEIDataListener(new MediaPlayer.MediaPlayerSEIDataListener() {
            @Override
            public void onSeiUserUnregisteredData(String data) {
                //解析SEI数据，在这里可以展示题目信息或答案信息
            }
        });
    }

    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

}
