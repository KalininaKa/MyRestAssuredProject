package tests;


import api.reqres.specifications.Specifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class Test_4 {
    private static String URL = "https://reqres.in/";
    private static String UserIdNotFound = "23";
    @Test
    @Tag("@API")
    @Tag("@GET")
    @Tag("@Recres")
    @DisplayName("(RecresTest_4) 404 при получении по несуществующему id GET api/users/{id}")
    public void singleUserNotFoundTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecNOTFOUND404());
            given().when()
                .get("api/users/" + UserIdNotFound)
                .then().log().all();
    }
}
