package com.madest.currencygenerator.web.api;

import com.madest.currencygenerator.dto.CurrencyPairInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPairResponse {
    private String pair;
    private List<CurrencyPairInfo> currencyPairs;
}
