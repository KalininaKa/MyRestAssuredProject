package api.tests;


import api.reqres.specifications.Specifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class RecresTest_9 {
    private static String URL = "https://reqres.in/";
    private static Integer IdNotFound = 23;
    @Test
    @Tag("@RecresTest_9")
    @DisplayName("(RecresTest_9) 404 при получении по несуществующему id GET /api/unknown/{id}")
    public void singleUserNotFoundTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecNOTFOUND404());
            given().when()
                .get("api/unknown/" + IdNotFound)
                .then().log().all();
    }
}
