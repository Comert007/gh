package com.ww.android.governmentheart.network.api;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.together.ActBean;
import com.ww.android.governmentheart.mvp.bean.together.OnlineBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author feng
 * @Date 2018/7/4.
 */
public interface TogetherApi {

    /**
     * 活动列表
     * @param body
     * @return
     */
    @POST("act")
    Observable<ResponseBean<PageListBean<ActBean>>> act(@Body RequestBody body);


    /**
     * 活动报名
     * @param body
     * @return
     */
    @POST("joinact")
    Observable<ResponseBean<String>> joinAct(@Body RequestBody body);



    /**
     * 直播列表
     * @return
     */
    @POST("liveList")
    Observable<ResponseBean<PageListBean<OnlineBean>>> online(@Body RequestBody body);


    /**
     * 直播详情
     * @return /getLiveById?args={"id":"31076f197eca499fbedc6130d958b14a"}  直播详情
     */
    @POST("getLiveById")
    Observable<ResponseBean<PageListBean<String>>> onlineDetail(@Body RequestBody body);


}
