package com.ww.android.governmentheart.mvp.model.base;

import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.network.utils.RxSchedulers;

import java.util.List;

public class MainModel extends BaseModel {

    /**
     * 新闻分类
     * @param observer
     */
    public void newsCategory(BaseObserver<List<NewsTypeBean>> observer){
        HttpRequest.loginApi().newsCategory()
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }
}
