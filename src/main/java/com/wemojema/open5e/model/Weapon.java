package com.wemojema.open5e.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public final class Weapon extends Open5EResultHeader {

    private String name;
    private String category;
    private String cost;
    private String damage_dice;
    private String damage_type;
    private String weight;
    private List<String> properties;

}
