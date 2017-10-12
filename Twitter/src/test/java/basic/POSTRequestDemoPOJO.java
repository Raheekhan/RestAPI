package basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class POSTRequestDemoPOJO {

    /**
     * Given I have this information
     * When I perform this action
     * Then this should be the ouput
     *
     * AIzaSyC6JzFgtH83zCxWGqY1zpF03y5ZgvuBc3M
     */

    @BeforeClass
    public void initialize() {
        RestAssured.baseURI = "https://maps.googleapis.com";
        RestAssured.basePath = "/maps/api";
    }

    @Test
    public void statusCodeVerification() {
        Map<String, Double> locationMap = new HashMap<>();
        locationMap.put("lat", -33.8669710);
        locationMap.put("lng", 151.1958750);

        List<String> types = new ArrayList<>();
        types.add("shoe_store");

        PlacesAddModel places = new PlacesAddModel();
        places.setLocation(locationMap);
        places.setAccuracy(50);
        places.setName("Google Shoes!");
        places.setPhone_number("(02) 9374 4000");
        places.setAddress("48 Pirrama Road, Pyrmont, NSW 2009, Australia");
        places.setTypes(types);
        places.setWebsite("http://www.google.com.au/");
        places.setLanguage("en-AU");

        //Response response =
        given()
                .queryParam("key", "AIzaSyD5tt0dLs1q1-ID4980OI4qwRa0gsB-ipg")
                .body(places)
                .when()
                .post("/place/add/json")
        //System.out.println(response.body().asString());
                .then()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("scope", equalTo("APP"))
                .and()
                .body("status", equalTo("OK"));
    }
}