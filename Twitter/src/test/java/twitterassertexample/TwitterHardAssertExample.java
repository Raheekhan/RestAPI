package twitterassertexample;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TwitterHardAssertExample {

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
    public void readTweets() {
        given()
                .auth().oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .queryParam("status", "Tweeting From IntelliJ")
                .when()
                .get("/user_timeline.json")
                .then()
                .statusCode(200)
                .body("user.name", hasItem("Rahee Khan"))
                .body("entities.hashtags[0].text", hasItem("multiple"))
                .body("entities.hashtags[0].size()", equalTo(2))
                .body("entities.hashtags[1].size()", lessThan(2));
    }
}