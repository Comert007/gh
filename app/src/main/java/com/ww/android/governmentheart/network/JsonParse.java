package com.ww.android.governmentheart.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class JsonParse {

    public static String MEDIA_JSON_TYPE = "application/json";
    public static String MEDIA_FORM_TYPE = "application/x-www-form-urlencoded";

    /**
     * 将map对象转成json
     *
     * @param map
     * @return
     */
    public static RequestBody crateMapJson(@NonNull Map<String, Object> map) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String jsonString = gson.toJson(map);
        return RequestBody.create(MediaType.parse(MEDIA_JSON_TYPE), jsonString);
    }

    public static RequestBody crateMapForm(@NonNull Map<String, Object> map) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String jsonString = gson.toJson(map);
        return RequestBody.create(MediaType.parse(MEDIA_FORM_TYPE), jsonString);
    }

    public static RequestBody createArgs(@NonNull Map<String, Object> map) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        String jsonString = "args=" + gson.toJson(map);
        return RequestBody.create(MediaType.parse(MEDIA_FORM_TYPE), jsonString);
    }

}
