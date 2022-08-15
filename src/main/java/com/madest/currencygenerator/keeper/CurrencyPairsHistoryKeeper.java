package com.madest.currencygenerator.keeper;

import com.madest.currencygenerator.config.annotations.Keeper;
import com.madest.currencygenerator.dto.CurrencyPairInfo;
import com.madest.currencygenerator.integration.api.Quotes;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Keeper
@Slf4j
public class CurrencyPairsHistoryKeeper {

    private Map<String, List<CurrencyPairInfo>> currencyPairsHistoryMap;

    public void putNewPairInfo(String pair,CurrencyPairInfo pairInfo) {
        log.info("Entered in method putNewPairInfo with args {} , {}",pair,pairInfo);
        var pairInfoHistoryList = currencyPairsHistoryMap.get(pair);
        if (pairInfoHistoryList != null) {
            putActualAndRemoveExtra(pairInfoHistoryList,pairInfo);
        } else {
            var listForPair= new CopyOnWriteArrayList<CurrencyPairInfo>();
            listForPair.add(pairInfo);
            currencyPairsHistoryMap.put(pair,listForPair);
        }
    }

    public void putNewPairsFromData(Quotes currencyData) {
        log.info("Entered in method putNewPairsFromData {}",currencyData);
        putNewPairInfo("GBPRUB",new CurrencyPairInfo(LocalDateTime.now(),currencyData.getGbprub()));
        putNewPairInfo("GBPUSD",new CurrencyPairInfo(LocalDateTime.now(),currencyData.getGbpusd()));
        putNewPairInfo("GBPEUR",new CurrencyPairInfo(LocalDateTime.now(),currencyData.getGbpeur()));


    }

    public List<CurrencyPairInfo> getCurrencyPair(String pair) {
        return currencyPairsHistoryMap.get(pair);
    }

    public void putActualAndRemoveExtra(List<CurrencyPairInfo> pairInfoHistoryList,CurrencyPairInfo pairInfo) {
        log.info("Entered in method putActualAndRemoveExtra with args {} , {}",pairInfoHistoryList,pairInfo);
        if (pairInfoHistoryList.size() >= 100) {
            log.debug("Removing first element from list ,element : {}",pairInfoHistoryList.get(0));
            pairInfoHistoryList.remove(0);
        }
        pairInfoHistoryList.add(pairInfo);
    }

    @PostConstruct
    public void init() {
        currencyPairsHistoryMap = new ConcurrentHashMap<>();
        putNewPairInfo("GBPRUB",new CurrencyPairInfo(LocalDateTime.now(),1.766));
        putNewPairInfo("GBPUSD",new CurrencyPairInfo(LocalDateTime.now(),1.766));
        putNewPairInfo("GBPEUR",new CurrencyPairInfo(LocalDateTime.now(),1.766));
    }

    public void setCurrencyPairsHistoryMap(Map<String, List<CurrencyPairInfo>> currencyPairsHistoryMap) {
        this.currencyPairsHistoryMap = currencyPairsHistoryMap;
    }
}
