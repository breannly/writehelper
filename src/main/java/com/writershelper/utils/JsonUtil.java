package com.writershelper.utils;

import com.google.gson.Gson;

public class JsonUtil {

    private static final Gson gson = new Gson();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static<T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

}
