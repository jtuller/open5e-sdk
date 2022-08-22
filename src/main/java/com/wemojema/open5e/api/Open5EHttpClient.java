package com.wemojema.open5e.api;

import com.wemojema.open5e.model.APIResponse;
import com.wemojema.open5e.model.Monster;
import com.wemojema.open5e.model.Weapon;
import lombok.SneakyThrows;

import java.util.List;

public interface Open5EHttpClient {

    String fetchWeapon(String slug);

    List<APIResponse<Weapon>> fetchAllWeapons();

    @SneakyThrows
    List<APIResponse<Monster>> fetchAllMonsters();
}
