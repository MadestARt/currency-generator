package com.madest.currencygenerator.integration.service;

import com.madest.currencygenerator.config.APIKeyProperties;
import com.madest.currencygenerator.integration.api.CurrencyIntegrationResponse;
import com.madest.currencygenerator.keeper.CurrencyPairsHistoryKeeper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.madest.currencygenerator.endpoints.CurrencyAPIEndpoints.*;
import static org.springframework.http.HttpMethod.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledClientService {
    private final RestTemplate restTemplate;
    private final CurrencyPairsHistoryKeeper pairsKeeper;
    private final APIKeyProperties apiKeyProperties;


    @Scheduled(fixedRate = 120000)
    void addFreshCurrencyRates() {
        log.info("Scheduled client is sending HTTP request to {}",CURRENCY_RATES.getUrl());
        var httpResponse = restTemplate.exchange(CURRENCY_RATES.getUrl(), GET, prepareHttpRequest(), CurrencyIntegrationResponse.class);
        pairsKeeper.putNewPairsFromData(httpResponse.getBody().getQuotes());
    }

    private HttpHeaders prepareHeaders() {
        var headers = new HttpHeaders();
        headers.add("apikey",apiKeyProperties.getKey());
        return headers;
    }

    private HttpEntity<?> prepareHttpRequest() {
        return new HttpEntity<>(prepareHeaders());
    }
}
