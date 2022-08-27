package com.wemojema.open5e.api;

import com.wemojema.open5e.model.APIResponse;
import com.wemojema.open5e.model.Armor;
import com.wemojema.open5e.model.Weapon;

import java.util.List;

public interface Open5EHttpClient {

    String fetchWeapon(String slug);

    List<APIResponse<Weapon>> fetchAllWeapons();

    List<APIResponse<Armor>> fetchAllArmor();

}
