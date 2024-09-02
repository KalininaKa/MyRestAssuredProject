package tests;


import api.reqres.specifications.Specifications;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
@Epic(value = "reqres.in")
@Feature(value = "Тесты для reqres.in")
@Story(value = "GET api/users")

@Tag("@API")
@TmsLink("RecresTest_4")
@Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
@Owner(value = "Калинина Карина Андреевна")
@Severity(value = SeverityLevel.NORMAL)
@DisplayName("(RecresTest_4) 404 при получении по несуществующему id GET api/users/{id}")
public class Test_4 {
    private static String URL = "https://reqres.in/";
    private static String UserIdNotFound = "23";

    @Test
    @Description("Проверка, что получили 404 по несуществующему id")
    @Step(value = "Делаем GET api/users/{id} и получаем 404")

    public void singleUserNotFoundTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecNOTFOUND404());
            given().when()
                .get("api/users/" + UserIdNotFound)
                .then().log().all();
    }
}
