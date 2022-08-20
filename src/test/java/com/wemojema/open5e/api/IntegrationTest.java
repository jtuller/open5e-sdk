package com.wemojema.open5e.api;

import com.alibaba.fastjson.JSON;
import com.wemojema.open5e.model.Weapon;
import okhttp3.OkHttpClient;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

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


}
