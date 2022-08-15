package com.madest.currencygenerator.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Endpoints {
    BASE_LOCAL_URL("http://localhost"),
    BASE_PATH("/currency-generator/v1"),
    GET_CURRENCY("/currency");


    private final String url;

}
