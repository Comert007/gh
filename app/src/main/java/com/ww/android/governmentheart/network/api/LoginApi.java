package com.ww.android.governmentheart.network.api;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;
import com.ww.android.governmentheart.mvp.bean.home.CommentBean;
import com.ww.android.governmentheart.mvp.bean.home.OrganizationBean;
import com.ww.android.governmentheart.mvp.bean.home.OrganizationDetailBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsChildTypeBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.bean.login.PassBean;
import com.ww.android.governmentheart.mvp.bean.login.UserBean;
import com.ww.android.governmentheart.mvp.bean.together.OrganizationTypeBean;
import com.ww.android.governmentheart.mvp.model.home.MyCommentBean;
import com.ww.android.governmentheart.mvp.model.home.UserMemberBean;

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
     *
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
    Observable<ResponseBean<UserBean>> login(@Body RequestBody body);

    /**
     * 新闻分类
     *
     * @return
     */
    @POST("news_category")
    Observable<ResponseBean<PageListBean<NewsTypeBean>>> newsCategory();


    @POST("news_category_child")
    Observable<ResponseBean<PageListBean<NewsChildTypeBean>>> newsCategoryChild(@Body RequestBody body);

    /**
     * 评论列表
     *
     * @param body
     * @return
     */
    @POST("commentList")
    Observable<ResponseBean<PageListBean<CommentBean>>> comments(@Body RequestBody body);

    @POST("commentSave")
    Observable<ResponseBean<String>> saveComment(@Body RequestBody body);

    /**
     * 根据分类获取推荐位
     * openapi/getRecommend?args={"code":"5"} 按分类码获取 推荐位
     * @param body
     * @return
     */
    @POST("getRecommend")
    Observable<ResponseBean<PageListBean<NewsBean>>> recommend(@Body RequestBody body);

    @POST("mainpic")
    Observable<ResponseBean<String>> mainpic();


    /**
     * @return 获取统战分类
     */
    @POST("tztype")
    Observable<ResponseBean<PageListBean<OrganizationTypeBean>>> organizationType();


    /**
     * 根据分类获取统战组织
     *
     * @return
     */
    @POST("gettzzz")
    Observable<ResponseBean<PageListBean<OrganizationBean>>> organizations(@Body RequestBody body);


    /**
     * 2.21.	获取统战组织详情
     *
     * @return
     */
    @POST("gettzzzDetail")
    Observable<ResponseBean<PageListBean<OrganizationDetailBean>>> organizationDetail(@Body RequestBody body);


    @POST("userpage")
    Observable<ResponseBean<PageListBean<UserMemberBean>>> userpage(@Body RequestBody body);


    Observable<ResponseBean<PageListBean<MyCommentBean>>> myComments();


}
