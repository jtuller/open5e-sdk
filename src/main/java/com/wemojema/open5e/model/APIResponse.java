package com.wemojema.open5e.model;

import lombok.Data;

import java.util.List;

@Data
public class APIResponse<T extends Open5EResultHeader> {

    Integer count;
    String next;
    String previous;
    List<T> results;

}
