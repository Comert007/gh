package com.ww.android.governmentheart.network;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class JsonParse {

    public static String MEDIA_JSON_TYPE = "application/json";
    public static String MEDIA_FORM_TYPE = "application/x-www-form-urlencoded";
    public static String MEDIA_IMAGE_TYPE = "image/*";

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
//        Debug.d("str:"+jsonString);
        return RequestBody.create(MediaType.parse(MEDIA_FORM_TYPE), jsonString);
    }


    public static MultipartBody createMultipartBody(List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse(MEDIA_IMAGE_TYPE), file);
            builder.addFormDataPart("files", file.getName(), requestBody);
        }
        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }


    public static List<MultipartBody.Part> createMultipart(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            RequestBody requestBody = RequestBody.create(MediaType.parse(MEDIA_IMAGE_TYPE), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("files", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }

}
