package com.ww.android.governmentheart.network.api;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.ContactBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.QuestionBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.QuestionDetailBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.SuggestBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.SuggestDetailBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionDetailBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.UploadBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

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
    Observable<ResponseBean<PageListBean<SuggestDetailBean>>> suggestDetail(@Body RequestBody body);


    /**
     * 2.15.	上传附件
     * @param body
     * @return
     */
    @POST("upload")
    Observable<ResponseBean<UploadBean>> upload(@Body RequestBody body);


    /**
     * 2.15.	上传附件
     * @return
     */
    @POST("uploadFiles")
    Observable<ResponseBean<PageListBean<UploadBean>>> uploadFiles(@Body MultipartBody body);


    /**
     * 2.15.	上传附件
     * @return
     */
    @Multipart
    @POST("uploadFiles")
    Observable<ResponseBean<PageListBean<UploadBean>>> uploadFiles(@Part() List<MultipartBody.Part> parts);


    @GET("download")
    Call<ResponseBody> download(@Query("fileId") String fileId);


    /**
     * 2.8.	参政议政保存
     * @param body
     * @return
     */
    @POST("suggestSave")
    Observable<ResponseBean<String>> saveSuggest(@Body RequestBody body);

    /**
     * 资料发送
     * @param body
     * @return
     */
    @POST("material")
    Observable<ResponseBean<PageListBean<TransmissionBean>>> material(@Body RequestBody body);


    /**
     * 资料保存
     * @param body
     * @return
     */
    @POST("materialSave")
    Observable<ResponseBean<String>> saveMaterial(@Body RequestBody body);

    //materialDetail

    /**
     * 资料发送详情
     * @param body
     * @return
     */
    @POST("materialDetail")
    Observable<ResponseBean<TransmissionDetailBean>> materialDetail(@Body RequestBody body);


    /**
     * userpage?args={"pageNo":"0","name":"雷"} 查人员
     * 联系人查询
     * @param body
     * @return
     */
    @POST("userpage")
    Observable<ResponseBean<PageListBean<ContactBean>>> contacts(@Body RequestBody body);

    /**
     * 知识交流列表 page
     * @param body
     * @return
     */
    @POST("questionList")
    Observable<ResponseBean<PageListBean<QuestionBean>>> questions(@Body RequestBody body);


    /**
     * con="问题" title="标题"
     * 新增知识交流
     * @param body
     * @return
     */
    @POST("addQuestion")
    Observable<ResponseBean<String>> addQuestion(@Body RequestBody body);


    /**
     * 获取知识交流详情 id="1"
     * @param body
     * @return
     */
    @POST("getQuestionById")
    Observable<ResponseBean<PageListBean<QuestionDetailBean>>> questionDetail(@Body RequestBody body);
}
