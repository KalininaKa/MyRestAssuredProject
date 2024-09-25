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
@Story(value = "DELETE api/users")

@Tag("@API")
@TmsLink("RecresTest_13")
@Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
@Owner(value = "Калинина Карина Андреевна")
@Severity(value = SeverityLevel.CRITICAL)
@DisplayName("(RecresTest_12) Удаление существующего юзера")
public class Test_13 {
     private static Integer UserId = 2;
    @Test
   @Description("Проверка DELETE api/users/{id}")
    @Step(value = "Делаем DELETE api/users/{id} и проверяем что юзер успешно удален")
    public void deleteUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK204());
            given().when()
                .delete("/api/users/" + UserId)
                .then().log().all();
    }
}
