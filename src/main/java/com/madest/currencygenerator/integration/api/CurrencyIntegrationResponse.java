package com.madest.currencygenerator.integration.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyIntegrationResponse {
    private boolean success;
    private float timestamp;
    private String source;
    @JsonProperty("quotes")
    private Quotes quotes;
}
