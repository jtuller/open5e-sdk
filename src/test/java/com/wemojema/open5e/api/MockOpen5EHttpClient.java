package com.wemojema.open5e.api;

import com.wemojema.open5e.BaseTest;
import com.wemojema.open5e.model.APIResponse;
import com.wemojema.open5e.model.Monster;
import com.wemojema.open5e.model.Weapon;

import java.util.Collections;
import java.util.List;

public class MockOpen5EHttpClient implements Open5EHttpClient {

    @Override
    public String fetchWeapon(String slug) {
        return BaseTest.resource("/weapons/" + slug + ".json");
    }

    @Override
    public List<APIResponse<Weapon>> fetchAllWeapons() {
        return Collections.emptyList();
    }

    @Override
    public List<APIResponse<Monster>> fetchAllMonsters() {
        return null;
    }
}
