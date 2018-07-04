package com.ww.android.governmentheart.network.utils;

import android.content.Context;

import java.io.File;

import okhttp3.Cache;

public class Utils {
    public static File getDiskCacheDir(Context context, String uniqueName) {
        return new File(context.getCacheDir(), uniqueName);
    }

    public static Cache getCache(Context context, int maxCacheSize, String uniqueName) {
        return new Cache(getDiskCacheDir(context, uniqueName), maxCacheSize);
    }
}