package api.tests.reqres;


import api.tests.reqres.specifications.Specifications;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;


public class RecresTest_17 {
    private static String URL = "https://reqres.in/";
    private static Integer Page = 2;

    @Test
    @Tag("@RecresTest_17")
    @DisplayName("(RecresTest_17) Получение существующего юзера (проверка что юзер с именем Tobias имеет фамилию Funke)")
    public void singleUserFoundTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        given()
                .when()
                .get("/api/users?page=" + Page)
                .then().assertThat()
                .body("per_page", is(6))
                .body("data.findAll {it.first_name == 'Tobias'}.last_name", hasItem("Funke"));
    }
}
