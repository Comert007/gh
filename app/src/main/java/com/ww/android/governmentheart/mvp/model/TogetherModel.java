package com.ww.android.governmentheart.mvp.model;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.together.ActBean;
import com.ww.android.governmentheart.mvp.bean.together.OnlineBean;
import com.ww.android.governmentheart.mvp.model.base.BaseModel;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.network.JsonParse;
import com.ww.android.governmentheart.network.utils.RxSchedulers;

import java.util.Map;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class TogetherModel extends BaseModel {


    /**
     * 活动列表
     *
     * @return
     */
    public void act(Map map, BaseObserver<PageListBean<ActBean>> observer) {
        HttpRequest.togetherApi().act(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    /**
     * 加入活动
     * @param map
     * @param observer
     */

    public void joinAct(Map map, BaseObserver<String> observer){
        HttpRequest.togetherApi().joinAct(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    /**
     * 直播列表
     * @param observer
     */
    public void online(Map map,BaseObserver<PageListBean<OnlineBean>> observer){
        HttpRequest.togetherApi().online(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }
}
