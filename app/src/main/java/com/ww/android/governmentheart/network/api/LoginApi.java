package com.ww.android.governmentheart.network.api;

import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.bean.login.PassBean;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    @FormUrlEncoded
    @POST("initpass")
    Observable<ResponseBean<PassBean>> initPass(@Field("phone") String phone);

    /**
     * 登录
     *
     * @param username 用户名
     * @param pass     密码
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Observable<ResponseBean<UserBean>> login(@Field("username") String username,
                                             @Field("pass") String pass);

    /**
     * 新闻分类
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Observable<ResponseBean<NewsTypeBean>> newsType();

}
