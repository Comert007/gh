package com.ww.android.governmentheart.network.api;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.work.MessageEntity;
import com.ww.android.governmentheart.mvp.bean.work.NotifyEntity;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WorkApi {

    /**
     * 通知列表
     * @param body
     * @return
     */
    //openapi/ msgList
    @POST("getNotify")
    Observable<ResponseBean<PageListBean<NotifyEntity>>> workList(@Body RequestBody body);

    /**
     * 通知详情
     * @param body
     * @return
     */
    @POST("notifyDetail")
    Observable<ResponseBean<NotifyEntity>> notifyDetail(@Body RequestBody body);

    @POST("msgList")
    Observable<ResponseBean<PageListBean<MessageEntity>>> messageList(@Body RequestBody body);
}
