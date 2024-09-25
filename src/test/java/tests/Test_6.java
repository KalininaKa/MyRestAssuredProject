package tests;


import api.reqres.specifications.Specifications;
import api.reqres.users.UserData;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.given;
@Epic(value = "reqres.in")
@Feature(value = "Тесты для reqres.in")
@Story(value = "GET api/users")

@Tag("@API")
@TmsLink("RecresTest_6")
@Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
@Owner(value = "Калинина Карина Андреевна")
@Severity(value = SeverityLevel.MINOR)
@DisplayName("(RecresTest_6) Почта оканчиваются на reqres.in")
public class Test_6 {
    private static String URL = "https://reqres.in/";

    @Test
    @Description("Проверка что почта оканчиваются на reqres.in")
    @Step(value = "Делаем GET api/users?page=2 и проверяем что почта оканчиваются на reqres.in")

    public void checkEmailTest() {
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());

        List<UserData> users = given()
                .when()
                .get("api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

        Assertions.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));
    }
}
