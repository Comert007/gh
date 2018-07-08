package com.ww.android.governmentheart.mvp.model.base;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.network.JsonParse;
import com.ww.android.governmentheart.network.utils.RxSchedulers;

import java.util.Map;

public class MainModel extends BaseModel {

    /**
     * 新闻分类
     * @param observer
     */
    public void newsCategory(BaseObserver<PageListBean<NewsTypeBean>> observer){
        HttpRequest.loginApi().newsCategory()
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


    /**
     * 新闻子类分类
     * @param map
     * @param observer
     */
    public void newsCategoryChild(Map map, BaseObserver<PageListBean<NewsTypeBean>> observer){

        HttpRequest.loginApi().newsCategoryChild(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }
}
