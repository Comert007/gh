package com.ww.android.governmentheart.network;

import android.content.Context;
import android.text.TextUtils;

import com.ww.android.governmentheart.BaseApplication;
import com.ww.android.governmentheart.BuildConfig;
import com.ww.android.governmentheart.network.api.HeartApi;
import com.ww.android.governmentheart.network.api.JoinApi;
import com.ww.android.governmentheart.network.api.LoginApi;
import com.ww.android.governmentheart.network.api.StyleApi;
import com.ww.android.governmentheart.network.api.TogetherApi;
import com.ww.android.governmentheart.network.api.WisdomApi;
import com.ww.android.governmentheart.network.config.RequestConstants;
import com.ww.android.governmentheart.network.utils.Utils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();
    private static OkHttpClient okHttpClient = getDefaultClient();
    private static Retrofit retrofit = getRetrofit();

    public static LoginApi loginApi;
    public static HeartApi heartApi;
    public static JoinApi joinApi;
    public static TogetherApi togetherApi;
    public static StyleApi styleApi;
    public static WisdomApi wisdomApi;



    private HttpRequest() {
    }

    public static WisdomApi wisdomApi() {
        if (wisdomApi == null) {
            wisdomApi = retrofit.create(WisdomApi.class);
        }
        return wisdomApi;
    }


    public static TogetherApi togetherApi() {

        if (togetherApi == null) {
            togetherApi = retrofit.create(TogetherApi.class);
        }
        return togetherApi;
    }


    public static StyleApi styleApi() {
        if (styleApi == null) {
            styleApi = retrofit.create(StyleApi.class);
        }
        return styleApi;
    }

    public static JoinApi joinApi() {
        if (joinApi == null) {
            joinApi = retrofit.create(JoinApi.class);
        }
        return joinApi;
    }


    public static HeartApi heartApi() {
        if (heartApi == null) {
            heartApi = retrofit.create(HeartApi.class);
        }
        return heartApi;
    }


    public static LoginApi loginApi() {
        if (loginApi == null) {
            loginApi = retrofit.create(LoginApi.class);
        }
        return loginApi;
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
                .build();
    }


    private static Retrofit getRetrofit() {
        return  new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build();
    }


    private static OkHttpClient getDefaultClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.LOGGER) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        Cache cache = Utils.getCache(BaseApplication.getInstance().getApplicationContext(),
                RequestConstants.MAX_CACHE_SIZE, RequestConstants
                        .CACHE_DIR_NAME);
        builder.cache(cache);

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder requestBuilder =  request.newBuilder();
                requestBuilder
                        .addHeader("Accept-Charset", "utf-8")
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .addHeader("client", "android");

               String token = BaseApplication.getInstance().getToken();
               if (!TextUtils.isEmpty(token)){
                   requestBuilder.addHeader("token",token);
               }

               request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

        builder.retryOnConnectionFailure(false)
                .addInterceptor(interceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);


       return builder.build();

    }

}
