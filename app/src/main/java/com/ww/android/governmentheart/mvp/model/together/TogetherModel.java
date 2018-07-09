package com.ww.android.governmentheart.mvp.model.together;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.together.OrganizationTypeBean;
import com.ww.android.governmentheart.mvp.model.base.BaseModel;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.network.utils.RxSchedulers;

/**
 * @author feng
 * @Date 2018/7/9.
 */
public class TogetherModel extends BaseModel {

    public void organizationType(BaseObserver<PageListBean<OrganizationTypeBean>> observer){
        HttpRequest.togetherApi().organizationType()
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }
}
