package com.ww.android.governmentheart.network.exception;

public class ApiException extends RuntimeException {


    public static final String SOCKET_TIMEOUT_CODE = "101";
    public static final String CONNECT_CODE = "102";
    public static final String UNKNOWN_HOST_CODE = "103";
    public static final String STATUS_CODE = "104";
    public static final String CONVERT_CODE = "105";



    private String status;
    private String message;

    public ApiException(String status, String message) {
        this.status = status;
        this.message = message;
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

}
