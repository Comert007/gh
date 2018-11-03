package com.ww.android.governmentheart.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.BuildConfig;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.fragment.HeartFragment;
import com.ww.android.governmentheart.fragment.HomeFragment;
import com.ww.android.governmentheart.fragment.StyleFragment;
import com.ww.android.governmentheart.fragment.TogetherFragment;
import com.ww.android.governmentheart.fragment.WisdomFragment;
import com.ww.android.governmentheart.fragment.work.WorkFragment;
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.event.UpdateAppEvent;
import com.ww.android.governmentheart.mvp.bean.login.VersionBean;
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.utils.DialogUtils;
import com.ww.android.governmentheart.utils.ToastUtils;
import com.ww.android.governmentheart.utils.permission.CustomPermissionCallback;
import com.ww.android.governmentheart.utils.permission.PermissionHelper;
import com.ww.android.governmentheart.utils.rxbus.RxBusHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindViews;
import me.weyye.hipermission.PermissionItem;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ww.com.core.Debug;
import ww.com.core.adapter.MenuTabAdapter;
import ww.com.core.utils.PhoneUtils;

public class MainActivity extends BaseActivity<VoidView, MainModel> {

    @BindViews({R.id.tab_home_layout, R.id.tab_heart_layout, R.id.tab_together_layout, R.id
            .tab_style_layout, R.id.tab_wisdom_layout,R.id.tab_work_layout})
    List<View> menus;
    @BindViews({R.id.tab_home_image, R.id.tab_heart_image, R.id.tab_together_image, R.id
            .tab_style_image, R.id.tab_wisdom_image,R.id.tab_work_image})
    List<View> images;
    @BindViews({R.id.tab_home_text, R.id.tab_heart_text, R.id.tab_together_text, R.id
            .tab_style_text, R.id.tab_wisdom_text,R.id.tab_work_text})
    List<View> texts;

    private MenuTabAdapter adapter;
    private List<Fragment> fragments;
    private int REQUEST_CODE_APP_INSTALL = 0x22;

    private File mFile;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        addFragments();
        onUpdate();
    }

    private void addFragments() {
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new HeartFragment());
        fragments.add(new TogetherFragment());
        fragments.add(new StyleFragment());
        fragments.add(new WisdomFragment());
        fragments.add(new WorkFragment());
        addCheck();

        changeStatus(0);
        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
    }

    private void addCheck() {
        adapter = new MenuTabAdapter(this, menus, fragments, R.id.main_content);
        adapter.setOnMenuClickListener(new MenuTabAdapter.OnMenuClickListener() {
            @Override
            public void onDoubleClick(View view) {

            }

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.tab_home_layout:
                        changeStatus(0);
                        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                                .colorPrimary).init();
                        break;
                    case R.id.tab_heart_layout:
                        changeStatus(1);
                        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                                .colorPrimary).init();
                        break;
                    case R.id.tab_together_layout:
                        changeStatus(2);
                        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                                .colorPrimary).init();
                        break;
                    case R.id.tab_style_layout:
                        changeStatus(3);
                        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                                .colorPrimary).init();
                        break;
                    case R.id.tab_wisdom_layout:
                        changeStatus(4);
                        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                                .colorPrimary).init();
                        break;
                    case R.id.tab_work_layout:
                        changeStatus(5);
                        mImmersionBar.fitsSystemWindows(true).statusBarColor(R.color
                                .colorPrimary).init();
                        break;
                }
            }
        });

    }


    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.keyboardEnable(true).init();
    }

    @Override
    protected boolean isDefaultImmersionBar() {
        return false;
    }

    private void changeStatus(int index) {
        adapter.changeMenuStatus(index);
        changeMenuStatus(index);
        adapter.changeMenu(index);
    }


    public void changeMenuStatus(int index) {
        int imageSize = images.size();
        for (int i = 0; i < imageSize; i++) {
            if (i == index) {
                this.images.get(i).setSelected(true);
                this.texts.get(i).setSelected(true);
            } else {
                this.images.get(i).setSelected(false);
                this.texts.get(i).setSelected(false);
            }
        }
    }


    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 3000) {
                Toast.makeText(getApplicationContext(), "再按一次退出应用", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                BaseApplication.getInstance().clearTopTask(MainActivity.this);
                onBackPressed();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 判断是否有更新
     */
    private void onUpdate() {
        m.onUpdate(new HashMap(), new BaseObserver<VersionBean>(this, bindToLifecycle()) {
            @Override
            protected void onSuccess(@Nullable VersionBean versionBean, @Nullable
                    List<VersionBean> list, @Nullable PageBean<VersionBean> page) {
                String ver = PhoneUtils.getAppVer(MainActivity.this);
                String version = versionBean.getVersion();
                Debug.e("version:"+version+", ver:"+ver+",version.compareTo(ver):"+version.compareTo(ver));
                if (version.compareTo(ver) > 0) {
//                    showDialog(versionBean.getDonwLoadUrl());
                    update(versionBean.getDonwLoadUrl());
                }
            }
        });
    }


    private void update(String url) {
        final String[] onUrl = {url};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionHelper.startSinglePermission(this, new PermissionItem(Manifest.permission
                    .WRITE_EXTERNAL_STORAGE, "存储", R
                    .drawable.permission_ic_storage), new CustomPermissionCallback() {
                @Override
                public void onFinish() {
                    super.onFinish();
                    download(onUrl[0]);
                }
            });
        } else {
            download(url);
        }

    }

    /**
     * 显示更新提示
     */
    private void showDialog(File file) {
        DialogUtils.showDialog(MainActivity.this, "更新提示", "有最新版，是否进行安装？",
                "确定", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    installPermission(file);
                });
    }

    private void download(String downloadUrl) {
        if (!downloadUrl.endsWith(".apk")){
            ToastUtils.showToast("下载文件不是App应用，不能进行更新");
            return;
        }
        RxBusHelper.post(new UpdateAppEvent(downloadUrl));
        String name = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1, downloadUrl.length());
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File
                .separator + name);

        if (file.exists()){
           showDialog(file);
           return;
        }

        HttpRequest.loginApi().downloadFileWithDynamicUrlSync(downloadUrl)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody>
                            response) {

                        File writtenToDisk = writeResponseBodyToDisk(response.body(), name);
                        mFile = writtenToDisk;
                        showDialog(mFile);
                        Debug.d("file download was a success? " + writtenToDisk.exists());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.fillInStackTrace();
                    }
                });
    }


    private File writeResponseBodyToDisk(ResponseBody body, String name) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(Environment
                    .DIRECTORY_DOWNLOADS) + File.separator + name);
            Debug.d("file path:" + futureStudioIconFile.getPath());
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                }


                Debug.d("file download: " + fileSizeDownloaded + " of " + fileSize);

                outputStream.flush();

                return futureStudioIconFile;
            } catch (IOException e) {
                return null;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    private void installPermission(File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean hasInstallPermission = isHasInstallPermissionWithO(MainActivity.this);
            if (!hasInstallPermission) {
                mFile = file;
                startInstallPermissionSettingActivity(MainActivity.this);
                return;
            }
            installAPK(MainActivity.this, file);
        } else {
            installAPK(MainActivity.this, file);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private boolean isHasInstallPermissionWithO(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void startInstallPermissionSettingActivity(Context context) {
        if (context == null) {
            return;
        }
        DialogUtils.showDialog(MainActivity.this, "提示", "需打开未知来源安装权限,才能进行安装", "确定", new
                DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                ((Activity) context).startActivityForResult(intent, REQUEST_CODE_APP_INSTALL);
            }
        }, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }, false);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_APP_INSTALL) {
            installAPK(MainActivity.this, mFile);
        }
    }

    private void installAPK(Context mContext, File file) {

        Intent var2 = new Intent();
        var2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        var2.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uriForFile = FileProvider.getUriForFile(MainActivity.this, BuildConfig
                    .APPLICATION_ID + ".fileprovider", file);
            var2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            var2.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        } else {
            var2.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        try {
            mContext.startActivity(var2);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(mContext, "没有找到打开此类文件的程序", Toast.LENGTH_SHORT).show();
        }
    }
}
