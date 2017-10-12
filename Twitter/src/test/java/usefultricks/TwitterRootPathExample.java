package usefultricks;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class TwitterRootPathExample {

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
        RestAssured.rootPath = "entities.hashtags[0]";
    }


    /**
     * Using rootPath in our initialize method allows us to not
     * write out the path every time we write out test cases.
     * By using RestAssures.rootPath, it is important to remember
     * to not write multiple test cases in a single method, or class.
     */

    private String userPath = "user";

    @Test
    public void readTweets() {
        given()
                .auth().oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .queryParam("status", "Tweeting From IntelliJ")
                .when()
                .get("/user_timeline.json")
                .then()
                .statusCode(200)
                .log().body()
//                .rootPath(userPath)
//                .body("name", hasItem("Rahee Khan"),
//                        "screen_name", hasItem("RaheeIbrahimk"))
                //.rootPath(entityPath)
                .body("text", hasItem("multiple"),
                        "size()", equalTo(2));
    }
}