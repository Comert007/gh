package com.ww.android.governmentheart.activity.base;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.RelativeLayout;

import com.tencent.smtt.sdk.TbsReaderView;
import com.ww.android.governmentheart.R;
import com.ww.android.governmentheart.activity.BaseActivity;
import com.ww.android.governmentheart.config.type.ImmersionType;
import com.ww.android.governmentheart.mvp.model.wisdom.WisdomModel;
import com.ww.android.governmentheart.mvp.vu.base.VoidView;
import com.ww.android.governmentheart.utils.permission.CustomPermissionCallback;
import com.ww.android.governmentheart.utils.permission.PermissionHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import me.weyye.hipermission.PermissionItem;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ww.com.core.Debug;

/**
 * @author feng
 * @Date 2018/7/14
 */
public class OnLineReadActivity extends BaseActivity<VoidView, WisdomModel> implements
        TbsReaderView.ReaderCallback {

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    private TbsReaderView mTbsReaderView;
    private String title;
    private String url;

    public static void start(Context context, String title, String url) {
        Intent intent = new Intent(context, OnLineReadActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_online_read;
    }

    @Override
    protected void init() {
        initData();
        initWeb();
        downloadFile();
    }

    private void initData() {
        title = getIntent().getStringExtra("title");
        url = getIntent().getStringExtra("url");
        setTitleText(title);
    }


    private void initWeb() {
        mTbsReaderView = new TbsReaderView(this, this);
        rlRoot.addView(mTbsReaderView, new RelativeLayout.LayoutParams(RelativeLayout
                .LayoutParams.MATCH_PARENT,  RelativeLayout.LayoutParams.MATCH_PARENT));

    }


    @Override
    protected int initDefaultImmersionType() {
        return ImmersionType.WHITE;
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {

    }


    private void download() {
        m.download(url, new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.body() != null) {
                    boolean writeSuccess = writeResponseBodyToDisk(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


    private void openFile(File file) {
        Bundle bundle = new Bundle();
        String path = file.getPath();
        Debug.d("path:"+path);
        bundle.putString(TbsReaderView.KEY_FILE_PATH, path);
        bundle.putString(TbsReaderView.KEY_TEMP_PATH, Environment.getExternalStorageDirectory().getPath());
        boolean result = mTbsReaderView.preOpen(parseFormat(path), false);
        Debug.d("result:"+result);
        if (result) {
            mTbsReaderView.openFile(bundle);
        }
    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
//        String suffix = url.substring(url.lastIndexOf("."));
        String filename = url.substring(url.lastIndexOf(File.separator) + 1);
        String cacheDir = Environment.getExternalStorageDirectory().getPath();
        String filepath = cacheDir + File.separator + filename;
        try {
            // todo change the file location/name according to your needs
            File file = new File(filepath);
            if (file.exists()) {
                openFile(file);
                return true;
            } else {
                InputStream inputStream = null;
                OutputStream outputStream = null;

                try {
                    byte[] fileReader = new byte[4096];

                    long fileSize = body.contentLength();
                    long fileSizeDownloaded = 0;

                    inputStream = body.byteStream();
                    outputStream = new FileOutputStream(file);

                    while (true) {
                        int read = inputStream.read(fileReader);

                        if (read == -1) {
                            break;
                        }

                        outputStream.write(fileReader, 0, read);

                        fileSizeDownloaded += read;
                    }
                    outputStream.flush();
                    openFile(file);
                    return true;
                } catch (IOException e) {
                    return false;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }

                    if (outputStream != null) {
                        outputStream.close();
                    }
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    private void downloadFile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<PermissionItem> permissions = new ArrayList<>();
            permissions.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "存储", R
                    .drawable.permission_ic_storage));
            PermissionHelper.startMultiPermission(this, permissions, new CustomPermissionCallback
                    () {
                @Override
                public void onFinish() {
                    super.onFinish();
                    download();
                }
            });
        } else {
            download();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTbsReaderView.onStop();
    }
}
