package com.ww.android.governmentheart.network.api;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.SuggestBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.SuggestDetailBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionDetailBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author feng
 * @Date 2018/7/4.
 */
public interface WisdomApi {

    @POST("suggest")
    Observable<ResponseBean<PageListBean<SuggestBean>>> suggest(@Body RequestBody body);


    /**
     *
     * @param body
     * @return
     */
    @POST("suggestDetail")
    Observable<ResponseBean<SuggestDetailBean>> suggestDetail(@Body RequestBody body);


    /**
     * 资料发送
     * @param body
     * @return
     */
    @POST("material")
    Observable<ResponseBean<PageListBean<TransmissionBean>>> material(@Body RequestBody body);
    //materialDetail

    /**
     * 资料发送详情
     * @param body
     * @return
     */
    @POST("materialDetail")
    Observable<ResponseBean<TransmissionDetailBean>> materialDetail(@Body RequestBody body);
}
