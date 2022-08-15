package com.madest.currencygenerator.unit.keeper;

import com.madest.currencygenerator.dto.CurrencyPairInfo;
import com.madest.currencygenerator.keeper.CurrencyPairsHistoryKeeper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.assertj.core.api.Assertions.*;

class CurrencyPairsHistoryKeeperTest {
    private CurrencyPairsHistoryKeeper pairsHistoryKeeper;
    private static final String TEST_PAIR = "GBPUSD";
    private static final CurrencyPairInfo FIRST_TEST_PAIR_INFO = new CurrencyPairInfo(LocalDateTime.now(),1.698);
    private static final CurrencyPairInfo SECOND_TEST_PAIR_INFO = new CurrencyPairInfo(LocalDateTime.now(),9999);

    @BeforeEach
    void initKeeper() {
        pairsHistoryKeeper= new CurrencyPairsHistoryKeeper();
        pairsHistoryKeeper.setCurrencyPairsHistoryMap( new ConcurrentHashMap<>());
    }

    @Test
    @DisplayName("Проверяем,что putNewPairInfo проверяет наличие списка историй цен по в.паре и инициализирует если нужно")
    void testPutNewPairInitializeListIfNoActual() {
        var expectedPairList = new CopyOnWriteArrayList<>(List.of(FIRST_TEST_PAIR_INFO));

        pairsHistoryKeeper.putNewPairInfo(TEST_PAIR, FIRST_TEST_PAIR_INFO);

        var actualPairList = pairsHistoryKeeper.getCurrencyPair(TEST_PAIR);

        assertThat(expectedPairList).hasSameElementsAs(actualPairList);

    }

    @Test
    @DisplayName("Проверяем, что putActualAndRemoveExtra убирает самый старый элемент из списка и добавляет новый")
    void testPutActualAndRemoveExtraWorksCorrect() {
        pairsHistoryKeeper.putNewPairInfo(TEST_PAIR,SECOND_TEST_PAIR_INFO);
        for (int i = 0; i < 99; i++) {
            pairsHistoryKeeper.putNewPairInfo(TEST_PAIR, FIRST_TEST_PAIR_INFO);
        }
        var expectedLatestElement = FIRST_TEST_PAIR_INFO;

        pairsHistoryKeeper.putNewPairInfo(TEST_PAIR,FIRST_TEST_PAIR_INFO);
        var actualLatestElement = pairsHistoryKeeper.getCurrencyPair(TEST_PAIR).get(0);

        assertThat(expectedLatestElement).isEqualTo(actualLatestElement);



    }
}
