package com.madest.currencygenerator.integration.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quotes {
    @JsonProperty("GBPUSD")
    private double gbpusd;
    @JsonProperty("GBPEUR")
    private double gbpeur;
    @JsonProperty("GBPRUB")
    private double gbprub;
}
