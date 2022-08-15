package com.madest.currencygenerator.service.impl;

import com.madest.currencygenerator.dto.CurrencyPairInfo;
import com.madest.currencygenerator.keeper.CurrencyPairsHistoryKeeper;
import com.madest.currencygenerator.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyPairsHistoryKeeper pairsHistoryKeeper;


    @Override
    public List<CurrencyPairInfo> getPairPricesHistory(String pair) {
        log.info("Entered int method getPairPricesHistory with arg {}",pair);
        return pairsHistoryKeeper.getCurrencyPair(pair);
    }
}
