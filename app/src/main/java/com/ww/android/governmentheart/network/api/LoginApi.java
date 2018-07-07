package com.ww.android.governmentheart.network.api;

import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.bean.login.PassBean;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginApi {

    /*
     * retrofit2 使用说明文档地址：http://square.github.io/retrofit/
     * 详细介绍 post，get ，put，delete方法的不同使用
     */

    /**
     * 电话号码 获取短信验证码
     * @return
     */
    @POST("initpass")
    Observable<ResponseBean<PassBean>> initPass(@Body RequestBody body);

    /**
     * 登录
     *
     * @return
     */
    @POST("login")
    Observable<ResponseBean<UserBean>> login(@Body  RequestBody body);

    /**
     * 新闻分类
     * @return
     */
    @POST("openapi/news_category")
    Observable<ResponseBean<List<NewsTypeBean>>> newsCategory();

}
