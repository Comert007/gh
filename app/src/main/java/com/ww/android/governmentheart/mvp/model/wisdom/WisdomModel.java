package com.ww.android.governmentheart.mvp.model.wisdom;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.SuggestBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.SuggestDetailBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionDetailBean;
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
public class WisdomModel extends BaseModel {

    //2.6.	参政议政列表
    public void suggest(Map map, BaseObserver<PageListBean<SuggestBean>> observer){
        HttpRequest.wisdomApi().suggest(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    //2.7.	参政议政详情
    public void suggestDetail(Map map, BaseObserver<SuggestDetailBean> observer){
        HttpRequest.wisdomApi().suggestDetail(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    //2.12.	资料发送列表
    public void material(Map map , BaseObserver<PageListBean<TransmissionBean>> observer){
        HttpRequest.wisdomApi().material(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


    public void materialDetail(Map map, BaseObserver<TransmissionDetailBean> observer){
        HttpRequest.wisdomApi().materialDetail(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

}
