package basic;

import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class GetRequest {

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
        given()
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", "AIzaSyC6JzFgtH83zCxWGqY1zpF03y5ZgvuBc3M")
                .when()
                .get("/distancematrix/json")
                .then()
                .statusCode(200);
    }

    @Test
    public void getResponseBody() {
        Response response =
                given()
                        .param("units", "imperial")
                        .param("origins", "Washington,DC")
                        .param("destinations", "New+York+City,NY")
                        .param("key", "AIzaSyC6JzFgtH83zCxWGqY1zpF03y5ZgvuBc3M")
                        .when()
                        .get("/distancematrix/json");
        System.out.println(response.body().prettyPrint());
    }
}
