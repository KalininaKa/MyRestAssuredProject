package tests;


import api.reqres.registration.Register;
import api.reqres.registration.SuccessUserReg;
import api.reqres.specifications.Specifications;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



@Tag("@API")
@TmsLink("RecresTest_2")
@Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
@Owner(value = "Калинина Карина Андреевна")
@Severity(value = SeverityLevel.CRITICAL)
@DisplayName("(RecresTest_2) Успешная регистрация")
public class Test_2 {

    @Test
    @Epic(value = "reqres.in")
    @Feature(value = "Тесты для reqres.in")
    @Story(value = "POST api/register")
    @Description("Проверка, что проходит успешная регистрация")
    @Step(value = "Успешная регистрация пользователя, проверка UserId и токена")
    public void successUserRegTest() {
        Integer UserId = 4;
        String UserPassword = "QpwL5tke4Pnpja7X4";
        Specifications.installSpecification(Specifications.requestSpec(), Specifications.responseSpecOK200());
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessUserReg successUserReg = given()
                .body(user)
                .when()
                .post("/api/register")
                .then()
                .log().all()
                .extract().as(SuccessUserReg.class);
       assertNotNull(successUserReg.getId());
       assertNotNull(successUserReg.getToken());
       assertEquals(UserId, successUserReg.getId());
       assertEquals(UserPassword, successUserReg.getToken());
    }
}
