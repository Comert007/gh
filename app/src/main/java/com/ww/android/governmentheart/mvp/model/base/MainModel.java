package com.ww.android.governmentheart.mvp.model.base;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.home.OrganizationBean;
import com.ww.android.governmentheart.mvp.bean.home.OrganizationDetailBean;
import com.ww.android.governmentheart.mvp.bean.login.NewsTypeBean;
import com.ww.android.governmentheart.mvp.bean.login.VersionBean;
import com.ww.android.governmentheart.mvp.bean.together.OrganizationTypeBean;
import com.ww.android.governmentheart.mvp.model.home.UserMemberBean;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.network.JsonParse;
import com.ww.android.governmentheart.network.utils.RxSchedulers;

import java.util.Map;

public class MainModel extends BaseModel {

    /**
     * 新闻分类
     *
     * @param observer
     */
    public void newsCategory(BaseObserver<PageListBean<NewsTypeBean>> observer) {
        HttpRequest.loginApi().newsCategory()
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }



    /**
     * 获取组织分类
     * @param observer
     */
    public void organizationType(BaseObserver<PageListBean<OrganizationTypeBean>> observer) {
        HttpRequest.loginApi().organizationType()
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


    /**
     *
     * @param map 根据分类获取组织
     * @param observer
     */
    public void organizations(Map map, BaseObserver<PageListBean<OrganizationBean>>
            observer) {
        HttpRequest.loginApi().organizations(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    /**
     *
     * @param map 2.21.	获取统战组织详情
     * @param observer
     */
    public void organizationDetail(Map map, BaseObserver<PageListBean<OrganizationDetailBean>>
            observer) {
        HttpRequest.loginApi().organizationDetail(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


    /**
     * 获取组织成员
     * @param map
     * @param observer
     */
    public void userpage(Map map, BaseObserver<PageListBean<UserMemberBean>> observer){
        HttpRequest.loginApi().userpage(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .safeSubscribe(observer);

    }


    /**
     * 更新
     */
    public void onUpdate(Map map, BaseObserver<VersionBean> observer){
        HttpRequest.loginApi().version(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .safeSubscribe(observer);
    }

}
