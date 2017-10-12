package extractresponse;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GoogleExtractResponse {

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
    public void extractResponse() {
        Response response =
        given()
                .param("units", "imperial")
                .param("origins", "Washington,DC")
                .param("destinations", "New+York+City,NY")
                .param("key", "AIzaSyC6JzFgtH83zCxWGqY1zpF03y5ZgvuBc3M")
                .when()
                .get("/distancematrix/xml")
                .then()
                .statusCode(200).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);

        // One way to get values by using String Class
        String value = response.path("distancematrixresponse.row.element.duration.value");
        System.out.println("The duration value is " + value);

        // One way to get values by using XmlPath Class
        XmlPath xmlPath = new XmlPath(responseString);
        String valuePath = xmlPath.get("distancematrixresponse.row.element.duration.text");
        System.out.println("The duration value using XmlPath is " + valuePath);
    }
}
