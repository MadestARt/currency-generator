package com.madest.currencygenerator.integration.web;

import com.madest.currencygenerator.config.Endpoints;
import com.madest.currencygenerator.integration.IntegrationTestBase;
import com.madest.currencygenerator.web.api.CurrencyPairResponse;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.madest.currencygenerator.config.Endpoints.*;
import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.*;

class CurrencyControllerIntegrationTest extends IntegrationTestBase {

    @Test
    @DisplayName("Проверяем, что при корректной валютной паре возвращается 200 ОК + респонс")
    void testGetCurrencyPairReturnCorrectResponseAndData() {
        var currencyPairResponse = given()
                .spec(getRequestSpecification())
                .when()
                .get(GET_CURRENCY.getUrl() + "/GBPUSD")
                .then()
                .statusCode(HttpStatus.OK.value())
                .and()
                .extract().as(CurrencyPairResponse.class);

        assertThat(currencyPairResponse.getCurrencyPairs()).isNotEmpty();
    }

}
