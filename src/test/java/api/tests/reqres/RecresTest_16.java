package api.tests.reqres;


import api.tests.reqres.specifications.Specifications;
import api.tests.reqres.users.UserData;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;


public class RecresTest_16 {
    private static String URL = "https://reqres.in/";
    private static Integer UserId = 2;

    @Test
    @Tag("@RecresTest_16")
    @DisplayName("(RecresTest_16) Получение существующего юзера (проверка по schema json в ответе)")
    public void singleUserFoundTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        given()
                .when()
                .get("api/users/" + UserId)
                .then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json"));
    }
}
