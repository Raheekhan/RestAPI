package specs;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ResponseSpecificationDemo {

    /**
     * Given I have this information
     * When I perform this action
     * Then this should be the ouput
     */

    String consumerKey = "Xb32jq6IXomaN755sKFoR4ddN";
    String consumerSecret = "7fYDvZBNWHB05Y0FCDbw18kADR5aWJ9tkOTrUAt10EEH7Tv7YV";
    String accessToken = "917916223953874944-MxozQg69twJcDfmwwb59nqr5gujIZUU";
    String accessSecret = "5tIHIujFuN1cclRbFbTjkE7B7lj5BZLoqkckg0lfpXpmJ";

    RequestSpecBuilder requestBuilder;
    ResponseSpecBuilder responseBuilder;
    static RequestSpecification requestSpec;
    static ResponseSpecification responseSpec;

    @BeforeClass
    public void initialize() {
        AuthenticationScheme authScheme =
                RestAssured.oauth(consumerKey, consumerSecret, accessToken, accessSecret);

        requestBuilder = new RequestSpecBuilder();
        requestBuilder.setBaseUri("https://api.twitter.com");
        requestBuilder.setBasePath("/1.1/statuses");
        requestBuilder.addQueryParam("user_id", "RaheeIbrahimk");
        requestBuilder.setAuth(authScheme);
        requestSpec = requestBuilder.build();

        responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectStatusCode(200);
        responseBuilder.expectBody("user.name", hasItem("Rahee Khan"));
        responseBuilder.expectBody("user.screen_name", hasItem("RaheeIbrahimk"));
        responseSpec = responseBuilder.build();
    }

    @Test
    public void readTweets() {
        given()
                .spec(requestSpec)
                .when()
                .get("/user_timeline.json")
                .then()
                .spec(responseSpec);
    }
}