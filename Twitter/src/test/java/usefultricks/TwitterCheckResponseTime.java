package usefultricks;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

public class TwitterCheckResponseTime {

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

    private String userPath = "user";
    private String entityPath = "entities.hashtags[0]";

    @Test
    public void readTweets() {
//        long responseTime =
//        given()
//                .auth().oauth(consumerKey, consumerSecret, accessToken, accessSecret)
//                .queryParam("user_id", "RaheeIbrahimk")
//                .when()
//                .get("/user_timeline.json")
//                //.time();
//        .timeIn(TimeUnit.SECONDS);
//        System.out.println("Response Time: " + responseTime);

        given()
                .auth().oauth(consumerKey, consumerSecret, accessToken, accessSecret)
                .queryParam("user_id", "RaheeIbrahimk")
                .when()
                .get("/user_timeline.json")
                .then()
                .statusCode(200)
                .time(lessThan(1L), TimeUnit.SECONDS)
                .log().body()
                .rootPath(userPath)
                .body("name", hasItem("Rahee Khan"),
                        "screen_name", hasItem("RaheeIbrahimk"))
                .rootPath(entityPath)
                .body("text", hasItem("multiple"),
                        "size()", equalTo(2));
    }
}