package com.ww.android.governmentheart.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.ww.android.governmentheart.BuildConfig;

import java.io.File;

public class DownLoadCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
            //在广播中取出下载任务的id
            long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            Toast.makeText(context, "编号：" + id + "的下载任务已经完成！", Toast.LENGTH_SHORT).show();
            DownloadManager.Query query = new DownloadManager.Query();
            DownloadManager dm = (DownloadManager) context.getSystemService(Context
                    .DOWNLOAD_SERVICE);
            query.setFilterById(id);
            Cursor c = dm.query(query);
            if (c != null) {
                try {
                    if (c.moveToFirst()) {
                        //获取文件下载路径
                        String filename = c.getString(c.getColumnIndex(DownloadManager
                                .COLUMN_LOCAL_FILENAME));
                        int status = c.getInt(c.getColumnIndexOrThrow(DownloadManager
                                .COLUMN_STATUS));
                        if (status == DownloadManager.STATUS_SUCCESSFUL) {
                            installAPK(context, new File(filename));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                } finally {
                    c.close();
                }

            }
        }
    }

    private void installAPK(Context mContext, File mFile) {
        //获取下载文件的Uri
        if (mFile != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //+ File.separator + name
                Log.e("file path", mFile.getAbsolutePath());
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                Uri apkUri = FileProvider.getUriForFile(mContext, BuildConfig.APPLICATION_ID + ".fileprovider", mFile);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(mFile), "application/vnd.android.package-archive");
            }
            mContext.startActivity(intent);
        }
    }
}
