package basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class POSTRequestDemo {

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
        Response response =
        given()
                .queryParam("key", "AIzaSyD5tt0dLs1q1-ID4980OI4qwRa0gsB-ipg")
                .body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -33.8669710,\n" +
                        "    \"lng\": 151.1958750\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Google Shoes!\",\n" +
                        "  \"phone_number\": \"(02) 9374 4000\",\n" +
                        "  \"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\",\n" +
                        "  \"types\": [\"shoe_store\"],\n" +
                        "  \"website\": \"http://www.google.com.au/\",\n" +
                        "  \"language\": \"en-AU\"\n" +
                        "}")
                .when()
                .post("/place/add/json");
        System.out.println(response.body().asString());
//                .then()
//                .statusCode(200)
//                .and()
//                .contentType(ContentType.JSON)
//                .and()
//                .body("scope", equalTo("APP"))
//                .and()
//                .body("status", equalTo("OK"));
    }
}