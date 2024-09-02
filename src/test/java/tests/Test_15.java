package tests;


import api.reqres.login.Login;
import api.reqres.login.UnsuccessfulLogin;
import api.reqres.specifications.Specifications;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Java6Assertions.assertThat;

@Tag("@API")
@DisplayName("(RecresTest_15) LOGIN-UNSUCCESSFUL (отсутствует пароль)")
public class Test_15 {
    private static String URL = "https://reqres.in/";

    @Test
    @Link(name = "Ссылка на reqres.in", url = "https://reqres.in")
    @Owner(value = "Калинина Карина Андреевна")
    @Severity(value = SeverityLevel.CRITICAL)
    @Description("Проверка LOGIN-UNSUCCESSFUL")
    @Step(value = "Делаем POST api/login и проверяем что LOGIN-UNSUCCESSFUL получена 400 и ErrorText = \"Missing password\"")

    public void LoginUnSuccessfulTest() {
        String Email = "peter@klaven";
        String ErrorText = "Missing password";
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecERROR400());
        Login request = new Login(Email);
        UnsuccessfulLogin loginUnSuccessful = given()
                .body(request)
                .when()
                .post("api/login")
                .then()
                .log().all()
                .extract().as(UnsuccessfulLogin.class);
        assertThat(loginUnSuccessful.getError())
                .isEqualTo(ErrorText);
    }
}
