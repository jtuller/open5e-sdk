package com.wemojema.open5e.api;

import com.wemojema.open5e.BaseTest;

public class MockOpen5EHttpClient implements Open5EHttpClient {

    @Override
    public String fetchWeapon(String slug) {
        return BaseTest.resource("/weapons/" + slug + ".json");
    }
}
