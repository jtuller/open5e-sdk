package com.wemojema.open5e.api;

import com.wemojema.open5e.model.Weapon;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OpenFifthEditionTest {

    OpenFifthEdition uut;

    @BeforeEach
    void setup() {
        uut = new OpenFifthEdition(new MockOpen5EHttpClient());
    }

    @Test
    void should_reply_with_a_club() {
        String slug = "club";
        Weapon weapon = uut.fetchWeapon(slug);
        Assertions.assertEquals(slug, weapon.getSlug());
    }

}
