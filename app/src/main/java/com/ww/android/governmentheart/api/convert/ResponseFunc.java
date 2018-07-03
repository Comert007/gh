package com.ww.android.governmentheart.api.convert;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ww.android.governmentheart.api.exception.ApiException;
import com.ww.android.governmentheart.config.Constant;
import com.ww.android.governmentheart.mvp.bean.ResponseBean;

import rx.functions.Func1;

public class ResponseFunc<T> implements Func1<String, ResponseBean<T>> {

    @Override
    public ResponseBean<T> call(String string) {
        try {
            ResponseBean<T> responseBean = JSONObject.parseObject(string, new
                    TypeReference<ResponseBean<T>>() {
            });
            if (responseBean != null) {
                if (Constant.STATUS_OK.equals(responseBean.getStatus()))
                    return responseBean;
                else {
                    throw new ApiException(Constant.STATUS_ERROR,responseBean.getMsg());
                }
            } else {
                throw new ApiException(Constant.STATUS_ERROR,ApiException.ApiExceptionName.NETWORK_EXCEP_NAME);
            }
        } catch (Exception e) {
            throw new ApiException(Constant.STATUS_ERROR, ApiException.ApiExceptionName.CONVERT_EXCEP_NAME);
        }
    }
}
