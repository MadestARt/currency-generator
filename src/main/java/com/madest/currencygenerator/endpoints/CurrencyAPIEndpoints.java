package com.madest.currencygenerator.endpoints;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CurrencyAPIEndpoints {
    CURRENCY_RATES("https://api.apilayer.com/currency_data/live?source=GBP&currencies=USD,EUR,RUB");

    private String url;



}
