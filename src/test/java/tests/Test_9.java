package tests;


import api.reqres.specifications.Specifications;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@Tag("@API")
@DisplayName("(RecresTest_9) 404 при получении по несуществующему id GET /api/unknown/{id}")
public class Test_9 {
    private static String URL = "https://reqres.in/";
    private static Integer IdNotFound = 23;
    @Test
    @Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
    @Owner(value = "Калинина Карина Андреевна")
    @Severity(value = SeverityLevel.NORMAL)
    @Description("Проверка 404 при получении по несуществующему id GET /api/unknown/{id}")
    @Step(value = "Делаем GET /api/unknown/{id} по несуществующему id и проверяем что получили 404")

    public void singleUserNotFoundTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecNOTFOUND404());
            given().when()
                .get("api/unknown/" + IdNotFound)
                .then().log().all();
    }
}
