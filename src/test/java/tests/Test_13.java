package tests;


import api.reqres.specifications.Specifications;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class Test_13 {
    private static String URL = "https://reqres.in/";
    private static Integer UserId = 2;
    @Test
    @Tag("@API")
    @Tag("@DELETE")
    @Tag("@Recres")
    @DisplayName("(RecresTest_12) Удаление существующего юзера")
    public void deleteUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK204());
            given().when()
                .delete("api/users/" + UserId)
                .then().log().all();
    }
}
