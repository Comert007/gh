package com.ww.android.governmentheart.network.api;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.work.MessageEntity;
import com.ww.android.governmentheart.mvp.bean.work.NotifyEntity;
import com.ww.android.governmentheart.mvp.bean.work.ReceptionEntity;
import com.ww.android.governmentheart.mvp.bean.work.ThemeDetailBean;
import com.ww.android.governmentheart.mvp.bean.work.ThemeEntity;
import com.ww.android.governmentheart.mvp.bean.work.ThemeReplyEntity;

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
    Observable<ResponseBean<PageListBean<NotifyEntity>>> notifyDetail(@Body RequestBody body);

    @POST("msgList")
    Observable<ResponseBean<PageListBean<MessageEntity>>> messageList(@Body RequestBody body);

    @POST("topicList")
    Observable<ResponseBean<PageListBean<ThemeEntity>>> topicList(@Body RequestBody body);

    /**
     * 主题详情
     * @return
     */
    @POST("topicDetail")
    Observable<ResponseBean<PageListBean<ThemeDetailBean>>> topicDetail(@Body RequestBody body);

    /**
     * 主题回复列表
     * @param body
     * @return
     */
    @POST("replyList")
    Observable<ResponseBean<PageListBean<ThemeReplyEntity>>> replyList(@Body RequestBody body);


    /**
     * 回帖
     * @param body
     * @return
     */
    @POST("reply")
    Observable<ResponseBean<String>> replayForum(@Body RequestBody body);

    /**
     * 资料接收
     * @param body
     * @return
     */
    @POST("receiveMaterial")
    Observable<ResponseBean<PageListBean<ReceptionEntity>>> receiveMaterial(@Body RequestBody body);

    @POST("readMsg")
    Observable<ResponseBean<String>> readMsg(@Body RequestBody body);

}
