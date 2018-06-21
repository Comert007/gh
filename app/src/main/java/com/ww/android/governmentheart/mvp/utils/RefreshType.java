package com.ww.android.governmentheart.mvp.utils;

/**
 * @author feng
 * @Date 2018/6/21.
 */
public interface RefreshType {
    /**
     * 是否能刷新并加载更多
     */
    int ENABLE = 1;
    /**
     * 是否仅刷新不加载更多
     */
    int REFRESH = 2;
    /**
     * 是否仅加载更多不刷新
     */
    int LOAD_MORE = 3;
    /**
     * 不启用刷新功能
     */
    int NOT_ENABLE = 0;
}
