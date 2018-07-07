package com.ww.android.governmentheart.network.api;

import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author feng
 * @Date 2018/7/4.
 */
public interface HeartApi {

    @POST("news")
    Observable<ResponseBean<List<NewsBean>>> news(@Body RequestBody body);
}
