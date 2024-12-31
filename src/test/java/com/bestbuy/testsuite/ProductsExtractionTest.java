package com.bestbuy.testsuite;

import com.bestbuy.constant.EndPoints;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest extends TestBase {

    static ValidatableResponse response;

    @Test
    public void extractOfProductData() {

        response = given()
                .when()
                .get(EndPoints.PRODUCT)
                .then().log().ifValidationFails().statusCode(200);


        System.out.println("Product Data limit is : " +
                response.extract().path("limit").toString());

        System.out.println("products total is : " +
                response.extract().path("total").toString());

        System.out.println("Name Of 5th Product is : " +
                response.extract().path("data[4].name").toString());

        System.out.println("Name Of All Products is : " +
                response.extract().path("data.name").toString());

        System.out.println("Product Id Of All Product is : " +
                response.extract().path("data.id").toString());

        List<String> data = response.extract().path("data");
        System.out.println("Size of Data List : " + data.size());

        System.out.println("Values for Product Energizer - MAX Batteries AA (4-Pack) : " +
                response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}").toString());

        System.out.println("Model of Product Energizer - N Cell E90 Batteries (2-Pack) : " +
                response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model").toString());

        System.out.println("Categories of 8th Product : " +
                response.extract().path("data[7].categories").toString());

        System.out.println("Categories for Product ID 150115 : " +
                response.extract().path("data.findAll{it.id == 150115}.categories").toString());

        System.out.println("Descriptions of All Products : " +
                response.extract().path("data.description").toString());

        System.out.println("IDs of All Categories of All Products : " +
                response.extract().path("data.categories.id").toString());

        System.out.println("Product Names of Type is HardGood : " +
                response.extract().path("data.findAll{it.type == 'HardGood'}.name").toString());

        List<String> categories = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories[0]");
        System.out.println("Total Categories of product is Duracell - AA 1.5V CopperTop Batteries (4-Pack) : " +
                categories.size());

        System.out.println("CreatedAt for Products with Price < 5.49 : " +
                response.extract().path("data.findAll{it.price < 5.49}.createdAt").toString());

        System.out.println("Category Names Of Product Energizer - MAX Batteries AA (4-Pack) : " +
                response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name").toString());

        System.out.println("Manufacturers of All Products : " +
                response.extract().path("data.manufacturer").toString());

        System.out.println("Images of Products where Manufacturer is Energizer : " +
                response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image").toString());

        System.out.println("CreatedAt for Categories of Products with Price > 5.99 : " +
                response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt").toString());

        System.out.println("URL of All Products : " +
                response.extract().path("data.url").toString());


    }
}
