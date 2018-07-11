package com.ww.android.governmentheart.mvp.model;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.heart.NewsBean;
import com.ww.android.governmentheart.mvp.bean.home.CommentBean;
import com.ww.android.governmentheart.mvp.model.base.BaseModel;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.network.JsonParse;
import com.ww.android.governmentheart.network.utils.RxSchedulers;

import java.util.Map;

public class CommonModel extends BaseModel {

    /**
     * 新闻列表
     * @param map
     * @param observer
     */
    public void news(Map map, BaseObserver<PageListBean<NewsBean>> observer){
        HttpRequest.heartApi().news(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);

    }


    /**
     * 评论列表
     * @param map
     * @param observer
     */
    public void comments(Map map, BaseObserver<PageListBean<CommentBean>> observer){
        HttpRequest.loginApi().comments(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    /**
     * 保存评论
     * @param map
     * @param observer
     */
    public void saveComment(Map map, BaseObserver<String> observer){
        HttpRequest.loginApi().saveComment(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


}
