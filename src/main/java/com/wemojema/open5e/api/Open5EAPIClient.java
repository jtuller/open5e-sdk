package com.wemojema.open5e.api;

import com.alibaba.fastjson.JSON;
import com.wemojema.open5e.model.APIResponse;
import com.wemojema.open5e.model.Open5EResultHeader;
import com.wemojema.open5e.model.Weapon;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Open5EAPIClient implements Open5EHttpClient {
    private static final String HOST = "https://api.open5e.com";
    private static final String FORMAT = "?format=json";
    private static final String WEAPONS = "/weapons";

    private static final Logger logger = LoggerFactory.getLogger(Open5EAPIClient.class);

    private final OkHttpClient httpClient;
    private Instant timerStart;

    public Open5EAPIClient(OkHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public static class WeaponsResponse extends APIResponse<Weapon> {
    }

    public static class ResponseContainer<T extends Open5EResultHeader> {
        List<APIResponse<T>> responses = new ArrayList<>();
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

    @Override
    @SneakyThrows
    public List<APIResponse<Weapon>> fetchAllWeapons() {
        logger.trace("fetching all weapons");
        startTimer();
        Request request = getRequestFor(WEAPONS);
        List<APIResponse<Weapon>> result = fetchAPIResponse(request, WeaponsResponse.class, new ResponseContainer<>());
        stopTimer();
        return result;
    }

    /**
     * ----------------------------------------------------------------------------------------------------------------
     * This method makes a call to the open5e API and returns the full set of paginated result(s) in a ResponseContainer
     * the recursive calls occur if the response from the API shows that the _next_ field contains a URL, at which
     * point the method will be called recursively until the _next_ field from the API is empty.
     * -----------------------------------------------------------------------------------------------------------------
     *
     * @param request           the initial request to fetch from the open5e API
     * @param clazz             the class for which the response should be parsed into
     * @param responseContainer a container to hold recursive responses
     * @param <T>               the Type of Responses to expect in the container
     * @param <R>               the Type of the embedded _result_ class from open5e API
     * @return a list of
     * @throws IOException when the body of the response from the API is empty or when the API call fails for some reason
     */
    private <T, R extends Open5EResultHeader> List<APIResponse<R>> fetchAPIResponse(Request request, Class<T> clazz, ResponseContainer<R> responseContainer) throws IOException {
        String jsonResponse = httpClient.newCall(request).execute().body().string();
        APIResponse<R> response = JSON.parseObject(jsonResponse, (Type) clazz);
        responseContainer.responses.add(response);
        if (response.getNext() != null)
            return fetchAPIResponse(getRequestForFullyQualifiedPath(response.getNext()), clazz, responseContainer);
        return responseContainer.responses;
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

    @NotNull
    private Request getRequestForFullyQualifiedPath(String path) {
        return new Request.Builder().get().url(path).build();
    }


}
