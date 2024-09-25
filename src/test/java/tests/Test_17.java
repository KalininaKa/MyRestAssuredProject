package tests;


import api.reqres.specifications.Specifications;
import io.qameta.allure.*;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItem;
@Epic(value = "reqres.in")
@Feature(value = "Тесты для reqres.in")
@Story(value = "GET api/users")

@Tag("@API")
@TmsLink("RecresTest_17")
@Owner(value = "Калинина Карина Андреевна")
@Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
@Severity(value = SeverityLevel.NORMAL)
@DisplayName("(RecresTest_17) Получение существующего юзера (проверка что юзер с именем Tobias имеет фамилию Funke)")
public class Test_17 {
    private static String URL = "https://reqres.in/";
    private static Integer Page = 2;

    @Test
    @Description("Проверка что юзер с именем Tobias имеет фамилию Funke")
    @Step(value = "Делаем GET api/users/{id} и проверяем что юзер с именем Tobias имеет фамилию Funke")

    public void singleUserFoundTest() {
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        given()
                .when()
                .get("/api/users?page=" + Page)
                .then().assertThat()
                .body("per_page", Is.is(6))
                .body("data.findAll {it.first_name == 'Tobias'}.last_name", hasItem("Funke"));
    }
}
