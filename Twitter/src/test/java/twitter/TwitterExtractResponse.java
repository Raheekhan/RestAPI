package twitter;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TwitterExtractResponse {

    /**
     * Given I have this information
     * When I perform this action
     * Then this should be the ouput
     */

    String consumerKey = "Xb32jq6IXomaN755sKFoR4ddN";
    String consumerSecret = "7fYDvZBNWHB05Y0FCDbw18kADR5aWJ9tkOTrUAt10EEH7Tv7YV";
    String accessToken = "917916223953874944-MxozQg69twJcDfmwwb59nqr5gujIZUU";
    String accessSecret = "5tIHIujFuN1cclRbFbTjkE7B7lj5BZLoqkckg0lfpXpmJ";

    @BeforeClass
    public void initialize() {
        RestAssured.baseURI = "https://api.twitter.com";
        RestAssured.basePath = "/1.1/statuses";
    }

    @Test
    public void extractResponse() {
        Response response =
        given()
                .auth().oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .queryParam("status", "Tweeting From IntelliJ")
                .when().post("/update.json").then().statusCode(200).extract().response();

        String id = response.path("id_str");
        System.out.println("The response path: " + id);

        String responseString = response.asString();
        System.out.println(responseString);

        JsonPath jsonPath = new JsonPath(responseString);
        String name = jsonPath.get("user.name");
        System.out.println("The username is: " + name);
    }
}