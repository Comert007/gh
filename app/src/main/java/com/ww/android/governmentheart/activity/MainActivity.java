package com.ww.android.governmentheart.activity;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.Nullable;
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
import com.ww.android.governmentheart.mvp.bean.PageBean;
import com.ww.android.governmentheart.mvp.bean.login.VersionBean;
import com.ww.android.governmentheart.mvp.model.base.MainModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.utils.DialogUtils;
import com.ww.android.governmentheart.utils.permission.CustomPermissionCallback;
import com.ww.android.governmentheart.utils.permission.PermissionHelper;

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
            .tab_style_layout, R.id
            .tab_wisdom_layout})
    List<View> menus;
    @BindViews({R.id.tab_home_image, R.id.tab_heart_image, R.id.tab_together_image, R.id
            .tab_style_image, R.id
            .tab_wisdom_image})
    List<View> images;
    @BindViews({R.id.tab_home_text, R.id.tab_heart_text, R.id.tab_together_text, R.id
            .tab_style_text, R.id
            .tab_wisdom_text})
    List<View> texts;

    private MenuTabAdapter adapter;
    private List<Fragment> fragments;
    private DownloadManager downloadManager;

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
                if (version.compareTo(ver) > 0) {
                    showDialog(versionBean.getDonwLoadUrl());
                }
            }
        });
    }

    /**
     * 显示更新提示
     */
    private void showDialog(String url) {
        DialogUtils.showDialog(MainActivity.this, "更新提示", "有最新版本，是否进行下载更新？",
                "确定", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    update(url);
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
                    //http://ucdl.25pp.com/fs01/union_pack/Wandoujia_363553_wap_app_article_download.apk
//                    onUrl[0] = "https://pro-bd.fir.im/1ee670a7497aa0ec3027a9ac2115625c1bddba41.apk?auth_key=1537804803-0-9b1583649b4a4026bdaa53857e31cd6e-72bc3ed0895e3fed45825117fd02e894";
                    download(onUrl[0]);
                }
            });
        } else {
            download(url);
        }

    }

    private void download(String downloadUrl) {
        String name = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1, downloadUrl.length());
//        File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + name);
//        if (file.exists()){
//            file.delete();
//        }
        HttpRequest.loginApi().downloadFileWithDynamicUrlSync(downloadUrl)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody>
                            response) {

                        File writtenToDisk = writeResponseBodyToDisk(response.body(),name);
                        installAPK(MainActivity.this,writtenToDisk);
                        Debug.d( "file download was a success? " + writtenToDisk.exists());
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.fillInStackTrace();
                    }
                });

        //创建request对象
//        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
//        //设置什么网络情况下可以下载
////        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
//        //设置通知栏的标题
//        request.setTitle("下载");
//        //设置通知栏的message
//        request.setDescription("心联汇正在下载.....");
//        //设置漫游状态下是否可以下载
//        request.setAllowedOverRoaming(false);
//        //设置文件存放目录
//        String name = downloadUrl.substring(downloadUrl.lastIndexOf("/") + 1, downloadUrl.length());
//        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, name);
//        //获取系统服务
//        downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
//        //进行下载
//        long id = downloadManager.enqueue(request);

    }



    private File writeResponseBodyToDisk(ResponseBody body,String name) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + File.separator + name);
            Debug.d("file path:"+futureStudioIconFile.getPath());
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


    private void installAPK(Context mContext, File file) {

        Intent var2 = new Intent();
        var2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        var2.setAction(Intent.ACTION_VIEW);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            Uri uriForFile = FileProvider.getUriForFile(MainActivity.this,  BuildConfig.APPLICATION_ID +".fileprovider", file);
            var2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            var2.setDataAndType(uriForFile,  "application/vnd.android.package-archive");
        }else{
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
