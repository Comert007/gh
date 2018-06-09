package com.ww.android.governmentheart.mvp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ww.android.governmentheart.mvp.manager.IUserInfo;
import com.ww.android.governmentheart.mvp.manager.IUserInfoManager;
import com.ww.android.governmentheart.mvp.manager.UserInfoDefaultManger;

import java.util.List;
import java.util.Stack;

/**
 * @Author feng
 * @Date 2018/6/9
 */
public class WWApplication extends Application implements IUserInfoManager {

    private Stack<Activity> runActivity = new Stack<>();
    private IUserInfoManager userInfoManager;

    @Override
    public void onCreate() {
        super.onCreate();
        initUserInfoManager();
    }

    protected void initUserInfoManager() {
        userInfoManager = new UserInfoDefaultManger<>(this);
    }

    @NonNull
    public IUserInfoManager getUserInfoManager() {
        return userInfoManager;
    }

    public void addRunActivity(Activity activity) {
        runActivity.push(activity);
    }

    public void removeRunActivity(Activity activity) {
        runActivity.remove(activity);
    }


    public void clearTopTask(Activity activity) {
        if (this.runActivity != null) {
            for (Activity act : this.runActivity) {
                if (activity != act) {
                    act.finish();
                }
            }
        }
    }

    public Activity getTopTask() {
        return runActivity.peek();
    }

    public void exitApp() {
        if (this.runActivity != null) {
            for (Activity act : this.runActivity) {
                act.finish();
            }
        }

    }

    /**
     * 是否是主进程
     *
     * @return
     */
    public boolean isMainProcess() {
        ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = getPackageName();
        int myPid = android.os.Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是UI进程状态
     * <pre>
     * 在聊天类型的App中，常常需要常驻后台来不间断的获取服务器的消息，这就需要我们把Service设置成START_STICKY，
     * kill 后会被重启（等待5秒左右）来保证Service常驻后台。
     * 如果Service设置了这个属性，这个App的进程就会被判断是前台，
     * 代码上的表现就是appProcess.importance的值永远是
     * ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND，这样就永远无法判断出到底哪个是前台了。
     * </pre>
     *
     * @return
     */
    public boolean isForeground() {
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String myPackage = getPackageName();
        if (processInfos != null) {
            for (ActivityManager.RunningAppProcessInfo processInfo : processInfos) {
                if (myPackage.equals(processInfo.processName)
                        && processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void saveUserInfo(IUserInfo userBean) {
        userInfoManager.saveUserInfo(userBean);
    }

    @Override
    public void clearUserInfo() {
        userInfoManager.clearUserInfo();
    }

    @Override
    public IUserInfo getUserInfo() {
        return userInfoManager.getUserInfo();
    }

    @Override
    public boolean isLogin() {
        return userInfoManager.isLogin();
    }

    @Override
    public String getToken() {
        return userInfoManager.getToken();
    }
}
