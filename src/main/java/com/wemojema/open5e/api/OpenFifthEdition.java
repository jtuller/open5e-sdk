package com.wemojema.open5e.api;

import com.alibaba.fastjson.JSON;
import com.wemojema.open5e.model.Weapon;

public class OpenFifthEdition {

    private final Open5EHttpClient client;

    public OpenFifthEdition(Open5EHttpClient client) {
        this.client = client;
    }

    public Weapon fetchWeapon(String slug) {
        return JSON.parseObject(client.fetchWeapon(slug), Weapon.class);
    }

}
