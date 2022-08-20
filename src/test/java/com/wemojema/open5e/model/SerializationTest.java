package com.wemojema.open5e.model;

import com.alibaba.fastjson.JSON;
import com.wemojema.open5e.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SerializationTest {


    @Test
    void should_deserialize_weapon_from_weapon_schema() {
        String weaponJson = BaseTest.resource("weapon.json");
        Weapon weapon = JSON.parseObject(weaponJson, Weapon.class);
        Assertions.assertEquals("club", weapon.getSlug());
        System.out.println(JSON.toJSON(weapon));
    }


    @Test
    void should_correctly_determine_weapon_is_equal() {
        String weaponJson = BaseTest.resource("weapon.json");
        Weapon weapon = JSON.parseObject(weaponJson, Weapon.class);
        Weapon weapon2 = JSON.parseObject(weaponJson, Weapon.class);
        Assertions.assertEquals(weapon, weapon2);
    }

}
