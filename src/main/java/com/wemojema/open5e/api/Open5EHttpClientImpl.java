package com.wemojema.open5e.api;

import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Open5EHttpClientImpl implements Open5EHttpClient {
    private static final String HOST = "https://api.open5e.com";
    private static final String WEAPONS = "/weapons";
    private static final String FORMAT = "?format=json";

    private final OkHttpClient httpClient;

    public Open5EHttpClientImpl(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    @SneakyThrows
    @Override
    public String fetchWeapon(String slug) {
        Request request = new Request.Builder().get().url(HOST + WEAPONS + "/" + slug + "/" + FORMAT).build();
        return httpClient.newCall(request).execute().body().string();
    }


}
