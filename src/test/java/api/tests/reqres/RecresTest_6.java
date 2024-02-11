package api.tests.reqres;


import api.tests.reqres.specifications.Specifications;
import api.tests.reqres.users.UserData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;


public class RecresTest_6 {
    private static String URL = "https://reqres.in/";

    @Test
    @Tag("@RecresTest_6")
    @DisplayName("(RecresTest_6) Почта оканчиваются на reqres.in")
    public void checkEmailTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());

        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        Assertions.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
    }
}
