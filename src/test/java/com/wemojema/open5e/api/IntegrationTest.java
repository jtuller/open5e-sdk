package com.wemojema.open5e.api;

import com.alibaba.fastjson.JSON;
import com.wemojema.open5e.model.APIResponse;
import com.wemojema.open5e.model.Monster;
import com.wemojema.open5e.model.Weapon;
import okhttp3.OkHttpClient;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;

@Ignore("Integration test Only. Slow and requires Network")
public class IntegrationTest {

    Open5EHttpClient uut;

    @BeforeEach
    void setup() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(Duration.ofSeconds(30))
                .readTimeout(Duration.ofSeconds(60))
                .build();
        uut = new Open5EAPIClient(okHttpClient);
    }

    @Test
    void should_fetch_from_actual_api() {
        String club = uut.fetchWeapon("club");
        Weapon weapon = JSON.parseObject(club, Weapon.class);
        Assertions.assertEquals("club", weapon.getSlug());
    }

    @Test
    void should_not_throw_an_exception_when_fetching_all_weapons() {
        Assertions.assertDoesNotThrow(() -> {
            List<APIResponse<Weapon>> result = uut.fetchAllWeapons();
            Assertions.assertNotNull(result);
            System.out.println(result);
        });
    }

    @Test
    void should_not_throw_an_exception_when_fetching_all_monsters() {
        Assertions.assertDoesNotThrow(() -> {
            List<APIResponse<Monster>> result = uut.fetchAllMonsters();
            Assertions.assertNotNull(result);
            System.out.println(result);
        });
    }

}
