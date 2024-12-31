package com.bestbuy.testsuite;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public void extractStoreData() {

        response = given().log().ifValidationFails()
                .when()
                .get(EndPoints.STORES)
                .then().log().ifValidationFails()
                .statusCode(200);

        System.out.println("Store Data limit is : " +
                response.extract().path("limit").toString());

        System.out.println("Store total is : " +
                response.extract().path("total").toString());

        System.out.println("Name Of 5th Store is : " +
                response.extract().path("data[4].name").toString());

        System.out.println("Name Of All Store is : " +
                response.extract().path("data.name").toString());

        System.out.println("Store Id Of All Store is : " +
                response.extract().path("data.id").toString());

        List<String> data = response.extract().path("data");
        System.out.println("Size of Data List is : " +
                data.size());

        List<String> storeValueByName = response.extract()
                .path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("Value of the store is st cloud :  " + storeValueByName);

        System.out.println("address of the store name is rochester : " +
                response.extract().path("data.findAll{it.name == 'Rochester'}.address"));

        System.out.println("All Services of 8th Store: " +
                response.extract().path("data[7].services"));

        List<String> storeServices = response.extract().path("data.services*.findAll{it.name == 'Windows Store'}.storeservices");   //data[0].services.findAll{it.name == 'Windows Store'}.storeservices
        System.out.println("Store Services of service name is Windows Store : " +
                storeServices.toString());

        System.out.println("All Store IDs : " +
                response.extract().path("data.id").toString());

        System.out.println("IDs of All Stores : " +
                response.extract().path("data.id").toString());

        System.out.println("Store Names of state nameis ND : " +
                response.extract().path("data.findAll{it.state == 'ND'}.name").toString());


        List<String> services = response.extract().path("data.findAll{it.name == 'Rochester'}.services");
        System.out.println("Total services of store name = Rochester : " +
                services.size());

        List<String> createdAtList = response.extract().path("data[0].services.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println("CreatedAt for 'Windows Store': " + createdAtList);

        List<String> allServices = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println(" All Service Names of Store name Fargo: "
                + allServices);

        System.out.println("Zips of All Stores : " +
                response.extract().path("data.zip").toString());

        System.out.println("Zip of store name = Roseville : " +
                response.extract().path("data.findAll{it.name == 'Roseville'}.zip").toString());

        List<String> storeServicesDetails = response.extract().path("data.services*.find{it.name == 'Magnolia Home Theater'}.storeservices");
        System.out.println("store services details of the service name is Magnolia Home Theater : "
                + storeServicesDetails);

        System.out.println("Latitudes of All Stores : " +
                response.extract().path("data.lat").toString());


        //System.out.println("Store Services for 'Magnolia Home Theater': " + response.extract().path("data.findAll { store -> store.services.find { it.name == 'Magnolia Home Theater' } }.services.storeservices"));

    }

}
