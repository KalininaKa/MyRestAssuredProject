package tests;


import api.reqres.specifications.Specifications;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;


public class Test_17 {
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
                .body("per_page", Is.is(6))
                .body("data.findAll {it.first_name == 'Tobias'}.last_name", hasItem("Funke"));
    }
}
