package com.bestbuy.testsuite;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class StoresAssertionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public void verifyStoreData() {

        response = given()
                .when()
                .get(EndPoints.STORES)
                .then().log().ifValidationFails().statusCode(200);


        response.body("total", equalTo(1561),
                "limit", equalTo(10),
                "data.name", hasItem("Inver Grove Heights"),
                "data.name", hasItems("Roseville", "Burnsville", "Maplewood"),
                "data[2].services[1].storeservices.storeId", equalTo(7),
                "data.findAll{it.name == 'Roseville'}.services[0].storeservices[0]", hasKey("createdAt"),
                "data[3].state", equalTo("MN"),
                "data[8].name", equalTo("Rochester"),
                "data[5].id", equalTo(11),
                "data[3].services[3].id", equalTo(4));

    }

}
