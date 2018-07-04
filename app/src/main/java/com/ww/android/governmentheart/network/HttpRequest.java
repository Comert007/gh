package com.ww.android.governmentheart.network;

import android.content.Context;
import android.util.Log;

import com.ww.android.governmentheart.BuildConfig;
import com.ww.android.governmentheart.network.api.HeartApi;
import com.ww.android.governmentheart.network.api.JoinApi;
import com.ww.android.governmentheart.network.api.LoginApi;
import com.ww.android.governmentheart.network.api.StyleApi;
import com.ww.android.governmentheart.network.api.TogetherApi;
import com.ww.android.governmentheart.network.api.WisdomApi;
import com.ww.android.governmentheart.network.config.RequestConstants;
import com.ww.android.governmentheart.network.utils.Utils;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author feng
 * @Date 2018/7/4.
 */
public class HttpRequest {

    private static String TAG = ">>>HttpRequest";
    public static LoginApi loginApi;
    public static HeartApi heartApi;
    public static JoinApi joinApi;
    public static TogetherApi togetherApi;
    public static StyleApi styleApi;
    public static WisdomApi wisdomApi;

    private static OkHttpClient okHttpClient = getDefaultClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory
            .create();
    private static final HttpLoggingInterceptor loggingInterceptor;
    private static boolean IS_LOGGING_ENABLED = false;

    private HttpRequest() {
    }

    static {
        loggingInterceptor =
                new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        try {
                            if (message != null && message.startsWith("{")) {
                                Log.d(TAG, new JSONObject(message).toString(4));
                            } else {
                                Log.d(TAG, message);
                            }
                        } catch (Exception e) {
                            Log.d(TAG, message);
                        }
                    }
                });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    public static WisdomApi wisdomApi(){
        if (!IS_LOGGING_ENABLED){
            okHttpClient = getDefaultBuilder().build();
        }
        if (wisdomApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            wisdomApi = retrofit.create(WisdomApi.class);
        }

        return wisdomApi;
    }


    public static TogetherApi togetherApi(){
        if (!IS_LOGGING_ENABLED){
            okHttpClient = getDefaultBuilder().build();
        }
        if (togetherApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            togetherApi = retrofit.create(TogetherApi.class);
        }

        return togetherApi;
    }


    public static StyleApi styleApi(){
        if (!IS_LOGGING_ENABLED){
            okHttpClient = getDefaultBuilder().build();
        }
        if (styleApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            styleApi = retrofit.create(StyleApi.class);
        }

        return styleApi;
    }

    public static JoinApi joinApi(){
        if (!IS_LOGGING_ENABLED){
            okHttpClient = getDefaultBuilder().build();
        }
        if (joinApi == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            joinApi = retrofit.create(JoinApi.class);
        }

        return joinApi;
    }


    public static HeartApi heartApi() {
        if (!IS_LOGGING_ENABLED){
            okHttpClient = getDefaultBuilder().build();
        }
        if (heartApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            heartApi = retrofit.create(HeartApi.class);
        }
        return heartApi;
    }


    public static LoginApi loginApi() {
        if (!IS_LOGGING_ENABLED){
            okHttpClient = getDefaultBuilder().build();
        }
        if (loginApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            loginApi = retrofit.create(LoginApi.class);
        }
        return loginApi;
    }


    public static OkHttpClient getDefaultClient() {
        if (okHttpClient == null) {
            return getDefaultBuilder().addInterceptor(loggingInterceptor)
                    .build();
        }
        return okHttpClient;
    }

    public static OkHttpClient.Builder getDefaultBuilder() {
        return new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
    }

    public static void setClientWithCache(Context context) {
        okHttpClient = new OkHttpClient().newBuilder()
                .cache(Utils.getCache(context, RequestConstants.MAX_CACHE_SIZE, RequestConstants
                        .CACHE_DIR_NAME))
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }


    public static boolean setLogging(boolean isLogging) {
        return IS_LOGGING_ENABLED = isLogging;
    }
}
