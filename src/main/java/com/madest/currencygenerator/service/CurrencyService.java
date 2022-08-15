package com.madest.currencygenerator.service;

import com.madest.currencygenerator.dto.CurrencyPairInfo;

import java.util.List;

public interface CurrencyService {

    List<CurrencyPairInfo> getPairPricesHistory(String pair);
}
