package com.madest.currencygenerator.web.controller;

import com.madest.currencygenerator.service.CurrencyService;
import com.madest.currencygenerator.web.api.CurrencyPairResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("${app.name}/v1")
@RequiredArgsConstructor
public class CurrencyController {
    private final CurrencyService currencyService;


    @GetMapping("/currency/{pair}")
    public ResponseEntity<CurrencyPairResponse> getCurrencyPair(
            @PathVariable String pair
    ) {
        log.info("Entered method GET /currency/{} by HTTP",pair);
        var pairPricesHistory = currencyService.getPairPricesHistory(pair);
        return ResponseEntity.ok(new CurrencyPairResponse(pair,pairPricesHistory));

    }
}
