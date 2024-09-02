package tests;


import api.reqres.login.Login;
import api.reqres.login.LoginSuccessful;
import api.reqres.specifications.Specifications;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Java6Assertions.assertThat;

@Tag("@API")
@DisplayName("(RecresTest_14) LOGIN-SUCCESSFUL")
public class Test_14 {
    private static String URL = "https://reqres.in/";

    @Test
    @Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
    @Owner(value = "Калинина Карина Андреевна")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Проверка LOGIN-SUCCESSFUL")
    @Step(value = "Делаем POST api/login и проверяем что LOGIN-SUCCESSFUL и получен токен")
    public void LoginSuccessfulTest() {
        String Email = "eve.holt@reqres.in";
        String Password = "cityslicka";
        String Token = "QpwL5tke4Pnpja7X4";
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        Login request = new Login(Email, Password);
        LoginSuccessful loginSuccessful = given()
                .body(request)
                .when()
                .post("api/login")
                .then()
                .log().all()
                .extract().as(LoginSuccessful.class);
        assertThat(loginSuccessful.getToken())
                .isEqualTo(Token);
    }
}
