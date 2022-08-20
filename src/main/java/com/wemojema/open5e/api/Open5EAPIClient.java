package com.wemojema.open5e.api;

import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class Open5EAPIClient implements Open5EHttpClient {
    private static final String HOST = "https://api.open5e.com";
    private static final String WEAPONS = "/weapons";
    private static final String FORMAT = "?format=json";

    private static final Logger logger = LoggerFactory.getLogger(Open5EAPIClient.class);

    private final OkHttpClient httpClient;
    private Instant timerStart;

    public Open5EAPIClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    @SneakyThrows
    @Override
    public String fetchWeapon(String slug) {
        logger.trace("fetching weapon: " + slug);
        startTimer();
        Request request = getRequestFor(WEAPONS + "/" + slug);
        String result = httpClient.newCall(request).execute().body().string();
        stopTimer();
        return result;
    }

    private void stopTimer() {
        long millisecondsElapsed = Instant.now().toEpochMilli() - timerStart.toEpochMilli();
        this.timerStart = null;
        logger.trace("call took " + millisecondsElapsed + " milliseconds");
    }

    private void startTimer() {
        this.timerStart = Instant.now();
    }

    @NotNull
    private Request getRequestFor(String path) {
        return new Request.Builder().get().url(HOST + path + "/" + FORMAT).build();
    }


}
