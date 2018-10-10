package com.ww.android.governmentheart.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import com.ww.android.governmentheart.mvp.bean.event.UpdateAppEvent;
import com.ww.android.governmentheart.utils.rxbus.OnListener;
import com.ww.android.governmentheart.utils.rxbus.RxBusHelper;

import java.io.File;

import io.reactivex.disposables.CompositeDisposable;
import ww.com.core.Debug;

public class InitApkBroadCastReceiver extends BroadcastReceiver {


    private CompositeDisposable mDisposable;
    private String path;
    public InitApkBroadCastReceiver() {
        initSubscribe();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction())) {
            removeFile(context,path);
            Debug.d("监听到系统广播添加");
        }
        if (Intent.ACTION_PACKAGE_REMOVED.equals(intent.getAction())) {
            removeFile(context,path);
            Debug.d("监听到系统广播移除");
            System.out.println("");
        }
        if (Intent.ACTION_PACKAGE_REPLACED.equals(intent.getAction())) {
            removeFile(context,path);
            Debug.d("监听到系统广播替换");
        }
    }

    private void initSubscribe(){
        if (mDisposable == null){
            mDisposable = new CompositeDisposable();
        }
        RxBusHelper.doOnChildThread(UpdateAppEvent.class, mDisposable, new OnListener<UpdateAppEvent>() {

            @Override
            public void onEvent(UpdateAppEvent updateAppEvent) {
                path = updateAppEvent.getUrl();
            }
        });
    }

    public File getPathFile(Context context,String path) {
        String apkName = path.substring(path.lastIndexOf("/"));
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File
                .separator + apkName);
        return file;
    }

    public void removeFile(Context context, String path) {
        File file = getPathFile(context,path);
        if (file.exists()){
            file.delete();
        }
    }

}
