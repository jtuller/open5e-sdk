package com.wemojema.open5e.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public abstract class Open5EResultHeader {

    private String slug;
    private String document__slug;
    private String document__title;
    private String document__license_url;

}
