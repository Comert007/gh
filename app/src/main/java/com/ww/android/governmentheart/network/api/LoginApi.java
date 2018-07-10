package com.ww.android.governmentheart.network.api;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.home.CommentBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.bean.login.PassBean;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;

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
    @POST("news_category")
    Observable<ResponseBean<PageListBean<NewsTypeBean>>> newsCategory();


    @POST("news_category_child")
    Observable<ResponseBean<PageListBean<NewsTypeBean>>> newsCategoryChild(@Body  RequestBody body);

    /**
     * 评论列表
     * @param body
     * @return
     */
    @POST("commentList")
    Observable<ResponseBean<PageListBean<CommentBean>>> comments(@Body  RequestBody body);

    @POST("commentSave")
    Observable<ResponseBean<String>> saveComment(@Body  RequestBody body);

}
