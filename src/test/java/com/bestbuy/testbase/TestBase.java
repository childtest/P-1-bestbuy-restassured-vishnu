package com.bestbuy.testbase;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    @BeforeMethod
    public void inIT(){
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
    }

}