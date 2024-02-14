package tests;


import api.reqres.specifications.Specifications;
import api.reqres.users.UserData;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;

@Tag("@API")
@DisplayName("(RecresTest_6) Почта оканчиваются на reqres.in")
public class Test_6 {
    private static String URL = "https://reqres.in/";

    @Test
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
