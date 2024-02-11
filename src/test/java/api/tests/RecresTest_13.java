package api.tests;


import api.reqres.specifications.Specifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class RecresTest_13 {
    private static String URL = "https://reqres.in/";
    private static Integer UserId = 2;
    @Test
    @Tag("@RecresTest_12")
    @DisplayName("(RecresTest_12) Удаление существующего юзера")
    public void deleteUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK204());
            given().when()
                .delete("api/users/" + UserId)
                .then().log().all();
    }
}
