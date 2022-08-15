package com.madest.currencygenerator.integration;

import com.madest.currencygenerator.config.Endpoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
public abstract class IntegrationTestBase {

    @LocalServerPort
    private int port;

    protected RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_LOCAL_URL.getUrl())
                .setBasePath(Endpoints.BASE_PATH.getUrl())
                .setPort(port)
                .build().log().all();
    }

}

