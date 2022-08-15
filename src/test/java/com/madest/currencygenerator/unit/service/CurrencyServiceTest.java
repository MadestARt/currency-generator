package com.madest.currencygenerator.unit.service;

import com.madest.currencygenerator.keeper.CurrencyPairsHistoryKeeper;
import com.madest.currencygenerator.service.impl.CurrencyServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private CurrencyPairsHistoryKeeper historyKeeper;
    @InjectMocks
    private CurrencyServiceImpl currencyService;
    private static final String TEST_PAIR = "GBPUSD";

    @Test
    @DisplayName("Проверяем, что getPairPricesHistory обращается к keeper`у за списком цен по валют паре")
    void testGetPairPricesHistoryWorksCorrect() {
        currencyService.getPairPricesHistory(TEST_PAIR);

        verify(historyKeeper).getCurrencyPair(TEST_PAIR);
    }
}
