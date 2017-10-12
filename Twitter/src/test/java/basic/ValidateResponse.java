package basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static javax.swing.text.DefaultStyledDocument.ElementSpec.ContentType;
import static org.hamcrest.CoreMatchers.equalTo;

public class ValidateResponse {

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
                .statusCode(200)
                .and()
                .body("rows[0].elements[0].distance.text", equalTo("225 mi"));
                //.contentType(ContentType.JSON);
    }
}