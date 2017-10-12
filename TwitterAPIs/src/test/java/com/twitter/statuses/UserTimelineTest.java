package com.twitter.statuses;

import com.twitter.common.RestUtilities;
import com.twitter.constants.EndPoints;
import com.twitter.constants.Path;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

public class UserTimelineTest {

   RequestSpecification reqSpec;
   ResponseSpecification resSpec;

   @BeforeClass
    public void setUp() {
       reqSpec = RestUtilities.getRequestSpecification();
       reqSpec.queryParam("user_id", "RaheeIbrahimk");
       reqSpec.basePath(Path.STATUSES);

       resSpec = RestUtilities.getResponseSpecification();
   }

   @Test
    public void ReadTweets() {
        given()
                .spec(reqSpec)
                .when().get(EndPoints.STATUSES_USER_TIMELINE)
                .then().spec(resSpec)
                .body("user.screen_name", hasItem("Rahee Khan"));
   }
}