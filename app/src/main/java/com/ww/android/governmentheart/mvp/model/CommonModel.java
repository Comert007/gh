package com.ww.android.governmentheart.mvp.model;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;
import com.ww.android.governmentheart.mvp.bean.home.CommentBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsChildTypeBean;
import com.ww.android.governmentheart.mvp.model.base.BaseModel;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.network.JsonParse;
import com.ww.android.governmentheart.network.utils.RxSchedulers;

import java.util.Map;

public class CommonModel extends BaseModel {

    /**
     * 新闻列表
     *
     * @param map
     * @param observer
     */
    public void news(Map map, BaseObserver<PageListBean<NewsBean>> observer) {
        HttpRequest.heartApi().news(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);

    }


    /**
     * 新闻子类分类
     *
     * @param map
     * @param observer
     */
    public void newsCategoryChild(Map map, BaseObserver<PageListBean<NewsChildTypeBean>> observer) {

        HttpRequest.loginApi().newsCategoryChild(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


    /**
     * 评论列表
     *
     * @param map
     * @param observer
     */
    public void comments(Map map, BaseObserver<PageListBean<CommentBean>> observer) {
        HttpRequest.loginApi().comments(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    /**
     * 保存评论
     *
     * @param map
     * @param observer
     */
    public void saveComment(Map map, BaseObserver<String> observer) {
        HttpRequest.loginApi().saveComment(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


    /**
     * openapi/getRecommend?args={"code":"5"} 按分类码获取 推荐位
     */
    public void recommend(Map map, BaseObserver<PageListBean<NewsBean>> observer) {
        HttpRequest.loginApi().recommend(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


    /**
     * 首页顶部首图
     * @param observer
     */
    public void mainPic(BaseObserver<String> observer) {
        HttpRequest.loginApi().mainpic()
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


}
