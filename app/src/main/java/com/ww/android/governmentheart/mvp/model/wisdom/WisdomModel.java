package com.ww.android.governmentheart.mvp.model.wisdom;

import com.ww.android.governmentheart.mvp.PageListBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.ContactBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.RequestFileBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.SuggestBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.SuggestDetailBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.TransmissionDetailBean;
import com.ww.android.governmentheart.mvp.bean.wisdom.UploadBean;
import com.ww.android.governmentheart.mvp.model.base.BaseModel;
import com.ww.android.governmentheart.network.BaseObserver;
import com.ww.android.governmentheart.network.HttpRequest;
import com.ww.android.governmentheart.network.JsonParse;
import com.ww.android.governmentheart.network.utils.RxSchedulers;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Callback;

/**
 * @Author feng
 * @Date 2018/7/8
 */
public class WisdomModel extends BaseModel {

    //2.6.	参政议政列表
    public void suggest(Map map, BaseObserver<PageListBean<SuggestBean>> observer) {
        HttpRequest.wisdomApi().suggest(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    //2.7.	参政议政详情
    public void suggestDetail(Map map, BaseObserver<PageListBean<SuggestDetailBean>> observer) {
        HttpRequest.wisdomApi().suggestDetail(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    //2.8   上传附件
    public void upload(Map map, BaseObserver<UploadBean> observer) {
        HttpRequest.wisdomApi().upload(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    public void uploadFiles(List<RequestFileBean> files, BaseObserver<PageListBean<UploadBean>> observer) {

        HttpRequest.wisdomApi().uploadFiles(JsonParse.createMultipart(files))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    //下载
    public void download(String fileId, Callback<ResponseBody> callback) {
        HttpRequest.wisdomApi().download(fileId)
                .enqueue(callback);
    }


    //2.8   保存参政议政
    public void saveSuggest(Map map, BaseObserver<String> observer) {
        HttpRequest.wisdomApi().saveSuggest(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }

    //2.12.	资料发送列表
    public void material(Map map, BaseObserver<PageListBean<TransmissionBean>> observer) {
        HttpRequest.wisdomApi().material(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


    //2.8   保存资料
    public void saveMaterial(Map map, BaseObserver<String> observer) {
        HttpRequest.wisdomApi().saveMaterial(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


    /**
     * 资料发送详情
     * @param map
     * @param observer
     */
    public void materialDetail(Map map, BaseObserver<TransmissionDetailBean> observer) {
        HttpRequest.wisdomApi().materialDetail(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


    /**
     * 联系人查询
     * @param map
     * @param observer
     */
    public void contacts(Map map, BaseObserver<PageListBean<ContactBean>> observer) {
        HttpRequest.wisdomApi().contacts(JsonParse.createArgs(map))
                .compose(RxSchedulers.cutObservableMain())
                .compose(observer.getTransformer())
                .subscribe(observer);
    }


}
