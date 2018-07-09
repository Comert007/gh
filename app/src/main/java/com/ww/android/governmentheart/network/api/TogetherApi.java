package com.ww.android.governmentheart.network.api;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.together.ActBean;
import com.ww.android.governmentheart.mvp.bean.together.OrganizationTypeBean;

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
     *
     * @return
     */
    @POST("tztype")
    Observable<ResponseBean<PageListBean<OrganizationTypeBean>>> organizationType();


}
