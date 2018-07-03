package com.ww.android.governmentheart.api.exception;

public class ApiException extends RuntimeException {

    private String status;
    private String message;

    public ApiException(String status, String message) {
        this.message = message;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public interface ApiExceptionName {
        String CONVERT_EXCEP_NAME ="数据转换失败";
        String NETWORK_EXCEP_NAME ="网络异常，请检查网络是否正常";
        String STATUS_EXCEP_NAME ="状态异常";
    }
}
