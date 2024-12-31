package com.bestbuy.testsuite;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ProductsAssertionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public void verifyProductData() {
        response = given()
                .when()
                .get(EndPoints.PRODUCT)
                .then().log().ifValidationFails().statusCode(200);

        response.body("total", equalTo(51957),
                "limit", equalTo(10),
               "data.name", hasItem("Duracell - AAA Batteries (4-Pack)"),
                "data.name", hasItems("Duracell - AA 1.5V CopperTop Batteries (4-Pack)", "Duracell - AA Batteries (8-Pack)", "Energizer - MAX Batteries AA (4-Pack)"),
                "data.findAll{it.id == 150115}.categories[0].name[2]",
                equalTo("Household Batteries"),
                "data.findAll{it.id == 333179}.categories[0].name[1]",
                equalTo("Housewares"),
                "data[3].price", equalTo(4.99f),
                "data[5].name", equalTo("Duracell - D Batteries (4-Pack)"),
                "data[8].id", equalTo(333179),
                "data.findAll{it.id == 346575}.categories[0].size()", equalTo(5));

       /* String aa = response.extract().path("data.findAll{it.id == 150115}.categories[0].name[2]");
        System.out.println(aa);*/
    }
}
